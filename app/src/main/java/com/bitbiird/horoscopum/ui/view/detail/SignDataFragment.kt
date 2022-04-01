package com.bitbiird.horoscopum.ui.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bitbiird.horoscopum.data.model.HoroscopeItem
import com.bitbiird.horoscopum.databinding.FragmentSignDataBinding

class SignDataFragment(private val horoscopeItem: HoroscopeItem) : Fragment() {

    private var _binding: FragmentSignDataBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView(horoscopeItem)
    }

    private fun setUpView(horoscopeItem: HoroscopeItem) {
        binding.apply {
            horoscopeItem.apply {
                currentDateText.text = currentDate
                moodText.text = mood
                luckyTimeText.text = luckyTime
                luckyNumberText.text = luckyNumber
                descriptionText.text = description
            }
        }
    }

}