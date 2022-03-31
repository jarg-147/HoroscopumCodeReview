package com.bitbiird.horoscopum.utils.interfaces

import com.bitbiird.horoscopum.utils.enums.HoroscopeSigns

interface IOnCompatibilitySignSelected {
    fun onSignsSelected(selectedSignsList: ArrayList<HoroscopeSigns>) {}
}