package com.tongtongstudio.keskonmange.ui.main

import android.os.Bundle
import android.os.Parcelable
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.tongtongstudio.keskonmange.KeskonmangeApplication
import com.tongtongstudio.keskonmange.R
import com.tongtongstudio.keskonmange.database.Recipe


class RecipeActivity : AppCompatActivity() {

    private val recipeViewModel: RecipeViewModel by viewModels {
        ViewModelFactory((application as KeskonmangeApplication).repository, "recipe")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)
        //get the recipe from mainfragment
        val intent = intent
        val recipe: Recipe = intent.getParcelableExtra<Parcelable>("recipe") as Recipe
        // TODO: 19/01/2022 make a constant to replace string name of extra

        recipeViewModel._recipe.value = recipe
    }
}