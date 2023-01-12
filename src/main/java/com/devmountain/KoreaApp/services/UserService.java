package com.devmountain.KoreaApp.services;

import com.devmountain.KoreaApp.dtos.UserDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    @Transactional
    List<String> addUser(UserDto userDto);
    List<String> userLogin(UserDto userDto);
    @Transactional
    List<String> saveRecipe(Long userId, Long recipeId);
}
