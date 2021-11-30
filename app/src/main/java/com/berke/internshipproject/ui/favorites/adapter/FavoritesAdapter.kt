package com.berke.internshipproject.ui.favorites.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.berke.internshipproject.R
import com.berke.internshipproject.databinding.BindItemFavorite
import com.berke.internshipproject.model.Favorites

class FavoritesAdapter(private val onClickItem: (Favorites) -> Unit): ListAdapter<Favorites, FavoritesAdapter.FavoritesViewHolder>(
    DiffUtilFavorites()
) {

    inner class FavoritesViewHolder(private val binding: BindItemFavorite): RecyclerView.ViewHolder(
        binding.root){
        fun bind(item: Favorites, onClickItem: (Favorites) -> Unit) {
            binding.apply {
                data = item

                root.setOnClickListener { onClickItem.invoke(item) }
                executePendingBindings()
            }
        }
    }

    override fun getItemViewType(position: Int) = R.layout.item_favorites

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val mView = BindItemFavorite.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoritesViewHolder(mView)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bind(getItem(position), onClickItem)
    }
}

class DiffUtilFavorites: androidx.recyclerview.widget.DiffUtil.ItemCallback<Favorites>() {
    override fun areItemsTheSame(oldItem: Favorites, newItem: Favorites): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Favorites, newItem: Favorites): Boolean {
        return oldItem == newItem
    }
}