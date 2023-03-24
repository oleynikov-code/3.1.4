package com.web.controller;

import com.web.models.User;
import com.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsersControllers {

    private final UserService userService;

    @Autowired
    public UsersControllers(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getThisUser(Authentication authentication, Model model){
        String userName = authentication.getName();
        User user = userService.getUserByEmail(userName);
        model.addAttribute("user", user);
        return "thisUser";
    }

}
