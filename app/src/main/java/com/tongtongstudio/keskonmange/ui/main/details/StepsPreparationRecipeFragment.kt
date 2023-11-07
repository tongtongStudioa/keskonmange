package com.tongtongstudio.keskonmange.ui.main.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.tongtongstudio.keskonmange.KeskonmangeApplication
import com.tongtongstudio.keskonmange.databinding.StepsPreparationRecipeFragmentBinding
import com.tongtongstudio.keskonmange.ui.main.RecipeViewModel
import com.tongtongstudio.keskonmange.ui.main.ViewModelFactory


class StepsPreparationRecipeFragment : Fragment(){

    private val recipeViewModel: RecipeViewModel by activityViewModels {
        ViewModelFactory((activity?.application as KeskonmangeApplication).repository, "recipe")
    }

    private lateinit var binding: StepsPreparationRecipeFragmentBinding
    private var stepsIndication : List<String>? = null
     override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

         binding = StepsPreparationRecipeFragmentBinding.inflate(
             inflater,
             container,
             false
         )

         recipeViewModel.recipe.observe(viewLifecycleOwner) {
             binding.recipeStep.text = it.preparationStep
             //stepsIndication = recipeViewModel.findNumberOfSteps(it.preparationStep)
         }

         displayStepPreparation()

        return binding.root
    }

    private fun displayStepPreparation() {
        // TODO: 23/01/2022 make it work
        /*for (step in stepsIndication!!) {
            createView(step)
        }*/
    }


    private fun createView(step : String): View {
        // TODO: 23/01/2022 make text view with element basic
        val stepPreparationX = TextView(context)
        stepPreparationX.text = step

        return stepPreparationX
    }
}