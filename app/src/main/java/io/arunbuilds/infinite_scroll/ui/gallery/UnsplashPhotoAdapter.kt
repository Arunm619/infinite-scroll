package io.arunbuilds.infinite_scroll.ui.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import io.arunbuilds.infinite_scroll.R
import io.arunbuilds.infinite_scroll.data.UnsplashPhoto
import io.arunbuilds.infinite_scroll.databinding.ItemUnspashPhotoBinding

class UnsplashPhotoAdapter(private val listener: OnItemClickListener) :
    PagingDataAdapter<UnsplashPhoto, UnsplashPhotoAdapter.UnsplashPhotoViewHolder>(PHOTO_COMPARATOR) {

    interface OnItemClickListener {
        fun onItemClick(photo: UnsplashPhoto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UnsplashPhotoViewHolder {
        val binding =
            ItemUnspashPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UnsplashPhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UnsplashPhotoViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }


    inner class UnsplashPhotoViewHolder(private val binding: ItemUnspashPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val currentPhoto = getItem(position)
                    if (currentPhoto != null)
                        listener.onItemClick(currentPhoto)
                }

            }
        }

        fun bind(photo: UnsplashPhoto) {
            binding.apply {
                Glide.with(itemView)
                    .load(photo.urls.regular)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(imageView)
                textViewAuthor.text = photo.user.username
            }
        }
    }

    companion object {
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<UnsplashPhoto>() {
            override fun areItemsTheSame(oldItem: UnsplashPhoto, newItem: UnsplashPhoto): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: UnsplashPhoto,
                newItem: UnsplashPhoto
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}