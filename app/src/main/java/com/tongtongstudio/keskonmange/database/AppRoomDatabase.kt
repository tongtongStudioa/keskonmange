package com.tongtongstudio.keskonmange.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.tongtongstudio.keskonmange.database.dao.IngredientsDao
import com.tongtongstudio.keskonmange.database.dao.RecipeDao
import com.tongtongstudio.keskonmange.database.dao.UserFilterDao
import com.tongtongstudio.keskonmange.database.dao.UserIngredientsDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Recipe::class,Ingredients::class,UserIngredients::class,UserFilters::class], version = 1,exportSchema = false)
abstract class AppRoomDatabase : RoomDatabase() {
    //DAO
    abstract fun recipeDao(): RecipeDao
    abstract fun ingredientsDao(): IngredientsDao
    abstract fun userIngredientsDao(): UserIngredientsDao
    abstract fun userFiltersDao(): UserFilterDao




    private class AppDatabaseCallback( private val scope: CoroutineScope, context: Context) : RoomDatabase.Callback() {

        //data for recipe and ingredients
        val data = DataRecipeAndIngredients(context)
        val listOfIngredients = data.listOfIngredients
        val listOfRecipes = data.listOfRecipes

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.recipeDao(), database.ingredientsDao(),listOfIngredients, listOfRecipes)
                }
            }
        }

        suspend fun populateDatabase(recipeDao: RecipeDao, ingredientsDao: IngredientsDao, listIngredients: ArrayList<Ingredients>, listRecipes: ArrayList<Recipe>) {
            // Delete all content here.
            recipeDao.deleteAllRecipes()
            ingredientsDao.deleteAllIngredients()

            // Add sample recipe.
            for (recipe in listRecipes) {
                recipeDao.insertRecipe(recipe)
            }

            // Add Ingredient
            for(ingredient in listIngredients) {
                ingredientsDao.insertIngredients(ingredient)
            }
        }
    }

    companion object {
        // Singleton prevents multiple instances of com.tongtongstudio.keskonmange.database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the com.tongtongstudio.keskonmange.database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppRoomDatabase::class.java,
                    "_database"
                )
                        .fallbackToDestructiveMigration()
                        .addCallback(AppDatabaseCallback(scope, context.applicationContext))
                        .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}