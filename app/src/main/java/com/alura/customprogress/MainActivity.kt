package com.alura.customprogress
import android.animation.Animator
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.LinearLayout.LayoutParams
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var index = 0
    private var count = 5

    var storiesResources = intArrayOf(
        R.drawable.sample1,
        R.drawable.sample2,
        R.drawable.sample3,
        R.drawable.sample1,
        R.drawable.sample2,
        R.drawable.sample3
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addProgresss(count)
    }

    private fun addProgresss(count:Int) {
        //Our list for each stories progress in the layout
        var storyList = ArrayList<ProgressBar>()

        //Add as many stories as you need in the layout, in this case we are using a LinearLayout as root
        for(i in 1..count step 1){
            val storyprogress = ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal)
            var params = LayoutParams(
                0,
               14,
                1.0f
            )
            params.setMargins(5,0,5,0)
            storyprogress.layoutParams = params
            storyprogress.progress = 0
            storyprogress.max = 100
            storyprogress.progressDrawable = resources.getDrawable(R.drawable.progressdrawable)
            //Add the new created progress into the layout
            storiesRoot.addView(storyprogress)
            //Add the reference for the progress for animation control
            storyList.add(storyprogress)
        }
        //Animate each individual
        animateStories(storyList)
    }

    private fun animateStories(storyList: ArrayList<ProgressBar>) {
        var animationTime = 15000L / count
        val animation = ObjectAnimator.ofInt(storyList.get(index), "progress", 0, storyList.get(index).max)
        animation.duration = animationTime
        animation.interpolator = AccelerateDecelerateInterpolator()
        animation.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animator: Animator) { }
            override fun onAnimationEnd(animator: Animator) {
                //Call next progress
                ++index
                if(index == storyList.size) return
                animateStories(storyList)
            }
            override fun onAnimationCancel(animator: Animator) {}
            override fun onAnimationRepeat(animator: Animator) {}
        })
        ivStories.setImageResource(storiesResources.get(index))
        animation.start()
    }
}









//TODO : ADD Fade in fade out
//TODO : Load the next step image before it is shown