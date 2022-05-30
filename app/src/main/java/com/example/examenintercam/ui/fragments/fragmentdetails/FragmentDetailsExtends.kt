package com.example.examenintercam.ui.fragments.fragmentdetails

fun FragmentDetails.initElements() {
    mBinding.apply {

        beerItem = viewModel.getSelectedModel()
        viewModel.getSelectedModel().apply {

            txtLiters.text = "${volume.value} Lts"

            txtAbvValue.text = "$abv %"
            txtIbuValue.text = "${ibu.toInt()}"
            txtOgValue.text = "${target_og.toInt()}"
            txtFgValue.text = "$target_fg"

            txtFirstBrewed.text = "First Brewed: $first_brewed"

            txtFoodPairing.text = food_pairing.joinToString(separator = "\n-", prefix = "-")

            txtHops.text = ingredients.hops.map {
                "${it.name} ${it.amount.value} g."
            }.joinToString(separator = "\n")

            txtMalt.text = ingredients.malt.map {
                "${it.name} ${it.amount.value} kg."
            }.joinToString(separator = "\n")
        }
    }
}