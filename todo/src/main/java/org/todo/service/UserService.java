package org.todo.service;


import org.todo.request.PasswordUpdateRequest;
import org.todo.response.UserResponse;

public interface UserService {
    UserResponse getUserInfo();
    void deleteUser();
    void updateUser(PasswordUpdateRequest passwordUpdateRequest);
}
