package com.alura.customprogress

import android.animation.ObjectAnimator
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.ProgressBar

class ProgressBarAnimation : Animation() {
     var progressBar: ProgressBar? = null
     var from = 0f
     var to = 0f

    fun ProgressBarAnimation(
        progressBar: ProgressBar?,
        from: Float,
        to: Float
    ) {

        this.progressBar = progressBar
        this.from = from
        this.to = to
    }

    override fun applyTransformation(
        interpolatedTime: Float,
        t: Transformation?
    ) {
        super.applyTransformation(interpolatedTime, t)
        val value = from + (to - from) * interpolatedTime
        progressBar!!.progress = value.toInt()
    }
}