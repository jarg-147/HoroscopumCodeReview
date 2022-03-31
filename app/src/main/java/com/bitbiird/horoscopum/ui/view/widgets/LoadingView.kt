package com.bitbiird.horoscopum.ui.view.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.AnimationUtils
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.bitbiird.horoscopum.R
import com.bitbiird.horoscopum.databinding.LoadingViewBinding

class LoadingView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    private val binding = LoadingViewBinding.inflate(LayoutInflater.from(context), this, true)

    private var loading = false

    init {
        initMoonLoadingAnimation()
    }

    private fun initMoonLoadingAnimation() {
        binding.apply {
            val rotation = AnimationUtils.loadAnimation(root.context, R.anim.rotate_fast)
            rotation.fillAfter = true
            loadingIcon.startAnimation(rotation)
        }
    }

    fun isLoading(): Boolean {
        return loading
    }

    fun setIsLoading(isLoading: Boolean) {
        loading = isLoading
        this.isVisible = isLoading
    }
}