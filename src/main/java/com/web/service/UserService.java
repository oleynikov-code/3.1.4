package com.web.service;


import com.web.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService{
List<User> getAllUsers();
User getUser(long id);
void saveUser(User user);
void updateUser(User user, long id);
void deleteUser(long id);
UserDetails loadUserByUsername(String username);
}
