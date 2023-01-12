package com.devmountain.KoreaApp.repositories;

import com.devmountain.KoreaApp.entites.Recipe;
import com.devmountain.KoreaApp.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findAllByUserEquals(User user);




}
