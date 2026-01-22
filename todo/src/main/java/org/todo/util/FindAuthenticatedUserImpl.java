package org.todo.util;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.todo.entity.User;

@Component
public class FindAuthenticatedUserImpl implements FindAuthenticatedUser {

    @Override
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(
                authentication == null ||
                !authentication.isAuthenticated() ||
                authentication.getPrincipal().equals("anonymous")) {
            throw new AccessDeniedException("You are not logged in");
        }
        return (User)authentication.getPrincipal();
    }
}
