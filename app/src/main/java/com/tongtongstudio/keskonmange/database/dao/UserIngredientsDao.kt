package com.tongtongstudio.keskonmange.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tongtongstudio.keskonmange.database.UserIngredients
import kotlinx.coroutines.flow.Flow

@Dao
interface UserIngredientsDao {
    @Insert
    suspend fun insertIngredients(ingredients: UserIngredients)

    @Query("DELETE FROM user_ingredients_table")
    suspend fun deleteAllIngredients()

    @Query("DELETE FROM user_ingredients_table WHERE id = :id")
    suspend fun deleteIngredient(id: Long)

    @Query("SELECT * FROM user_ingredients_table ORDER BY id DESC")
    fun getAllUserIngredients(): Flow<List<UserIngredients>>

    @Query("SELECT COUNT(id) FROM user_ingredients_table")
    suspend fun getCountIngredients(): Int

    @Query("SELECT * FROM user_ingredients_table WHERE id = :id ")
    suspend fun getIngredientsById(id: Long): UserIngredients
}