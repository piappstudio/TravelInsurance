package com.piappstudio.travelinsurance.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.piappstudio.travelinsurance.R
import com.piappstudio.travelinsurance.databinding.FragmentLoginBinding

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentLogin = FragmentLoginBinding.inflate(inflater, container, false)
        fragmentLogin.viewModel = viewModel
        fragmentLogin.lifecycleOwner = this
        initUI(fragmentLogin)

        return fragmentLogin.root
    }

    fun initUI(fragmentLogin: FragmentLoginBinding) {
        fragmentLogin.btnSignIn.setOnClickListener {
            if(viewModel.onClickLogin()) {
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }
        }
    }
}