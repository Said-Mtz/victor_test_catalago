package com.example.examenintercam.ui.fragments.principal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.examenintercam.core.model.BeerRequestModelItem
import com.example.examenintercam.databinding.ItemBeerBinding
import com.squareup.picasso.Picasso

class BeerAdapter: ListAdapter<BeerRequestModelItem, BeerAdapter.BeerViewHolder>(DiffCallback) {

    lateinit var onItemClickListener: (BeerRequestModelItem) -> Unit

    companion object DiffCallback : DiffUtil.ItemCallback<BeerRequestModelItem>() {

        override fun areItemsTheSame(oldItem: BeerRequestModelItem, newItem: BeerRequestModelItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BeerRequestModelItem, newItem: BeerRequestModelItem): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        val mBinding = ItemBeerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BeerViewHolder(mBinding)
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        val beer = getItem(position)
        return holder.render(beer)
    }

    inner class BeerViewHolder(private val mBinding: ItemBeerBinding): RecyclerView.ViewHolder(mBinding.root) {
        fun render(itemBeer: BeerRequestModelItem){
            mBinding.apply {
                with(itemBeer){
                    Picasso.get().load(image_url).into(img)
                    txtNameBeer.text = name
                    txtTaglineBeer.text = tagline

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