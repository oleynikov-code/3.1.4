package com.web.service;


import com.web.models.User;

import java.util.List;

public interface UserService {
List<User> getAllUsers();
User getUser(long id);
void saveUser(User user);
void updateUser(User user, long id);
void deleteUser(long id);
}
