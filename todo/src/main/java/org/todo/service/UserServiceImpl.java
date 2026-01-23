package org.todo.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.todo.entity.Authority;
import org.todo.entity.User;
import org.todo.repository.UserRepository;
import org.todo.request.PasswordUpdateRequest;
import org.todo.response.UserResponse;
import org.todo.util.FindAuthenticatedUser;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository  userRepository;
    private final FindAuthenticatedUser findAuthenticatedUser;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           FindAuthenticatedUser findAuthenticatedUser,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.findAuthenticatedUser = findAuthenticatedUser;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserInfo() {
        User user = findAuthenticatedUser.getAuthenticatedUser();
        return new UserResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getAuthorities()
                        .stream()
                        .map(auth -> (Authority) auth).toList()
        );
    }

    @Override
    @Transactional
    public void deleteUser() {
        User user = findAuthenticatedUser.getAuthenticatedUser();
        if(isLastAdmin(user)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Admin cannot delete itself");
        }
        userRepository.delete(user);
    }

    private boolean isLastAdmin(User user) {
        boolean isAdmin = user.getAuthorities()
                .stream()
                .anyMatch(auth -> "ROLE_ADMIN".equals(auth.getAuthority()));
        if(isAdmin) {
            long adminCount = userRepository.countAdminUsers();
            return adminCount <= 0;
        }
        return false;
    }

    @Override
    @Transactional
    public void updateUser(PasswordUpdateRequest passwordUpdateRequest) {
        User user = findAuthenticatedUser.getAuthenticatedUser();
        if(!isOldPasswordConfirmed(user.getPassword(), passwordUpdateRequest.getOldPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Current password is incorrect");
        }
        if(!isNewPasswordConfirmed(passwordUpdateRequest.getNewPassword(),
                passwordUpdateRequest.getConfirmedPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "New password is incorrect");
        }
        if(!isNewPasswordDifferent(passwordUpdateRequest.getOldPassword(),
                passwordUpdateRequest.getNewPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Old and New passwords are different");
        }
        user.setPassword(passwordEncoder.encode(passwordUpdateRequest.getNewPassword()));
        userRepository.save(user);
    }

    private boolean isNewPasswordDifferent(String oldPassword, String newPassword) {
        return passwordEncoder.equals(newPassword);
    }

    private boolean isNewPasswordConfirmed(String newPassword, String confirmedPassword) {
        return passwordEncoder.equals(confirmedPassword);
    }

    private boolean isOldPasswordConfirmed(String currentPassword, String oldPassword) {
        return passwordEncoder.matches(oldPassword, currentPassword);
    }
}
