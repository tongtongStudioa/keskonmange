package com.tongtongstudio.keskonmange.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.tongtongstudio.keskonmange.Repository
import com.tongtongstudio.keskonmange.database.Ingredients
import com.tongtongstudio.keskonmange.database.Recipe
import com.tongtongstudio.keskonmange.database.UserFilters
import com.tongtongstudio.keskonmange.database.UserIngredients
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {

    val allRecipes : LiveData<List<Recipe>> = repository.allRecipes.asLiveData()
    val allIngredients : LiveData<List<Ingredients>> = repository.allIngredients.asLiveData()
    val allUserIngredients : LiveData<List<UserIngredients>> = repository.allUserIngredients.asLiveData()

    fun compareRecipe(allUserIngredients: List<UserIngredients>, allRecipe: List<Recipe>): ArrayList<Recipe> {

        val ingredientListUser: ArrayList<String> = ArrayList()
        for (ingredients in allUserIngredients){
            ingredientListUser.add(ingredients.name.lowercase())
        }

        val listOfRecipeToCook: ArrayList<Recipe> = ArrayList()
        for (recipe in allRecipe) {
            val ingredientsListOfRecipe: List<String> = retrieveIngredientsList(recipe)
            ///////////// logic
            if (ingredientListUser.containsAll(ingredientsListOfRecipe)) {
                listOfRecipeToCook.add(recipe)
            }
            ///////////
        }

        return listOfRecipeToCook
    }

    private fun retrieveIngredientsList(recipe: Recipe): List<String> {
        val listOfIngredientsAndTheirQuantities = recipe.ingredientsAndQuantities.split(";")
        var onlyIngredients = ""
        for (eachIngredient in listOfIngredientsAndTheirQuantities){
            val listOfOneIngredientWithHisQuantities = eachIngredient.split("/")
            onlyIngredients += listOfOneIngredientWithHisQuantities[0].lowercase()
            if (eachIngredient != listOfIngredientsAndTheirQuantities.last()) {
                onlyIngredients += ";"
            }
        }
        return onlyIngredients.split(";")
    }

    fun deleteUserIngredient(id: Long){
        viewModelScope.launch {
            repository.deleteIngredient(id)
        }
    }

    fun deleteMyIngredients(){
        viewModelScope.launch {
            repository.deleteAllUserIngredients()
        }
    }

    fun insertUserIngredients(userIngredients: UserIngredients){
        viewModelScope.launch {
            repository.insertUserIngredient(userIngredients)
        }
    }

    fun searchIngredients(searchQuery: String): LiveData<List<Ingredients>> {
        return repository.searchIngredientsInDatabase(searchQuery).asLiveData()
    }

    fun haveAlreadySameIngredient(ingredient: UserIngredients, allUserIngredients: List<UserIngredients>): Boolean {
        val ingredientListUser: ArrayList<String> = ArrayList()
        for (element in allUserIngredients){
            ingredientListUser.add(element.name)
        }

        return ingredientListUser.contains(ingredient.name) // true if it's contains the same
    }

    fun deleteUserFilter(filter: UserFilters) {
        viewModelScope.launch {
            repository.deleteUserFilter(filter.id)
        }
    }

}