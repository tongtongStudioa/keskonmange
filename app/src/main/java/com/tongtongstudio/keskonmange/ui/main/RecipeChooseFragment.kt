package com.tongtongstudio.keskonmange.ui.main

import android.app.Dialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.tongtongstudio.keskonmange.KeskonmangeApplication
import com.tongtongstudio.keskonmange.R
import com.tongtongstudio.keskonmange.database.Recipe
import com.tongtongstudio.keskonmange.database.UserFilters
import com.tongtongstudio.keskonmange.databinding.RecipeChooseFragmentBinding
import com.tongtongstudio.keskonmange.recyclerview.RecipeListAdapter

class RecipeChooseFragment : Fragment(R.layout.recipe_choose_fragment), RecipeListAdapter.OnItemClickListener,
    SearchView.OnQueryTextListener {

    companion object {
        fun newInstance() = RecipeChooseFragment()
    }

    private val secondViewModel: RecipeChooseViewModel by activityViewModels {
        ViewModelFactory((activity?.application as KeskonmangeApplication).repository, "second")
    }

    private lateinit var binding: RecipeChooseFragmentBinding

    private val recipeAdapter: RecipeListAdapter by lazy { RecipeListAdapter(this) }

    private lateinit var recyclerView: RecyclerView
    private lateinit var entryChipGroup: ChipGroup

    // TODO: 29/12/2021 take off the null type
    private var recipesValidate: ArrayList<Recipe>? = null
    private val selectedFilters = ArrayList<String>()
    private val onlyAllFilters: Boolean = false
    private var currentSearchText: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RecipeChooseFragmentBinding.inflate(
            inflater,
            container,
            false
        )

        recyclerView = binding.recyclerviewRecipe
        recyclerView.adapter = recipeAdapter
        recyclerView.layoutManager = GridLayoutManager(context, 2)

        entryChipGroup = binding.entryChipGroupFilters

        secondViewModel.allUserFilters.observe(viewLifecycleOwner) {
            displayMyFilters(it)
        }

        // TODO: 19/01/2022 make this work, i don't know why it's not,other method is employe see recipeChooseActivity
        /*secondViewModel.recipeList.observe(viewLifecycleOwner) { recipes ->
            recipes.let {
                recipesValidate = it
            }
        }*/

        recipesValidate = secondViewModel.recipeArrayList
        //display all recipes
        filterList("all")

        //for create the menu
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.filters_menu -> {
                    showFilterDialog()
                }
                else -> {
                    Toast.makeText(context, getString(R.string.error), Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
        val search = binding.toolbar.menu.findItem(R.id.search_recipe_filters)
        val searchView = search.actionView as SearchView
        searchView.isIconifiedByDefault = true
        searchView.setOnQueryTextListener(this)

        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        binding.toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
        return binding.root
    }

    private fun showFilterDialog() {
        // TODO: 06/11/2021 create dialog view
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.layout_custom_dialog)
        dialog.show()
    }

    private fun displayMyFilters(list: List<UserFilters>) {
        entryChipGroup.removeAllViews()
        for (element in list) {
            val chip = createFilterChip(element)
            entryChipGroup.addView(chip)
        }
    }

    private fun createFilterChip(filter: UserFilters): Chip {
        // TODO: 06/11/2021 create filter chip when filter add to the list
        val chip = Chip(context)
        chip.text = filter.filterName
        chip.setOnCloseIconClickListener { deleteFilter(filter, chip) }

        return chip
    }

    private fun deleteFilter(filter: UserFilters, chip: Chip) {
        //mainViewModel.deleteUserFilter(filter)
        entryChipGroup.removeView(chip)
        Toast.makeText(context, "${filter.filterName} filter was delete !", Toast.LENGTH_SHORT)
            .show()
    }

    override fun onRecipeClick(recipe: Recipe) {
        val intent = Intent(context, RecipeActivity::class.java).apply {
            putExtra("recipe", recipe)
        }
        startActivity(intent)
    }

    // TODO: 29/12/2021 use filter list with chips
    fun filterList(filterCriteria: String) {
        selectedFilters.remove("all")
        if (!selectedFilters.contains(filterCriteria)) { // if not contains then add
            selectedFilters.add(filterCriteria)
        } else {
            selectedFilters.remove(filterCriteria)
        }
        if (selectedFilters.isEmpty() || selectedFilters.contains("all")) {
            recipeAdapter.submitList(recipesValidate)
        } else {
            val filteredRecipes = ArrayList<Recipe>()

            for (recipe in recipesValidate!!) {
                val listOfCriteria: List<String?> = listOf(
                    recipe.type,
                    recipe.origin,
                    recipe.preparationTimeInMin.toString(),
                    recipe.numberOfEat.toString(),
                    recipe.nutritiveValue.toString()
                )
                for (criteria in listOfCriteria) {
                    for (filter in selectedFilters) {
                        if (criteria?.lowercase()
                                ?.contains(filter) == true && !filteredRecipes.contains(recipe)
                        ) {
                            // TODO: 23/01/2022 add filter for < or > preparation time for example : if (filter.contains(">")) do something
                            if (criteria.lowercase()
                                    .contains(currentSearchText.lowercase()) || currentSearchText == ""
                            ) {
                                filteredRecipes.add(recipe)
                            }
                        } else if (filteredRecipes.contains(recipe) && onlyAllFilters) { // onlyAllFilters == true --> to understand
                            filteredRecipes.remove(recipe)
                        }
                    }
                }
            }
            recipeAdapter.submitList(filteredRecipes.toList())
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!searchRecipe(currentSearchText)) Toast.makeText(context, getString(R.string.no_recipes), Toast.LENGTH_SHORT).show()
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            currentSearchText = newText
            searchRecipe(currentSearchText)
        }
        return true
    }

    private fun searchRecipe(query: String): Boolean {

        val searchList = ArrayList<Recipe>()
        val recipeByQuery = secondViewModel.searchRecipesValidate(query)
        if (recipeByQuery.isNotEmpty()) {
            for (recipe in recipeByQuery) {
                if (selectedFilters.contains("all") && !searchList.contains(recipe)) {
                    searchList.add(recipe)
                } else {
                    for (filter in selectedFilters) {
                        // TODO: 29/12/2021 for all criteria
                        if (recipe.type.contains(filter.lowercase()) && !searchList.contains(
                                recipe
                            )
                        ) {
                            searchList.add(recipe)
                            continue
                        }
                    }
                }
            }
            recipeAdapter.submitList(searchList.toList())
            return true

        } else return false
    }
}
