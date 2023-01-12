package com.devmountain.KoreaApp.services;

import com.devmountain.KoreaApp.dtos.RecipeDto;
import com.devmountain.KoreaApp.entites.Recipe;
import com.devmountain.KoreaApp.entites.User;
import com.devmountain.KoreaApp.repositories.RecipeRepository;
import com.devmountain.KoreaApp.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class RecipeServiceImpl implements RecipeService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public List<RecipeDto> getAllRecipesByUserId(Long userId){
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()){
            List<Recipe> recipeList = recipeRepository.findAllByUserEquals(userOptional.get());
            return recipeList.stream().map(recipe -> new RecipeDto(recipe)).collect(Collectors.toList());
        }

        return Collections.emptyList();
    }

    @Override
    @Transactional
    public void addRecipe(RecipeDto recipeDto, Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Recipe recipe = new Recipe(recipeDto);
        userOptional.ifPresent(recipe::setUser);
        recipeRepository.saveAndFlush(recipe);
    }
    @Override
    @Transactional
    public void deleteRecipeById(Long recipeId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        recipeOptional.ifPresent(recipe -> {
            for (User user : recipe.getUserSet()) {
                user.getRecipeSet().remove(recipe);
            }
            recipeRepository.delete(recipe);


        });
    }
    @Override
    @Transactional
    public void updateRecipeById(RecipeDto recipeDto) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeDto.getId());
        recipeOptional.ifPresent(recipe -> {
            recipe.setRecipeName(recipeDto.getRecipeName());
            recipe.setRecipeDescription(recipeDto.getRecipeDescription());
            recipeRepository.saveAndFlush(recipe);
        });
    }

    @Override
    public Optional<RecipeDto> getRecipeById(Long recipeId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        if (recipeOptional.isPresent()){
            return Optional.of(new RecipeDto(recipeOptional.get()));
        }
        return Optional.empty();
    }
    @Override
    public List<RecipeDto> getAllRecipes(){
        List<Recipe> allRecipeList = recipeRepository.findAll();
        return allRecipeList.stream().map(recipe -> new RecipeDto(recipe)).collect(Collectors.toList());
    }

}
