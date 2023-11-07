package com.tongtongstudio.keskonmange.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tongtongstudio.keskonmange.Repository
import com.tongtongstudio.keskonmange.database.Recipe

class RecipeViewModel(repository: Repository) : ViewModel() {
    val _recipe = MutableLiveData<Recipe>()

    val recipe: LiveData<Recipe>
        get() = _recipe

    fun retrieveIngredientsList(recipe: Recipe): List<String> {
        val listOfIngredientsAndTheirQuantities = recipe.ingredientsAndQuantities.split(";")
        var onlyIngredients: String= ""
        for (eachIngredient in listOfIngredientsAndTheirQuantities){
            val listOfOneIngredientWithHisQuantities = eachIngredient.split("/")
            onlyIngredients += listOfOneIngredientWithHisQuantities[0]
            if (eachIngredient != listOfIngredientsAndTheirQuantities.last()) {
                onlyIngredients += ";"
            }
        }
        return onlyIngredients.split(";")
    }

    fun retrieveFilterList(recipe: Recipe): List<String> {
        return "${recipe.preparationTimeInMin}min;${recipe.type};${recipe.origin?:"origine inconnue"};${recipe.nutritiveValue} calories".split(";")
    }

    fun findNumberOfSteps(stepsPreparation: String): List<String> {
        return stepsPreparation.split("/")
    }

}