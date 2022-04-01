package com.bitbiird.horoscopum.ui.view.detail.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bitbiird.horoscopum.data.model.HoroscopeItem
import com.bitbiird.horoscopum.ui.view.detail.SignDataFragment

class SignDataPagerAdapter(fragment: FragmentActivity, horoscopeItemList: ArrayList<HoroscopeItem>) :
    FragmentStateAdapter(fragment) {

    private val fragmentList: ArrayList<Fragment> = arrayListOf(
        SignDataFragment(horoscopeItemList[0]),
        SignDataFragment(horoscopeItemList[1]),
        SignDataFragment(horoscopeItemList[2])
    )

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position]

}