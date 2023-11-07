package com.tongtongstudio.keskonmange.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "recipe_table")
data class Recipe (
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "picture") val picture: Int? = null,
    @ColumnInfo(name = "ingredients_and_quantities") val ingredientsAndQuantities: String, // "tomate/2;banane/3;fromage/300g;lait/2l" /
    @ColumnInfo(name = "preparation_time") val preparationTimeInMin: Int, // ex : 120    ...... in minute
    @ColumnInfo(name = "preparation_step") val preparationStep: String,
    @ColumnInfo(name = "description") val ltlDescription: String, // little description for the recycler view
    @ColumnInfo(name = "recipe_type") val type: String, // entrée, plat, dessert
    @ColumnInfo(name = "person_number")val personNumber: Int,
    @ColumnInfo(name = "nutritive_value") val nutritiveValue: Int? = null, // 200;500;300  -> lipides, protides, glucides
    @ColumnInfo(name = "recipe_origin") val origin: String? = null,// francais, marocain, belge...
    @ColumnInfo(name = "necessary_tools") val necessaryTools: String? = null,// poele;fouet;fourchette
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "number_of_time_eat") var numberOfEat: Int = 0): Parcelable

@Entity(tableName = "ingredients_table")
class Ingredients(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "Photo") val img: Long? = null,
    @ColumnInfo(name = "name_of_ingredients") val name: String,
    @ColumnInfo(name = "month_to_eat")val month: String? = null,
    @ColumnInfo(name = "category") val category: String? = null)

@Entity(tableName = "user_ingredients_table")
class UserIngredients(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "Photo") val img: Long? = null,
    @ColumnInfo(name = "name_of_ingredients") val name: String,
    @ColumnInfo(name = "month_to_eat")val month: String? = null)

@Entity(tableName = "user_filters")
class UserFilters(
    @ColumnInfo(name = "filter_name") val filterName: String,
    @ColumnInfo(name = "filter_value_String") val valueString: String?,
    @ColumnInfo(name = "filter_value_Int") val valueInt: Int?,
    @ColumnInfo(name = "code_for_sign") val code: Char?, // sign for nutritive value : > 200 calories

    @PrimaryKey(autoGenerate = true) val id: Long = 0)

