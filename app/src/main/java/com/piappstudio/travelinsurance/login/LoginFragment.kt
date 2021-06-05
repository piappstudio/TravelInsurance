package com.piappstudio.travelinsurance.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.piappstudio.pilibrary.ui.PIBaseActivity
import com.piappstudio.pilibrary.utility.Resource
import com.piappstudio.travelinsurance.R
import com.piappstudio.travelinsurance.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class LoginFragment : Fragment() {

    val TAG = LoginFragment::class.java.name
    private val viewModel:LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentLogin = FragmentLoginBinding.inflate(inflater, container, false)
        fragmentLogin.viewModel = viewModel
        fragmentLogin.lifecycleOwner = this
        initUI(fragmentLogin)

        return fragmentLogin.root
    }

    private fun initUI(fragmentLogin: FragmentLoginBinding) {
        fragmentLogin.btnSignIn.setOnClickListener {
          viewModel.onClickLogin()
        }

        viewModel.liveLoginFlow.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "value ${it.name}")
            when(it) {
                Resource.Status.LOADING-> {
                    (activity as PIBaseActivity).showProgressDialog("Login")
                }
                Resource.Status.SUCCESS-> {
                    (activity as PIBaseActivity).dismissProgressDialog("Login")
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                } else -> {
                    (activity as PIBaseActivity).dismissProgressDialog("Login")
                }
            }
        })
    }
}