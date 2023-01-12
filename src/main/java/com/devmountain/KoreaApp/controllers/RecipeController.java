package com.devmountain.KoreaApp.controllers;

import com.devmountain.KoreaApp.dtos.RecipeDto;
import com.devmountain.KoreaApp.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/v1/recipes")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @PostMapping("/users/{UserId}")
    public void addRecipe(@RequestBody RecipeDto recipeDto, @PathVariable Long UserId) {
        recipeService.addRecipe(recipeDto, UserId);
    }

    @PutMapping
    public void updateRecipe(@RequestBody RecipeDto recipeDto) {
        recipeService.updateRecipeById(recipeDto);
    }

    @DeleteMapping("/{recipeId}")
    public void deleteRecipeById(@PathVariable long recipeId) {
        recipeService.deleteRecipeById(recipeId);
    }

    @GetMapping("/user/{userId}")
    public List<RecipeDto> getRecipeByUser(@PathVariable Long userId) {
        return recipeService.getAllRecipesByUserId(userId);
    }

    @GetMapping("/{recipeId}")
    public Optional<RecipeDto> getRecipeById(@PathVariable Long recipeId) {
        return recipeService.getRecipeById(recipeId);
    }
    @GetMapping("/")
    public List<RecipeDto> getAllRecipes() {
        return recipeService.getAllRecipes();
    }
}
