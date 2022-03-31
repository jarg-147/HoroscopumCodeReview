package com.bitbiird.horoscopum.ui.view.signslist.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bitbiird.horoscopum.databinding.ViewholderHoroscopeSignListBinding
import com.bitbiird.horoscopum.ui.view.signslist.recyclerview.viewholder.HoroscopeListViewHolder
import com.bitbiird.horoscopum.utils.enums.HoroscopeSigns
import com.bitbiird.horoscopum.utils.interfaces.IOnSignSelected

class HoroscopeListAdapter(private val listener: IOnSignSelected) :
    RecyclerView.Adapter<HoroscopeListViewHolder>() {

    private var signs: ArrayList<HoroscopeSigns> = arrayListOf()

    override fun getItemCount(): Int = signs.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoroscopeListViewHolder {
        return HoroscopeListViewHolder(
            ViewholderHoroscopeSignListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HoroscopeListViewHolder, position: Int) {
        holder.render(signs[position], listener)
    }

    fun setItems(newItems: Array<HoroscopeSigns>) {
        signs.clear()
        signs.addAll(newItems)
        notifyDataSetChanged()
    }

}