package com.bitbiird.horoscopum.ui.view.detail.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bitbiird.horoscopum.data.model.HoroscopeResponse
import com.bitbiird.horoscopum.ui.view.detail.SignDataFragment

class SignDataPagerAdapter(fragment: FragmentActivity, horoscopeResponseList: ArrayList<HoroscopeResponse>) :
    FragmentStateAdapter(fragment) {

    private val fragmentList: ArrayList<Fragment> = arrayListOf(
        SignDataFragment(horoscopeResponseList[0]),
        SignDataFragment(horoscopeResponseList[1]),
        SignDataFragment(horoscopeResponseList[2])
    )

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position]

}