package com.arash.altafi.samplepayload.withoutPayload

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arash.altafi.samplepayload.Item
import com.arash.altafi.samplepayload.databinding.ItemRecyclerviewBinding
import com.bumptech.glide.Glide

class AdapterWithoutPayload(
    private val favoriteListener: (String, Boolean) -> Unit
) : ListAdapter<Item, AdapterWithoutPayload.ItemViewHolder>(Companion) {

    companion object : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
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
    }
}