package com.devmountain.KoreaApp.dtos;

import com.devmountain.KoreaApp.entites.Recipe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDto implements Serializable {
    private Long id;
    private String recipeName;
    private String recipeDescription;
    private UserDto userDto;

    public RecipeDto(Recipe recipe){
        if (recipe.getId() != null){
            this.id = recipe.getId();
        }
        if (recipe.getRecipeName() !=null){
            this.recipeName = recipe.getRecipeName();
        }
        if (recipe.getRecipeDescription() !=null){
            this.recipeDescription = recipe.getRecipeDescription();
        }
    }
}
