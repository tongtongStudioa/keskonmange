package com.tongtongstudio.keskonmange.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.tongtongstudio.keskonmange.KeskonmangeApplication
import com.tongtongstudio.keskonmange.R
import com.tongtongstudio.keskonmange.database.Recipe

class RecipeChooseActivity : AppCompatActivity() {

    private val recipeChooseViewModel: RecipeChooseViewModel by viewModels {
        ViewModelFactory((application as KeskonmangeApplication).repository, "second")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = ""
        //get the recipe from mainfragment
        val intent = intent
        val recipeList = intent.getParcelableArrayListExtra<Recipe>("list")

        recipeChooseViewModel._recipeList.value = recipeList
        recipeChooseViewModel.recipeArrayList = recipeList

        setContentView(R.layout.activity_recipe_choose)
    }
}