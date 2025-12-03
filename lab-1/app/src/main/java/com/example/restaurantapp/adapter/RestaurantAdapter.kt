package com.example.restaurantapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantapp.databinding.ItemRestaurantBinding
import com.example.restaurantapp.model.RestaurantItem

class RestaurantAdapter : ListAdapter<RestaurantItem, RestaurantAdapter.RestaurantViewHolder>(DiffCallback) {

    private var onItemClickListener: ((RestaurantItem) -> Unit)? = null

    fun setOnItemClickListener(listener: (RestaurantItem) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val binding = ItemRestaurantBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RestaurantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class RestaurantViewHolder(private val binding: ItemRestaurantBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    onItemClickListener?.invoke(item)
                }
            }
        }

        fun bind(item: RestaurantItem) {
            binding.apply {
                tvName.text = item.name
                tvDescription.text = item.description
                tvPrice.text = "₽${item.price}"
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