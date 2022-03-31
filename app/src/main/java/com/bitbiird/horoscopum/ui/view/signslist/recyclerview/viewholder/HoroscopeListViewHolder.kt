package com.bitbiird.horoscopum.ui.view.signslist.recyclerview.viewholder

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bitbiird.horoscopum.databinding.ViewholderHoroscopeSignListBinding
import com.bitbiird.horoscopum.utils.enums.HoroscopeSigns
import com.bitbiird.horoscopum.utils.interfaces.IOnSignSelected

class HoroscopeListViewHolder(private val binding: ViewholderHoroscopeSignListBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private val context: Context = binding.root.context

    fun render(horoscopeSign: HoroscopeSigns, listener: IOnSignSelected) {
        binding.apply {
            horoscopeName.text = context.getText(horoscopeSign.signName)
            horoscopeImage.setImageResource(horoscopeSign.signIcon)
            horoscopeDate.text = context.getText(horoscopeSign.signDate)

            root.setOnClickListener {
                listener.onSignSelected(horoscopeSign, binding.horoscopeImage)
            }
        }
    }
}