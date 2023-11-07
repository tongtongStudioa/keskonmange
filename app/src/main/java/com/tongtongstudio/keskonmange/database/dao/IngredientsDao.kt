package com.tongtongstudio.keskonmange.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tongtongstudio.keskonmange.database.Ingredients
import kotlinx.coroutines.flow.Flow

@Dao
interface IngredientsDao {
    @Insert
    suspend fun insertIngredients(ingredients: Ingredients)

    @Query("DELETE FROM ingredients_table")
    suspend fun deleteAllIngredients()

    // TODO: 19/01/2022 order by type 
    @Query("SELECT * FROM ingredients_table ORDER BY name_of_ingredients ASC")
    fun getAllIngredients(): Flow<List<Ingredients>>

    // TODO: 19/01/2022 make it work 
    @Query("SELECT * FROM ingredients_table WHERE month_to_eat LIKE :month ORDER BY name_of_ingredients ASC")
    fun getAllIngredientsBySeason(month: String): Flow<List<Ingredients>>

    @Query("SELECT * FROM ingredients_table WHERE name_of_ingredients LIKE :searchQuery")
    fun searchIngredientsInDatabase(searchQuery: String): Flow<List<Ingredients>>

    @Query("SELECT COUNT(id) FROM ingredients_table")
    suspend fun getCountIngredients(): Int

    @Query("SELECT * FROM ingredients_table WHERE id = :id ")
    suspend fun getIngredientById(id: Long): Ingredients
}