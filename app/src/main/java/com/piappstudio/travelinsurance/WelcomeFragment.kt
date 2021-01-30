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
import com.piappstudio.travelinsurance.slider.IntroInfo
import com.piappstudio.travelinsurance.slider.ScreenSlidePagerAdapter
import kotlinx.android.synthetic.main.fragment_welcome.*

/**
 * A simple [Fragment] subclass.
 * Use the [WelcomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WelcomeFragment : Fragment() {
    val lstItem = mutableListOf<IntroInfo>()
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
       lstItem.add(IntroInfo(R.drawable.ic_car, getString(R.string.offer_car_title),
           getString(R.string.offer_car_desc)))

        lstItem.add(IntroInfo(R.drawable.ic_motorcycle, getString(R.string.offer_bike_title),
           getString(R.string.offer_bike_desc)))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pagerAdapter = ScreenSlidePagerAdapter(this, lstItem)
        vpIntro.adapter = pagerAdapter
        TabLayoutMediator(into_tab_layout, vpIntro) { tab, position -> }.attach()

        val btnSignIn = view.findViewById<Button>(R.id.btnSignIn)
        btnSignIn.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_loginFragment)
        }
        view.findViewById<Button>(R.id.btnRegister).setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_registrationFragment)
        }
    }
}