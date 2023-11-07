package com.tongtongstudio.keskonmange.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tongtongstudio.keskonmange.database.Recipe
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Insert
    suspend fun insertRecipe(recipe: Recipe)

    @Query("DELETE FROM recipe_table")
    suspend fun deleteAllRecipes()

    @Query("SELECT * FROM recipe_table ORDER BY random()")
    fun getAllRecipes(): Flow<List<Recipe>>

    // TODO: 06/11/2021 maybe supress these unused method to display recipe filtered
    /**@Query("SELECT * FROM recipe_table WHERE recipe_type = :type")
    fun getAllRecipesByType(type: String): Flow<List<Recipe>>

    @Query("SELECT * FROM recipe_table WHERE recipe_origin = :origins")
    fun getAllRecipesByOrigins(origins: String): Flow<List<Recipe>>

    @Query("SELECT * FROM recipe_table WHERE preparation_time <= :time")
    fun getAllRecipesByTime(time: Int): Flow<List<Recipe>>

    @Query("SELECT * FROM recipe_table WHERE nutritive_value <= :nutritiveValue")
    fun getAllRecipesByNutritiveValueLes(nutritiveValue: Int, code: Int): Flow<List<Recipe>>

    @Query("SELECT * FROM recipe_table WHERE nutritive_value >= :nutritiveValue")
    fun getAllRecipesByNutritiveValueSup(nutritiveValue: Int, code: Int): Flow<List<Recipe>>

    @Query("SELECT * FROM recipe_table ORDER BY number_of_time_eat ASC")
    fun getAllRecipesByNumberOfEat(): Flow<List<Recipe>>*/

    @Query("SELECT COUNT(id) FROM recipe_table")
    suspend fun getCountRecipes(): Int

    @Query("SELECT * FROM recipe_table WHERE id = :id ")
    suspend fun getRecipeById(id: Long): Recipe

    // TODO: 29/12/2021 select all from recipe table in titlle but also in other criteria
    @Query("SELECT * FROM recipe_table WHERE title LIKE :searchQuery")
    fun searchRecipes(searchQuery: String): Flow<List<Recipe>>

}