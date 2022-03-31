package com.bitbiird.horoscopum.ui.view.detail

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.bitbiird.horoscopum.R
import com.bitbiird.horoscopum.data.model.HoroscopeResponse
import com.bitbiird.horoscopum.data.state.ResponseState
import com.bitbiird.horoscopum.databinding.ActivityHoroscopeDetailBinding
import com.bitbiird.horoscopum.ui.view.detail.viewpager.SignDataPagerAdapter
import com.bitbiird.horoscopum.ui.viewmodel.HoroscopeDetailViewModel
import com.bitbiird.horoscopum.utils.enums.HoroscopeSigns
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HoroscopeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHoroscopeDetailBinding

    private val horoscopeViewModel: HoroscopeDetailViewModel by viewModels()

    companion object {
        var HOROSCOPE_ITEM = "horoscopeSign"
        var DATA = "data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHoroscopeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getIntentData()
        initObservers()
        initListeners()
    }

    private fun getIntentData() {
        val horoscopeSign =
            intent.extras?.getBundle(DATA)?.getSerializable(HOROSCOPE_ITEM) as HoroscopeSigns
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
        horoscopeViewModel.horoscopeModel.observe(this) {
            when (it) {
                is ResponseState.Success -> {
                    val response = it.data as ArrayList<HoroscopeResponse>
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

        horoscopeViewModel.isLoading.observe(this) {
            binding.loadingView.setIsLoading(it)
        }

    }

    private fun showHoroscopeData(horoscopeResponseList: ArrayList<HoroscopeResponse>) {
        setUpViewPager(horoscopeResponseList)
        showUI()
    }

    private fun setUpViewPager(horoscopeDataResponseList: ArrayList<HoroscopeResponse>) {
        val adapter = SignDataPagerAdapter(this, horoscopeDataResponseList)
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
        binding.backButton.setOnClickListener { onBackPressed() }
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
            Snackbar.make(root, R.string.no_internet_data, BaseTransientBottomBar.LENGTH_SHORT)
                .setBackgroundTint(
                    ContextCompat.getColor(
                        root.context,
                        R.color.vineyard_autumn
                    )
                )
                .show()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right)
    }

}
