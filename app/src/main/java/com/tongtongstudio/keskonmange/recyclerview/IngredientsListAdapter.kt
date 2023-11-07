package com.tongtongstudio.keskonmange.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tongtongstudio.keskonmange.R
import com.tongtongstudio.keskonmange.database.Ingredients
import com.tongtongstudio.keskonmange.databinding.RecyclerviewItemIngredientBinding

class IngredientsListAdapter(private val listener: OnItemClickListener, val context: Context) : ListAdapter<Ingredients, IngredientsListAdapter.IngredientsViewHolder>(
    IngredientsComparator()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item_ingredient, parent, false)
        return IngredientsViewHolder(RecyclerviewItemIngredientBinding.bind(view))
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        val currentIngredient: Ingredients = getItem(position)
        holder.bind(currentIngredient)
        // TODO: 23/04/2022 change background card color when ingredient selected or not
    }

    inner class IngredientsViewHolder(private val binding: RecyclerviewItemIngredientBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(ingredient: Ingredients) {
            binding.apply {
                ingredientName.text = ingredient.name
            }
        }

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                val currentItem: Ingredients = getItem(adapterPosition)
                listener.onIngredientClick(currentItem)
            }
        }
    }

    interface OnItemClickListener {
        fun onIngredientClick(ingredient: Ingredients)
    }

    class IngredientsComparator : DiffUtil.ItemCallback<Ingredients>() {
        override fun areItemsTheSame(oldItem: Ingredients, newItem: Ingredients): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Ingredients, newItem: Ingredients): Boolean {
            return oldItem.name == newItem.name
        }
    }
}