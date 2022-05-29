package com.example.examenintercam.ui.fragments.principal

import android.view.View
import com.example.examenintercam.core.api.ApiBeerService
import com.example.examenintercam.core.api.RetrofitService
import com.example.examenintercam.core.model.BeerRequestModel
import com.example.examenintercam.ui.fragments.dialogfragmentdetails.DialogFragmentDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

fun PrincipalFragment.initElements() {
    mBinding.apply {
        val retrofit: Retrofit = RetrofitService.instance()
        apiService = retrofit.create(ApiBeerService::class.java)
        initRecycler()
    }
}

fun PrincipalFragment.initRecycler() {
    apiService.getBeer().enqueue(object : Callback<BeerRequestModel> {
        override fun onResponse(
            call: Call<BeerRequestModel>,
            response: Response<BeerRequestModel>
        ) {
            val body = response.body()
            if (body != null) {
                mBinding.recyclerView.adapter = myAdapter
                myAdapter.submitList(body)
                mBinding.shimmer.visibility = View.GONE
                mBinding.recyclerView.visibility = View.VISIBLE
            }
        }

        override fun onFailure(call: Call<BeerRequestModel>, t: Throwable) {

        }
    })
    myAdapter.onItemClickListener = { itemList ->
    /**Mostrando dialog fragment generico**/
    /*DialogGenericFragment<DialogDetailsBinding>(R.layout.dialog_details){ mBinding, dialogFragment ->
            mBinding.apply {
                imgBeer.setOnClickListener {
                    dialogFragment.dismissAllowingStateLoss()
                }

            }
        }.show(requireActivity().supportFragmentManager,"iodjiwedo")*/

        /**show my dialog fragment**/
        val dialog = DialogFragmentDetails()
        dialog.show(requireActivity().supportFragmentManager, "myDialogFragment")

        //Toast.makeText(requireActivity(), "Press $itemList", Toast.LENGTH_SHORT).show()
    }
}