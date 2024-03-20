package com.example.labjee.helpers;

import com.example.labjee.models.User;

public interface PasswordChanger {
    void checkPassword(User user, String password);
    void changePassword(User user, String newPassword);
}
