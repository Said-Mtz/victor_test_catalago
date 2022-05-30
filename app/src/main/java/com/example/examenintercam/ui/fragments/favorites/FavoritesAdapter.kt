package com.example.examenintercam.ui.fragments.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.examenintercam.databinding.ItemFavoriteBinding

class FavoritesAdapter :
    ListAdapter<FavoritesModel, FavoritesAdapter.FavoritesViewHolder>(DiffCallback) {

    lateinit var onItemClickListener: (FavoritesModel, Float) -> Unit

    companion object DiffCallback : DiffUtil.ItemCallback<FavoritesModel>() {

        override fun areItemsTheSame(oldItem: FavoritesModel, newItem: FavoritesModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FavoritesModel, newItem: FavoritesModel): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val mBinding =
            ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoritesViewHolder(mBinding)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val favorite = getItem(position)
        return holder.render(favorite)
    }

    inner class FavoritesViewHolder(private val mBinding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun render(itemFavorites: FavoritesModel) {
            mBinding.apply {
                with(itemFavorites) {
                    txtTitle.text = nameBeer
                    txtYeast.text = yeast
                    ratingBar.rating = itemFavorites.rateFromLocal.toFloat()
                    imageUrl = img
                    btnSave.setOnClickListener {
                        if (::onItemClickListener.isInitialized) {
                            onItemClickListener(this, ratingBar.rating)
                        }
                    }
                }
            }
        }
    }
}