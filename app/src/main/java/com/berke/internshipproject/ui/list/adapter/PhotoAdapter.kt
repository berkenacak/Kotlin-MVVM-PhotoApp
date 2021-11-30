package com.berke.internshipproject.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.berke.internshipproject.R
import com.berke.internshipproject.databinding.BindItemPhoto
import com.berke.internshipproject.model.ImageModel

class PhotoAdapter(private val onClickItem: (ImageModel) -> Unit): ListAdapter<ImageModel, PhotoAdapter.PhotoViewHolder>(
    DiffUtilPhotos()
) {

    inner class PhotoViewHolder(private val binding: BindItemPhoto): RecyclerView.ViewHolder(
        binding.root){
        fun bind(item: ImageModel, onClickItem: (ImageModel) -> Unit) {
            binding.apply {
                data = item

                root.setOnClickListener { onClickItem.invoke(item) }
                executePendingBindings()
            }
        }
    }

    override fun getItemViewType(position: Int) = R.layout.item_photo

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val mView = BindItemPhoto.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(mView)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(getItem(position), onClickItem)
    }
}

class DiffUtilPhotos: androidx.recyclerview.widget.DiffUtil.ItemCallback<ImageModel>() {
    override fun areItemsTheSame(oldItem: ImageModel, newItem: ImageModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ImageModel, newItem: ImageModel): Boolean {
        return oldItem == newItem
    }
}