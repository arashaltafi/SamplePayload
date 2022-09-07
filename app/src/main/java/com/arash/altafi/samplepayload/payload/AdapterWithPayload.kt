package com.arash.altafi.samplepayload.payload

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arash.altafi.samplepayload.Item
import com.arash.altafi.samplepayload.databinding.ItemRecyclerviewBinding
import com.bumptech.glide.Glide

class AdapterWithPayload(
    private val favoriteListener: (String, Boolean) -> Unit
) : ListAdapter<Item, AdapterWithPayload.ItemViewHolder>(Companion) {

    companion object : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
        override fun getChangePayload(oldItem: Item, newItem: Item): Any? {
            return if (oldItem.isFavorite != newItem.isFavorite) true else null
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemViewHolder(binding, favoriteListener)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            if (payloads[0] == true) {
                holder.bindFavoriteState(getItem(position).isFavorite)
            }
        }
    }

    inner class ItemViewHolder(
        private val binding: ItemRecyclerviewBinding,
        private val favoriteListener: (String, Boolean) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var item: Item

        init {
            binding.favoriteIcon.setOnClickListener {
                favoriteListener(item.id, !it.isSelected)
            }
        }

        fun bind(item: Item) {
            this.item = item
            Glide.with(binding.root.context).load(item.imageResId).into(binding.image)
            binding.title.text = item.title
            binding.description.text = item.description
            binding.favoriteIcon.isSelected = item.isFavorite
        }

        fun bindFavoriteState(isFavorite: Boolean) {
            binding.favoriteIcon.isSelected = isFavorite
        }
    }
}