package com.piappstudio.travelinsurance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.piappstudio.pilibrary.ui.slider.IntroInfo
import com.piappstudio.pilibrary.ui.slider.ScreenSlidePagerAdapter
import com.piappstudio.travelinsurance.databinding.FragmentWelcomeBinding

/**
 * create an instance of this fragment.
 */
class WelcomeFragment : Fragment() {
    val lstItem = mutableListOf<IntroInfo>()

    var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()

    }

    override fun onResume() {
        super.onResume()
        val supportActivity = activity as? AppCompatActivity
        supportActivity?.supportActionBar?.hide()
    }

    override fun onStop() {
        super.onStop()
        val supportActivity = activity as? AppCompatActivity
        supportActivity?.supportActionBar?.show()
    }
    fun initData() {
       lstItem.add(
           IntroInfo(R.drawable.ic_car, getString(R.string.offer_car_title),
           getString(R.string.offer_car_desc))
       )

        lstItem.add(
            IntroInfo(R.drawable.ic_motorcycle, getString(R.string.offer_bike_title),
           getString(R.string.offer_bike_desc))
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
       _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pagerAdapter = ScreenSlidePagerAdapter(this, lstItem)
        binding.vpIntro.adapter = pagerAdapter
        TabLayoutMediator(binding.intoTabLayout, binding.vpIntro) { _, _ -> }.attach()

        val btnSignIn = view.findViewById<Button>(R.id.btnSignIn)
        btnSignIn.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_loginFragment)
        }
        view.findViewById<Button>(R.id.btnRegister).setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_registrationFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}