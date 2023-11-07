package com.tongtongstudio.keskonmange.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.tongtongstudio.keskonmange.KeskonmangeApplication
import com.tongtongstudio.keskonmange.R
import com.tongtongstudio.keskonmange.database.Ingredients
import com.tongtongstudio.keskonmange.database.Recipe
import com.tongtongstudio.keskonmange.database.UserIngredients
import com.tongtongstudio.keskonmange.databinding.MainFragmentBinding
import com.tongtongstudio.keskonmange.recyclerview.IngredientsListAdapter


class MainFragment : Fragment(R.layout.main_fragment), SearchView.OnQueryTextListener,
    IngredientsListAdapter.OnItemClickListener {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val mainViewModel: MainViewModel by viewModels {
        ViewModelFactory((activity?.application as KeskonmangeApplication).repository, "main")
    }

    private lateinit var binding: MainFragmentBinding

    private val ingredientsAdapter: IngredientsListAdapter by lazy { IngredientsListAdapter(this, requireContext()) }

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var entryChipGroup: ChipGroup

    private var allRecipes: List<Recipe>? = null
    private var allUserIngredients: List<UserIngredients>? = null


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = MainFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        requireActivity().setActionBar(binding.toolbar)

        recyclerView = binding.recyclerview
        recyclerView.adapter = ingredientsAdapter
        recyclerView.layoutManager = GridLayoutManager(context, 2) //LinearLayoutManager(context)
        val scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    binding.searchRecipe.hide()
                } else {
                    binding.searchRecipe.show()
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        }
        recyclerView.clearOnScrollListeners()
        recyclerView.addOnScrollListener(scrollListener)
        searchView = binding.searchIngredients
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(this)

        entryChipGroup = binding.entryChipGroupFrigo

        mainViewModel.allUserIngredients.observe(viewLifecycleOwner) {
            allUserIngredients = it
            displayMyIngredients(it)
        }
        mainViewModel.allRecipes.observe(viewLifecycleOwner) {
            allRecipes = it
        }

        displayAllIngredients()

        binding.searchRecipe.setOnClickListener {
            if (allUserIngredients != null && allRecipes != null) {
                val listOfRecipe = mainViewModel.compareRecipe(allUserIngredients!!, allRecipes!!)
                if (listOfRecipe.isNotEmpty()) {
                    val intent = Intent(context, RecipeChooseActivity::class.java).apply {
                        putParcelableArrayListExtra("list", listOfRecipe)
                    // TODO: 19/01/2022 choose a constant to replace string name of extra
                    }
                    startActivity(intent)
                } else {
                    // TODO: 29/12/2021 improve ui and maybe comments
                    Toast.makeText(
                        context,
                        getString(R.string.no_result_recipes),
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            } else {
                Toast.makeText(context, "problem : \nall recipes = $allRecipes", Toast.LENGTH_LONG)
                    .show()
            }
        }
        //for create the menu
        binding.toolbar.inflateMenu(R.menu.main_menu)
        setHasOptionsMenu(true)

        //Inflate the layout for this fragment
        return binding.root
    }

override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    inflater.inflate(R.menu.main_menu, menu)
}

override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when (item.itemId) {
        R.id.deleteAllUserIngredients -> {
            entryChipGroup.removeAllViews()
            mainViewModel.deleteMyIngredients()
            Toast.makeText(context, getString(R.string.all_ingredients_user_delete), Toast.LENGTH_SHORT).show()
            true
        }
        R.id.allRecipeView -> {
            val intent = Intent(context, RecipeChooseActivity::class.java).apply {
                putParcelableArrayListExtra("list", ArrayList(allRecipes!!.toMutableList()))
            }
            startActivity(intent)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}

@SuppressLint("StringFormatInvalid")
private fun deleteIngredient(ingredient: UserIngredients, chip: Chip) {
    mainViewModel.deleteUserIngredient(ingredient.id)
    entryChipGroup.removeView(chip)
    Toast.makeText(context, getString(R.string.ingredient_user_deleted, ingredient.name) , Toast.LENGTH_SHORT).show()
}

private fun createIngredientChip(ingredient: UserIngredients): Chip {
    val chip = Chip(context)
    chip.text = ingredient.name
    chip.setOnCloseIconClickListener { deleteIngredient(ingredient, chip) }

    return chip
}

private fun displayAllIngredients() {
    mainViewModel.allIngredients.observe(viewLifecycleOwner) { list: List<Ingredients> ->
        list.let {
            ingredientsAdapter.submitList(it)
        }
    }
}

private fun displayMyIngredients(list: List<UserIngredients>) {
    entryChipGroup.removeAllViews()
    for (element in list) {
        val chip = createIngredientChip(element)
        entryChipGroup.addView(chip)
    }
}

override fun onIngredientClick(ingredient: Ingredients) {
    val userIngredient =
        UserIngredients(img = ingredient.img, name = ingredient.name, month = ingredient.month)
    var haveNotThisIngredient: Boolean? = null
    mainViewModel.allUserIngredients.observe(viewLifecycleOwner) {
        haveNotThisIngredient = !mainViewModel.haveAlreadySameIngredient(userIngredient, it)
    }
    if (haveNotThisIngredient!!) {
        mainViewModel.insertUserIngredients(userIngredient)
    } // TODO: 15/03/2022 remove ingredient of display  when click

    searchView.setQuery("",false)
}

override fun onQueryTextSubmit(query: String?): Boolean {
    if (query != null) {
        searchDatabase(query)
        // TODO: 23/01/2022 manage no result
        /*Toast.makeText(context, "No result, look for something else...", Toast.LENGTH_SHORT)
            .show()
        displayAllIngredients()*/
        //Toast.makeText(context, "Voici les résultats", Toast.LENGTH_SHORT).show()
    }
    return true
}

override fun onQueryTextChange(query: String?): Boolean {
    if (query != null) {
        searchDatabase(query)
    } else displayAllIngredients()

    return true
}

private fun searchDatabase(query: String) {
    val searchQuery = if (query.toCharArray().size == 1) "$query%"
    else "%$query%"

    mainViewModel.searchIngredients(searchQuery).observe(this) { list ->
        list.let {
            if (it.isNotEmpty()) {
                ingredientsAdapter.submitList(it)
            }
        }
    }
}

}