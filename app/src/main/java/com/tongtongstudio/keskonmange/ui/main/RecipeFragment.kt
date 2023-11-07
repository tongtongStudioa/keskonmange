package com.tongtongstudio.keskonmange.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.tongtongstudio.keskonmange.KeskonmangeApplication
import com.tongtongstudio.keskonmange.databinding.RecipeFragmentBinding
import com.tongtongstudio.keskonmange.ui.main.details.SectionsPagerAdapter

class RecipeFragment : Fragment() {

    private val recipeViewModel: RecipeViewModel by activityViewModels {
        ViewModelFactory((activity?.application as KeskonmangeApplication).repository, "recipe")
    }

    private lateinit var binding: RecipeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RecipeFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        // TODO: 23/01/2022 adapt for view content smoothly with help ui designer
        val sectionsPagerAdapter = SectionsPagerAdapter(requireContext(), fm = childFragmentManager)

        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)
        tabs.tabGravity = TabLayout.GRAVITY_FILL
        viewPager.currentItem = 0

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        // TODO: 23/01/2022 use bottom sheet behavior
        /*BottomSheetBehavior.from(binding.designBottomSheet).apply {
            peekHeight = 600
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }*/


        recipeViewModel.recipe.observe(viewLifecycleOwner){
            binding.recipeTitle.text = it.title
            // TODO: 23/01/2022 add picture ui design
            //if (it.picture != null) binding.imgView.setImageResource(it.picture)
        }
        return binding.root
    }


}