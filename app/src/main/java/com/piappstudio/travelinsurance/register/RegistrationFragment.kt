package com.piappstudio.travelinsurance.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.piappstudio.pilibrary.ui.PIBaseActivity
import com.piappstudio.travelinsurance.R
import com.piappstudio.travelinsurance.common.TIApplication
import com.piappstudio.travelinsurance.databinding.FragmentRegistrationBinding
import com.piappstudio.pilibrary.utility.Resource
import com.piappstudio.travelinsurance.model.repository.TravelRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
@AndroidEntryPoint
class RegistrationFragment : Fragment() {

    private val registerViewModel:RegistrationViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val registerBinding = FragmentRegistrationBinding.inflate(inflater, container, false)
        registerBinding.registerModel = registerViewModel
        registerBinding.lifecycleOwner = this
        initUI(registerBinding)
        return registerBinding.root
    }

    private fun initUI(registrationBinding: FragmentRegistrationBinding) {
        registrationBinding.btnSignUp.setOnClickListener {
           onRegisterClick(registrationBinding.etConfirmPassword.text.toString())
        }

        registrationBinding.etConfirmPassword.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                onRegisterClick(v.text.toString())
                true
            } else false
        }

        registerViewModel.liveRegistrationFlow.observe(viewLifecycleOwner, Observer {
            when (it) {
                Resource.Status.LOADING-> {
                    (activity as PIBaseActivity).showProgressDialog("Registration")
                }
                Resource.Status.SUCCESS -> {
                    (activity as PIBaseActivity).dismissProgressDialog("Registration")
                    findNavController().navigate(R.id.action_registrationFragment_to_homeFragment)
                } else -> {
                    (activity as PIBaseActivity).dismissProgressDialog("Registration")
                }
            }

        })
    }

    private fun onRegisterClick(confirmPassword:String) {
        registerViewModel.validateForm(confirmPassword)
    }
}