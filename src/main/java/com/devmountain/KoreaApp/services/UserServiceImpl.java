package com.devmountain.KoreaApp.services;

import com.devmountain.KoreaApp.dtos.RecipeDto;
import com.devmountain.KoreaApp.dtos.UserDto;
import com.devmountain.KoreaApp.entites.Recipe;
import com.devmountain.KoreaApp.entites.User;
import com.devmountain.KoreaApp.repositories.RecipeRepository;
import com.devmountain.KoreaApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public List<String> addUser(UserDto userDto){
        List<String> response = new ArrayList<>();
        User user = new User(userDto);
        userRepository.saveAndFlush(user);
        response.add("http://localhost:8080/login.html");
        return response;
    }
    @Override
    public List<String> userLogin(UserDto userDto){
        List<String> response = new ArrayList<>();
        Optional<User> userOptional = userRepository.findByUsername(userDto.getUsername());
        if (userOptional.isPresent()){
            if (passwordEncoder.matches(userDto.getPassword(), userOptional.get().getPassword())) {
                response.add("http://localhost:8080/home.html");
                response.add(String.valueOf(userOptional.get().getId()));

            }else {
                response.add("Username or Password is not correct");
            }
        }
        else {
            response.add("Username or password is not correct");

        }
        return response;
    }
    @Override
    @Transactional
    public List<String> saveRecipe(Long userId, Long recipeId){
        List<String> response = new ArrayList<>();

        Optional<User> userOptional = userRepository.findById(userId);
        userOptional.ifPresent(user -> {

            Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
            recipeOptional.ifPresent(recipe ->{
                user.addRecipe(recipe);
            });
            userRepository.saveAndFlush(user);
        });

return response;
    }


}
