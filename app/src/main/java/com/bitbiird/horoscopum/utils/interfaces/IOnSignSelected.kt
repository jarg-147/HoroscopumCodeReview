package com.bitbiird.horoscopum.utils.interfaces

import android.widget.ImageView
import com.bitbiird.horoscopum.utils.enums.HoroscopeSigns

interface IOnSignSelected {
    fun onSignSelected(horoscopeSign: HoroscopeSigns, signImage: ImageView) {}
}