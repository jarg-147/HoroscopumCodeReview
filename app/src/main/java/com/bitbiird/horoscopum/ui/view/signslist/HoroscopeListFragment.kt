package com.bitbiird.horoscopum.ui.view.signslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bitbiird.horoscopum.databinding.FragmentHoroscopeListBinding
import com.bitbiird.horoscopum.ui.view.signslist.recyclerview.adapter.HoroscopeListAdapter
import com.bitbiird.horoscopum.utils.enums.HoroscopeSigns
import com.bitbiird.horoscopum.utils.interfaces.IOnSignSelected
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HoroscopeListFragment : Fragment(), IOnSignSelected {

    private var _binding: FragmentHoroscopeListBinding? = null
    private val binding get() = _binding!!

    private val adapter = HoroscopeListAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHoroscopeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initRecyclerView()
    }

    private fun initListeners() {
        binding.backButtonIcon.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun initRecyclerView() {
        binding.apply {
            horoscopeSignsRecycler.layoutManager = GridLayoutManager(root.context, 2)
            horoscopeSignsRecycler.adapter = adapter
        }
        updateRecyclerView(HoroscopeSigns.values())
    }

    private fun updateRecyclerView(horoscopeSign: Array<HoroscopeSigns>) {
        binding.apply {
            adapter.setItems(horoscopeSign)
        }
    }

    override fun onSignSelected(horoscopeSign: HoroscopeSigns) {
        val action =
            HoroscopeListFragmentDirections.actionSignListFragmentToSignDetailFragment(horoscopeSign)
        findNavController().navigate(action)
    }


}