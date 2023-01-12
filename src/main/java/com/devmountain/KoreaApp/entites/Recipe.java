package com.devmountain.KoreaApp.entites;

import com.devmountain.KoreaApp.dtos.RecipeDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Recipes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String recipeName;

    @Column(unique = true)
    private String recipeDescription;

    @ManyToOne
    @JsonBackReference
    private User user;

    @ManyToMany(mappedBy = "recipeSet", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @EqualsAndHashCode.Exclude private Set<User> userSet = new HashSet<>();

    public Recipe(RecipeDto recipeDto){
        if(recipeDto.getRecipeName() != null){
            this.recipeName = recipeDto.getRecipeName();
        }
        if(recipeDto.getRecipeDescription() != null){
            this.recipeDescription = recipeDto.getRecipeDescription();
        }
    }

}

