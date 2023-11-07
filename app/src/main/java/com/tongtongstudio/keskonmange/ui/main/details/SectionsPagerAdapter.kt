package com.tongtongstudio.keskonmange.ui.main.details

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.tongtongstudio.keskonmange.R

private val TAB_TITLES = arrayOf(
    R.string.infoRecipe,
    R.string.StepsPreparation
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context,
                           private val mNumOfTabs: Int = TAB_TITLES.size,
                           fm: FragmentManager
) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        return when (position) {
            1 -> StepsPreparationRecipeFragment()
            0 -> InfoRecipeFragment()

            else -> InfoRecipeFragment() // TODO: 06/11/2021 search how to improve leaks 
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show total pages.
        return mNumOfTabs
    }
}