package com.tongtongstudio.keskonmange.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tongtongstudio.keskonmange.Repository

class ViewModelFactory(private val repository: Repository, private val idName: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when (idName) {
            "main" -> { // TODO: 17/01/2022 mettre des constante à la place des string idName
                if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return MainViewModel(repository) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
            "second" -> {
                if (modelClass.isAssignableFrom(RecipeChooseViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return RecipeChooseViewModel(repository) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
            "recipe" -> {
                if (modelClass.isAssignableFrom(RecipeViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return RecipeViewModel(repository) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }

    }
}