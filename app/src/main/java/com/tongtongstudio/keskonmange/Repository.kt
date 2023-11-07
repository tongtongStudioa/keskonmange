package com.tongtongstudio.keskonmange

import androidx.annotation.WorkerThread
import com.tongtongstudio.keskonmange.database.Ingredients
import com.tongtongstudio.keskonmange.database.Recipe
import com.tongtongstudio.keskonmange.database.UserFilters
import com.tongtongstudio.keskonmange.database.UserIngredients
import com.tongtongstudio.keskonmange.database.dao.IngredientsDao
import com.tongtongstudio.keskonmange.database.dao.RecipeDao
import com.tongtongstudio.keskonmange.database.dao.UserFilterDao
import com.tongtongstudio.keskonmange.database.dao.UserIngredientsDao
import kotlinx.coroutines.flow.Flow

class Repository(private val recipeDao: RecipeDao, private val ingredientsDao: IngredientsDao, private val userIngredientsDao: UserIngredientsDao, private val userFiltersDao: UserFilterDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allRecipes: Flow<List<Recipe>> =  recipeDao.getAllRecipes()

    val allIngredients: Flow<List<Ingredients>> = ingredientsDao.getAllIngredients()

    val allUserIngredients: Flow<List<UserIngredients>> = userIngredientsDao.getAllUserIngredients()

    val allUserFilters: Flow<List<UserFilters>> = userFiltersDao.getAllFilters()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running com.tongtongstudio.keskonmange.database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertRecipe(recipe: Recipe) {
        recipeDao.insertRecipe(recipe)
    }

    suspend fun deleteAllRecipes() {
        recipeDao.deleteAllRecipes()
    }

    suspend fun countOfRecipes(): Int {
        return recipeDao.getCountRecipes()
    }


    suspend fun getRecipeById(id: Long): Recipe{
        return recipeDao.getRecipeById(id)
    }

    // ingredients DAO
    fun searchIngredientsInDatabase(searchQuery: String): Flow<List<Ingredients>> {
        return ingredientsDao.searchIngredientsInDatabase(searchQuery)
    }

    suspend fun getIngredientById(id: Long): Ingredients{
        return ingredientsDao.getIngredientById(id)
    }

    // user ingredients DAO
    suspend fun deleteAllUserIngredients(){
        userIngredientsDao.deleteAllIngredients()
    }

    suspend fun deleteIngredient(id: Long) {
        userIngredientsDao.deleteIngredient(id)
    }

    suspend fun insertUserIngredient(ingredient: UserIngredients) {
        userIngredientsDao.insertIngredients(ingredient)
    }

    suspend fun countOfUserIngredients(): Int {
        return userIngredientsDao.getCountIngredients()
    }

    suspend fun getUserIngredientById(id: Long): UserIngredients{
        return userIngredientsDao.getIngredientsById(id)
    }

    // user filters dao

    suspend fun insertFilter(filter: UserFilters) {
        userFiltersDao.insertFilter(filter)
    }

    suspend fun deleteAllFilters() {
        userFiltersDao.deleteAllFilters()
    }
    suspend fun deleteUserFilter(id: Long) {
        userFiltersDao.deleteUserFilter(id)
    }

    fun getUserFilterByName(name: String): Flow<UserFilters> {
        return userFiltersDao.getUserFilterByName(name)
    }

    fun searchRecipeDatabase(searchQuery: String): Flow<List<Recipe>> {
        return recipeDao.searchRecipes(searchQuery)
    }


}