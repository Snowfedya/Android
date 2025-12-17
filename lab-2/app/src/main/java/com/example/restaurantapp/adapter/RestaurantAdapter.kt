```kotlin
package com.example.restaurantapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantapp.R
import com.example.restaurantapp.model.RestaurantItem

class RestaurantAdapter : RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    private var items: List<RestaurantItem> = emptyList()
    private var onItemClick: ((RestaurantItem) -> Unit)? = null

    fun submitList(newItems: List<RestaurantItem>) {
        items = newItems
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: (RestaurantItem) -> Unit) {
        onItemClick = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_restaurant, parent, false)
        return RestaurantViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.tvName)
        private val tvCategory: TextView = itemView.findViewById(R.id.tvCategory)
        private val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        private val ivImage: ImageView = itemView.findViewById(R.id.ivImage)

        fun bind(item: RestaurantItem) {
                tvCategory.text = item.category
                ratingBar.rating = item.rating
                tvRating.text = item.rating.toString()
                ivImage.setImageResource(item.imageResource)
            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<RestaurantItem>() {
            override fun areItemsTheSame(oldItem: RestaurantItem, newItem: RestaurantItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: RestaurantItem, newItem: RestaurantItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}