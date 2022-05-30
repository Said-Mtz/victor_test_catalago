package com.example.examenintercam.ui.fragments.home

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examenintercam.R
import com.example.examenintercam.utils.log


fun HomeFragment.initElements() {
    mBinding.apply {
        initRecycler()
    }
}

fun HomeFragment.initRecycler() {

    mBinding.recyclerView.adapter = beerAdapter
    viewModel.requestBeerData()

    mBinding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val totalItemCount = layoutManager.itemCount
            val lastVisible = layoutManager.findLastCompletelyVisibleItemPosition()

            if (totalItemCount > 0 && lastVisible == totalItemCount -1) {
                lastVisible.toString().log("LASTITEM")
                viewModel.requestBeerData()
            }
        }
    })

    beerAdapter.onFavoriteClickListener = { beerItemSelected ->
        viewModel.saveFavorite(beerItemSelected)
    }

    beerAdapter.onItemClickListener = { itemList ->
        viewModel.setSelectedModel(itemList)
        findNavController().navigate(R.id.action_principalFragment_to_fragmentDetails)
    }

}