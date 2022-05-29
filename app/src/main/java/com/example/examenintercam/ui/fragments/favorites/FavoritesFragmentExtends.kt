package com.example.examenintercam.ui.fragments.favorites

import android.widget.Toast

fun FavoritesFragment.initElements(){
    mBinding.apply {

        val listFavorite = arrayListOf(
            FavoritesModel(1,"Corona","La cerveza de mexico", ""),
            FavoritesModel(2,"Corona","La cerveza de mexico", ""),
            FavoritesModel(3,"Corona","La cerveza de mexico", ""),
            FavoritesModel(4,"Corona","La cerveza de mexico", ""),
            FavoritesModel(5,"Corona","La cerveza de mexico", ""),
            FavoritesModel(6,"Corona","La cerveza de mexico", ""),
            FavoritesModel(7,"Corona","La cerveza de mexico", ""),
            FavoritesModel(8,"Corona","La cerveza de mexico", ""),
            FavoritesModel(9,"Corona","La cerveza de mexico", ""),
            FavoritesModel(10,"Corona","La cerveza de mexico", ""),
        )

        initRecycler(listFavorite)
    }
}

private fun FavoritesFragment.initRecycler(list: ArrayList<FavoritesModel>){
    mBinding.recyclerView.adapter = myAdapter
    myAdapter.submitList(list)
    myAdapter.onItemClickListener = {
        Toast.makeText(requireActivity(), "press", Toast.LENGTH_SHORT).show()
    }
}