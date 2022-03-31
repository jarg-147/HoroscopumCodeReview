package com.bitbiird.horoscopum.ui.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bitbiird.horoscopum.R
import com.bitbiird.horoscopum.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initLogoAnimation()
    }

    private fun initListeners() {
        binding.apply {
            buttonReadHoroscope.setOnClickListener {
                onButtonReadHoroscopeClicked()
            }
        }
    }

    private fun onButtonReadHoroscopeClicked() {
        val action =
            HomeFragmentDirections
                .actionHomeFragmentToSignListFragment()
        findNavController().navigate(action)
    }

    private fun initLogoAnimation() {
        binding.apply {
            val rotation = AnimationUtils.loadAnimation(root.context, R.anim.rotate_slow)
            rotation.fillAfter = true
            homeLogo.startAnimation(rotation)
        }
    }

}