package com.example.examenintercam.ui.fragments.favorites

fun FavoritesFragment.initElements() {
    mBinding.apply {
        viewModel.getFavorite()
    }
}

fun FavoritesFragment.initRecycler(list: List<FavoritesModel>?) {
    mBinding.recyclerView.adapter = myAdapter
    myAdapter.submitList(list)
    myAdapter.onItemClickListener = { item, rate ->
        viewModel.saveFavorite(item, rate.toDouble()){
            myAdapter.submitList(list)
        }
    }
}