package com.tongtongstudio.keskonmange.ui.main.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.tongtongstudio.keskonmange.KeskonmangeApplication
import com.tongtongstudio.keskonmange.databinding.InfoRecipeFragmentBinding
import com.tongtongstudio.keskonmange.ui.main.RecipeViewModel
import com.tongtongstudio.keskonmange.ui.main.ViewModelFactory

class InfoRecipeFragment : Fragment() {

    private val recipeViewModel: RecipeViewModel by activityViewModels {
        ViewModelFactory((activity?.application as KeskonmangeApplication).repository, "recipe")
    }

    private lateinit var binding: InfoRecipeFragmentBinding
    private lateinit var ingredientsChipGroup: ChipGroup
    private lateinit var filterChipGroup: ChipGroup

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = InfoRecipeFragmentBinding.inflate(
            inflater,
            container,
            false
        )

        filterChipGroup = binding.filterChipGroup
        ingredientsChipGroup = binding.ingredientsChipGroup
        recipeViewModel.recipe.observe(viewLifecycleOwner) {
            val listIngredients = recipeViewModel.retrieveIngredientsList(it)
            displayRecipeIngredients(listIngredients)
            val listFilter = recipeViewModel.retrieveFilterList(it)
            displayAllFilter(listFilter)
        }
        return binding.root
    }

    private fun displayRecipeIngredients(recipeListIngredients: List<String>) {
        ingredientsChipGroup.removeAllViews()
        for (element in recipeListIngredients) {
            val chip = createChip(element)
            ingredientsChipGroup.addView(chip)
        }
    }

    private fun createChip(name: String): Chip {
        val chip = Chip(context)
        chip.text = name
        chip.isCloseIconVisible = false
        chip.isFocusable = false
        chip.isCheckable = false

        return chip
    }



    private fun displayAllFilter(recipeListFilter: List<String>) {
        filterChipGroup.removeAllViews()
        for (element in recipeListFilter) {
            val chip = createChip(element)
            filterChipGroup.addView(chip)
        }
    }
}