package com.web.controller;

import com.web.models.Role;
import com.web.models.User;
import com.web.repositories.RoleRepo;
import com.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
    public String getAllUser(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("allUsers",userService.getAllUsers());
        model.addAttribute("curentUser",userService.getUserByEmail(authentication.getName()));
        model.addAttribute("allRoles", roleRepo.findAll());
        model.addAttribute("newUser", new User());
        return "getAllUsers";
    }

    @GetMapping("/findUser/{id}")
    public String getUserById(@PathVariable("id") long id, Model model){
        model.addAttribute("user_by_id",userService.getUser(id));
        return "byId";
    }

    @GetMapping("/new")
    public String newUser(Model model){
        List<Role> roles = roleRepo.findAll();
        model.addAttribute("roles", roles);
        model.addAttribute("newUser", new User());
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user){
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String editUser(Model model, @PathVariable("id") long id){
        model.addAttribute("user",userService.getUser(id));
        model.addAttribute("roles", roleRepo.findAll());
        return "edit";
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
