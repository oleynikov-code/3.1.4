package com.web.controller;

import com.web.models.Role;
import com.web.models.User;
import com.web.repositories.RoleRepo;
import com.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/admin")
public class AdminsControllers {

    private final UserService userService;
    private final RoleRepo roleRepo;

    @Autowired
    public AdminsControllers(UserService userService, RoleRepo roleRepo) {
        this.userService = userService;
        this.roleRepo = roleRepo;
    }

    @GetMapping()
    public String getAllUser(Model model, Authentication authentication){
        String userName = authentication.getName();
        model.addAttribute("user", userService.getUserByEmail(userName));
        model.addAttribute("allUsers",userService.getAllUsers());
        model.addAttribute("newUser", new User());
        model.addAttribute("allRoles", roleRepo.findAll());
        return "getAllUsers";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user){
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") long id){
        userService.updateUser(user, id);
        return "redirect:/admin";
    }

    @DeleteMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") long id ){
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
