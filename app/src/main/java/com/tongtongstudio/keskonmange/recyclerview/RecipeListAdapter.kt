package com.tongtongstudio.keskonmange.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tongtongstudio.keskonmange.R
import com.tongtongstudio.keskonmange.database.Recipe

class RecipeListAdapter(private val listener: OnItemClickListener) : ListAdapter<Recipe, RecipeListAdapter.RecipeViewHolder>(RecipesComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val current: Recipe = getItem(position)
        holder.bind(current.title,current.ltlDescription)
    }

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val titleItemView: TextView = itemView.findViewById(R.id.title)
        private val descriptionItemView: TextView = itemView.findViewById(R.id.description)

        fun bind(title: String?, description: String?) {
            titleItemView.text = title
            descriptionItemView.text = description
        }

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                val currentItem: Recipe = getItem(adapterPosition)
                listener.onRecipeClick(currentItem)
            }
        }
    }

    interface OnItemClickListener {
        fun onRecipeClick(recipe: Recipe)
    }

    class RecipesComparator : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.title == newItem.title
        }
    }
}