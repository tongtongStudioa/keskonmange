package com.tongtongstudio.keskonmange.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.tongtongstudio.keskonmange.Repository
import com.tongtongstudio.keskonmange.database.Recipe
import com.tongtongstudio.keskonmange.database.UserFilters

class RecipeChooseViewModel(private val repository: Repository) : ViewModel() {
    val _recipeList = MutableLiveData<ArrayList<Recipe>>()

    val recipeList: LiveData<ArrayList<Recipe>>
        get() = _recipeList

    var recipeArrayList : ArrayList<Recipe>? = null

    val allUserFilters = repository.allUserFilters.asLiveData()

    val recipeTypeFilter : LiveData<UserFilters> = repository.getUserFilterByName("Type").asLiveData()
    val recipeOriginFilter : LiveData<UserFilters> = repository.getUserFilterByName("Origin").asLiveData()
    val recipeNutritiveValueFilter : LiveData<UserFilters> = repository.getUserFilterByName("NutritiveValue").asLiveData()
    val recipeNumberOfEatFilter: LiveData<UserFilters> = repository.getUserFilterByName("NOE").asLiveData()
    val recipePreparationTimeFilter: LiveData<UserFilters> = repository.getUserFilterByName("PreparationTime").asLiveData()

    fun searchRecipeDatabase(searchQuery: String): LiveData<List<Recipe>> {
        return repository.searchRecipeDatabase(searchQuery).asLiveData()
    }

    fun searchRecipesValidate(searchQuery: String): List<Recipe> {
        val searchRecipeByQuery = ArrayList<Recipe>()
        if (recipeArrayList != null){
            for (recipe in recipeArrayList!!.toList()) {
                // search "like" text in title
                if (searchQuery.toCharArray().size == 1) {
                    if (recipe.title.first().toString().lowercase() == searchQuery.lowercase()) {
                        searchRecipeByQuery.add(recipe)
                    }
                }else
                if (recipe.title.lowercase().contains(searchQuery.lowercase())){
                    searchRecipeByQuery.add(recipe)
                }
            }
        }//else searchRecipeByQuery.add(Recipe(title = "test",ingredientsAndQuantities = "ddk",preparationTimeInMin = 5,preparationStep = "kd",ltlDescription = "test ta mere",nutritiveValue = 8,origin = "kd",type = "kd",necessaryTools = null,personNumber = 5))
        return searchRecipeByQuery.toList()
    }
}