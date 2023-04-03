package com.web.service;

import com.web.models.User;
import com.web.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;



@Service
public class UserServiceImp implements UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserServiceImp(UserRepo userRepo, PasswordEncoder passwordEncoder){
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUser(long id) {
        Optional<User> user = userRepo.findById(id);
        return user.orElse(null);
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    @Override
    @Transactional
    public void updateUser(User user, long id) {
        user.setId(id);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        userRepo.deleteById(id);
    }

    @Override
     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException(String.format("Пользователь '%s' не найден", username));
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

}
