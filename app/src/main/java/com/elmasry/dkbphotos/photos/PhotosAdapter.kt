package com.elmasry.dkbphotos.photos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.elmasry.dkbphotos.databinding.ItemPhotoBinding
import com.elmasry.dkbphotos.model.Photo
import com.elmasry.dkbphotos.photos.PhotosAdapter.PhotoItemViewHolder

class PhotosAdapter() : ListAdapter<Photo, PhotoItemViewHolder>(PhotosDiffCallback()) {

    class PhotoItemViewHolder(
        private val itemBinding: ItemPhotoBinding,
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(photo: Photo) {
            Glide.with(itemView.context)
                .load(photo.thumbnailUrl)
                .into(itemBinding.imageViewDkbPhoto)
            itemBinding.textViewTitle.text = photo.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoItemViewHolder {
        val itemBinding = ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoItemViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PhotoItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class PhotosDiffCallback : DiffUtil.ItemCallback<Photo>() {
    override fun areItemsTheSame(p0: Photo, p1: Photo) = p0.id == p1.id

    override fun areContentsTheSame(p0: Photo, p1: Photo) = p0 == p1
}
