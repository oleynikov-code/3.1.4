package com.web.controller;

import com.web.models.User;
import com.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/admin/getAllUsers")
    public String getAllUser(Model model){
        model.addAttribute("allUsers",userService.getAllUsers());
        return "getAllUsers";
    }

    @GetMapping("/user/{id}")
    public String getUserById(@PathVariable("id") long id, Model model){
        model.addAttribute("user_by_id",userService.getUser(id));
        return "byId";
    }

    @GetMapping("/admin/new")
    public String newUser(Model model){
        model.addAttribute("newUser", new User());
        return "new";
    }

    @PostMapping("/admin")
    public String create(@ModelAttribute("user") User user){
        userService.saveUser(user);
        return "redirect:/admin/getAllUsers";
    }
    @GetMapping("/admin/edit/{id}")
    public String editUser(Model model, @PathVariable("id") long id){
        model.addAttribute("user",userService.getUser(id));
        return "edit";
    }
    @PatchMapping("/admin/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") long id){
        userService.updateUser(user, id);
        return "redirect:/admin/getAllUsers";
    }
    @DeleteMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") long id ){
        userService.deleteUser(id);
        return "redirect:/admin/getAllUsers";
    }
}
