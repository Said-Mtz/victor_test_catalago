package com.example.examenintercam.ui.fragments.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.examenintercam.R
import com.example.examenintercam.databinding.FragmentLoginBinding
import com.example.examenintercam.ui.StatusRequest
import com.example.examenintercam.utils.dialog.AlertDialogLoginObject
import com.example.examenintercam.utils.loading.LoadingDialogFragment
import com.example.examenintercam.utils.log
import com.example.examenintercam.viewmodel.MainViewModel


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    val mBinding get() = _binding!!
    val viewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initElements()
        viewModel.initDataBase(requireActivity())
        initObservers()
    }

    val loading = LoadingDialogFragment()
    private fun initObservers() {
        viewModel.statusLogin.observe(viewLifecycleOwner) {
            when (it.first) {
                StatusRequest.LOADING -> {
                    //Toast.makeText(requireContext(), "Cargando....", Toast.LENGTH_SHORT).show()
                    loading.show(requireActivity().supportFragmentManager, "Loading")
                }
                StatusRequest.SUCCESS -> {
                    findNavController().navigate(R.id.action_loginFragment_to_principalFragment)
                    "EXISTE".log("Login")
                    loading.dismiss()
                }
                StatusRequest.FAILURE -> {
                    loading.dismiss()
                    if (it.second != null) {
                        "NO EXISTE".log("Login")
                        val dialog = AlertDialogLoginObject
                        dialog.showDialog(requireActivity())
                        viewModel.resetStatusLogin()
                    } else {
                        "Algo salio mal".log("Login")
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        viewModel.resetStatusLogin()
    }
}