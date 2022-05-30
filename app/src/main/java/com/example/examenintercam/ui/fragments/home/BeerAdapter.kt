package com.example.examenintercam.ui.fragments.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.examenintercam.R
import com.example.examenintercam.core.model.BeerRequestModelItem
import com.example.examenintercam.databinding.ItemBeerBinding
import com.example.examenintercam.ui.fragments.favorites.FavoritesModel
import com.squareup.picasso.Picasso

class BeerAdapter : ListAdapter<BeerRequestModelItem, BeerAdapter.BeerViewHolder>(DiffCallback) {

    lateinit var onItemClickListener: (BeerRequestModelItem) -> Unit
    lateinit var onFavoriteClickListener: (BeerRequestModelItem) -> Unit
    private var localFavorite = listOf<FavoritesModel>()

    companion object DiffCallback : DiffUtil.ItemCallback<BeerRequestModelItem>() {

        override fun areItemsTheSame(
            oldItem: BeerRequestModelItem,
            newItem: BeerRequestModelItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: BeerRequestModelItem,
            newItem: BeerRequestModelItem
        ): Boolean {
            return oldItem == newItem
        }

    }

    fun setDataAndFavoriteList(dataList: ArrayList<BeerRequestModelItem>, listFavorite: List<FavoritesModel>) {
        this.localFavorite = listFavorite
        submitList(dataList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        val mBinding = ItemBeerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BeerViewHolder(mBinding)
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        val beer = getItem(position)
        return holder.render(beer)
    }

    inner class BeerViewHolder(private val mBinding: ItemBeerBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun render(itemBeer: BeerRequestModelItem) {
            mBinding.apply {
                with(itemBeer) {
                    imageUrl = image_url
                    txtNameBeer.text = name
                    txtTaglineBeer.text = tagline
                    if(localFavorite.find { it.nameBeer == name } != null){
                        imgFavorite.setColorFilter(R.color.purple_700)
                    }else{
                        imgFavorite.setColorFilter(R.color.black)
                    }
                    imgFavorite.setOnClickListener {
                        imgFavorite.setColorFilter(R.color.purple_700)
                        if (::onFavoriteClickListener.isInitialized) {
                            onFavoriteClickListener(this)
                        }
                    }

                    root.setOnClickListener {
                        if (::onItemClickListener.isInitialized) {
                            onItemClickListener(this)
                        }
                    }
                }
            }
        }
    }
}