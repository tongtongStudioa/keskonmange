package com.tongtongstudio.keskonmange

import android.app.Application
import com.tongtongstudio.keskonmange.database.AppRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

open class KeskonmangeApplication : Application() {
    // No need to cancel this scope as it'll be torn down with the process
    private val applicationScope = CoroutineScope(SupervisorJob())
    // Using by lazy so the com.tongtongstudio.keskonmange.database and the repository are only created when they're needed
    // rather than when the application starts
    private val database: AppRoomDatabase by lazy { AppRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { Repository(database.recipeDao(), database.ingredientsDao(), database.userIngredientsDao(), database.userFiltersDao()) }
}