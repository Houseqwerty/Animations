package com.example.animations

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.*
import android.widget.ImageView
import androidx.fragment.app.Fragment


class TweenFragment : Fragment() {

    private lateinit var image: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_tween, container, false)
        .apply(::initViews)

    private fun initViews(view: View) {
        with(view) {
            image = findViewById(R.id.myTweenImageView)
//            val hyperspaceJumpAnimation: Animation =
//                AnimationUtils.loadAnimation(requireContext(), R.anim.hyperspace_jump)
//            image.startAnimation(hyperspaceJumpAnimation)

            animateProgrammatically2()
        }
    }

    private fun animateProgrammatically2() {
        // create scale animation
        val scaleAnimation = ScaleAnimation(0.5f, 1f, 0.5f, 1f, 0.5f, 0.5f)
        scaleAnimation.repeatMode = Animation.REVERSE
        scaleAnimation.repeatCount = 10
        scaleAnimation.duration = 5000

        // create alpha animation
        val alphaAnimation = AlphaAnimation(0f, 1f)
        alphaAnimation.repeatMode = Animation.REVERSE
        alphaAnimation.repeatCount = 10
        alphaAnimation.duration = 5000

        // add animations to AnimationSet
        val animations = AnimationSet(false)
        animations.addAnimation(scaleAnimation)
        animations.addAnimation(alphaAnimation)

        image.startAnimation(animations)
    }


}

