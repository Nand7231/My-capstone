package com.devmountain.KoreaApp.controllers;

import com.devmountain.KoreaApp.dtos.UserDto;
import com.devmountain.KoreaApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin
@RequestMapping("api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public List<String> addUser(@RequestBody UserDto userDto) {
        String passHash = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(passHash);
        return userService.addUser(userDto);
    }

    @PostMapping("/login")
    public List<String> userLogin(@RequestBody UserDto userDto) {
        return userService.userLogin(userDto);
    }

    @PutMapping("/{userId}/recipe/{recipeId}")
    public void saveRecipe(@PathVariable Long userId, @PathVariable Long recipeId) {
        userService.saveRecipe(userId, recipeId);
    }
}
