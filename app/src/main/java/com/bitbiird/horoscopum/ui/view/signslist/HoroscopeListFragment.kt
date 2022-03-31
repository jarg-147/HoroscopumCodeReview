package com.bitbiird.horoscopum.ui.view.signslist

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.bitbiird.horoscopum.R
import com.bitbiird.horoscopum.databinding.FragmentHoroscopeListBinding
import com.bitbiird.horoscopum.ui.view.detail.HoroscopeDetailActivity
import com.bitbiird.horoscopum.ui.view.signslist.recyclerview.adapter.HoroscopeListAdapter
import com.bitbiird.horoscopum.utils.enums.HoroscopeSigns
import com.bitbiird.horoscopum.utils.interfaces.IOnSignSelected
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HoroscopeListFragment : Fragment(), IOnSignSelected {

    private var _binding: FragmentHoroscopeListBinding? = null
    private val binding get() = _binding!!

    private val adapter = HoroscopeListAdapter(this)

    companion object {
        var HOROSCOPE_ITEM = "horoscopeSign"
        var DATA = "data"
    }

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

    override fun onSignSelected(horoscopeSign: HoroscopeSigns, signImage: ImageView) {
        goToDetail(horoscopeSign, signImage)
    }

    private fun goToDetail(horoscopeSign: HoroscopeSigns, signImage: ImageView) {
        binding.apply {
            val bundle = Bundle()
            bundle.putSerializable(HOROSCOPE_ITEM, horoscopeSign)

            val intent = Intent(context, HoroscopeDetailActivity::class.java).putExtra(
                DATA, bundle
            )
            val options = Bundle()

            val slideTransition = ActivityOptions.makeCustomAnimation(
                activity,
                R.anim.enter_from_right,
                R.anim.exit_to_left
            )

            val pairs: Array<Pair<View, String>> = arrayOf(
                Pair.create(
                    signImage as View,
                    resources.getString(R.string.sign_icon_animation)
                )
            )

            val iconTransition = ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity as Activity,
                *pairs
            )

            options.putAll(slideTransition.toBundle())
            options.putAll(iconTransition.toBundle())
            startActivity(intent, options)
        }
    }


}