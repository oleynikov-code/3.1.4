package com.web.service;

import com.web.models.User;
import com.web.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;



@Service
public class UserServiceImp implements UserService {
    private final UserRepo userRepo;

    @Autowired
    public UserServiceImp(UserRepo userRepo){
        this.userRepo = userRepo;
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
        userRepo.save(user);
    }

    @Override
    @Transactional
    public void updateUser(User user, long id) {
        user.setId(id);
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
