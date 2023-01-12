package com.devmountain.KoreaApp.services;

import com.devmountain.KoreaApp.dtos.RecipeDto;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface RecipeService {
    List<RecipeDto> getAllRecipes();
    List<RecipeDto> getAllRecipesByUserId(Long userId);
    @Transactional
    void addRecipe(RecipeDto recipeDto, Long userId);

    @Transactional
    void deleteRecipeById(Long recipeId);

    @Transactional
    void updateRecipeById(RecipeDto recipeDto);

    Optional<RecipeDto> getRecipeById(Long recipeId);
}
