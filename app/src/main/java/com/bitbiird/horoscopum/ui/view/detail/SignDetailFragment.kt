package com.bitbiird.horoscopum.ui.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bitbiird.horoscopum.R
import com.bitbiird.horoscopum.data.model.HoroscopeItem
import com.bitbiird.horoscopum.data.state.ResponseState
import com.bitbiird.horoscopum.databinding.FragmentSignDetailBinding
import com.bitbiird.horoscopum.ui.view.detail.viewpager.SignDataPagerAdapter
import com.bitbiird.horoscopum.ui.viewmodel.HoroscopeDetailViewModel
import com.bitbiird.horoscopum.utils.enums.HoroscopeSigns
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignDetailFragment : Fragment() {

    private var _binding: FragmentSignDetailBinding? = null
    private val binding get() = _binding!!

    private val horoscopeViewModel: HoroscopeDetailViewModel by viewModels()

    private val args: SignDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getIntentData()
        initObservers()
        initListeners()
    }

    private fun getIntentData() {
        val horoscopeSign = args.horoscopeSign
        setUpView(horoscopeSign)
        getHoroscopeData(getString(horoscopeSign.signName))
    }

    private fun setUpView(horoscopeSign: HoroscopeSigns) {
        binding.apply {
            detailSignName.text = getString(horoscopeSign.signName)
            signIcon.setImageResource(horoscopeSign.signIcon)
        }
    }

    private fun getHoroscopeData(horoscopeSign: String) {
        horoscopeViewModel.onCreate(horoscopeSign.lowercase())
    }

    private fun initObservers() {
        horoscopeViewModel.horoscopeModel.observe(viewLifecycleOwner) {
            when (it) {
                is ResponseState.Success -> {
                    val response = it.data as ArrayList<HoroscopeItem>
                    if (response.isNotEmpty() && response.size == 3) {
                        showHoroscopeData(response)
                    } else {
                        showErrorView()
                    }
                }
                is ResponseState.Error -> showErrorView()
                is ResponseState.NoInternet -> {
                    showErrorView()
                    showSnackBarInternetError()
                }
            }
        }

        horoscopeViewModel.isLoading.observe(viewLifecycleOwner) {
            binding.loadingView.setIsLoading(it)
        }

    }

    private fun showHoroscopeData(horoscopeItemList: ArrayList<HoroscopeItem>) {
        setUpViewPager(horoscopeItemList)
        showUI()
    }

    private fun setUpViewPager(horoscopeDataItemList: ArrayList<HoroscopeItem>) {
        val adapter = SignDataPagerAdapter(requireActivity(), horoscopeDataItemList)
        binding.apply {
            viewpager.adapter = adapter
            TabLayoutMediator(daysTablayout, viewpager) { tab, position ->
                val tabTitles = listOf(
                    getString(R.string.yesterday),
                    getString(R.string.today),
                    getString(R.string.tomorrow)
                )
                tab.text = tabTitles[position]
            }.attach()
            daysTablayout.selectTab(daysTablayout.getTabAt(1), true)
            viewpager.setCurrentItem(daysTablayout.selectedTabPosition, false)
        }
        showViewPagerElements()
    }

    private fun showViewPagerElements() {
        binding.apply {
            viewpager.isVisible = true
            daysTablayout.isVisible = true
        }
    }

    private fun initListeners() {
        binding.backButton.setOnClickListener { activity?.onBackPressed() }
    }

    private fun showUI() {
        binding.topContainer.isVisible = true
        binding.dataContainer.isVisible = true
        startSignIconAnimation()
    }

    private fun showErrorView() {
        binding.apply {
            binding.topContainer.isVisible = true
            errorView.root.visibility = View.VISIBLE
        }
    }

    private fun startSignIconAnimation() {
        binding.apply {
            signIcon.transitionName = getString(R.string.sign_icon_animation)
        }
    }

    private fun showSnackBarInternetError() {
        binding.apply {
            Snackbar.make(root, R.string.no_internet_data, Snackbar.LENGTH_SHORT)
                .setBackgroundTint(
                    ContextCompat.getColor(
                        root.context,
                        R.color.vineyard_autumn
                    )
                )
                .show()
        }
    }

}