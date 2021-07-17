package com.example.animations

import android.animation.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator
import android.view.animation.BounceInterpolator
import android.widget.ImageView
import androidx.fragment.app.Fragment


class ValueAnimatorFragment : Fragment() {

    private lateinit var image: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_value_animator, container, false)
        .apply(::initViews)

    private fun initViews(view: View) {
        with(view) {
            image = findViewById(R.id.myValueAnimatorImageView)
//            animateImage(image)
//            animatePropertyImage(image)
//            animateFromXml(image)
//            animateFromObjectAnimator(image)
//            animateFromObjectAnimatorFromXml(image)
            animateFromAnimate(image)
        }
    }

    private fun animateImage(imageView: ImageView) {
        ValueAnimator.ofFloat(0.5f, 1f)
            .apply {
                duration = 4000
                repeatMode = ValueAnimator.REVERSE
                repeatCount = ValueAnimator.INFINITE
                interpolator = BounceInterpolator()
                addUpdateListener { animator: ValueAnimator ->
                    val scale = animator.animatedValue as Float
                    imageView.scaleX = scale
                    imageView.scaleY = scale
                    imageView.translationY = scale * 200
                }
            }
            .start()
    }

    private fun animatePropertyImage(imageView: ImageView) {

        val alphaHolder: PropertyValuesHolder = PropertyValuesHolder.ofFloat("alpha1", 0f, 1f)
        val scaleHolder: PropertyValuesHolder = PropertyValuesHolder.ofFloat("scale1", 0.5f, 1f)
        val scale2Holder: PropertyValuesHolder = PropertyValuesHolder.ofFloat("scale12", 100f, 600f)

        ValueAnimator.ofPropertyValuesHolder(alphaHolder, scaleHolder, scale2Holder)
            .apply {
                duration = 4000
                repeatMode = ValueAnimator.REVERSE
                repeatCount = ValueAnimator.INFINITE
                interpolator = AnticipateOvershootInterpolator()
                addUpdateListener { animator: ValueAnimator ->
                    val alpha = animator.getAnimatedValue("alpha1") as Float
                    val scale = animator.getAnimatedValue("scale1") as Float
                    val scale2 = animator.getAnimatedValue("scale12") as Float
                    imageView.translationY = scale2
                    imageView.alpha = alpha
                    imageView.scaleX = scale
                    imageView.scaleY = scale
                }
            }
            .start()
    }

    private fun animateFromXml(imageView: ImageView) {
        val animatorXml: ValueAnimator = AnimatorInflater.loadAnimator(
            requireContext(),
            R.animator.value_animator_example
        ) as ValueAnimator

        animatorXml.addUpdateListener { animator: ValueAnimator ->
            imageView.alpha = animator.animatedValue as Float
        }
        animatorXml.start()
    }

    private fun animateFromObjectAnimator(imageView: ImageView) {
        // rotation animator
        val rotationAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f)
            .apply {
                repeatCount = ObjectAnimator.INFINITE
                repeatMode = ObjectAnimator.REVERSE
                duration = 1000
            }
            .start()

        // translation animator
        val translationAnimator = ObjectAnimator.ofFloat(imageView, "alpha", 0f, 1f)
            .apply {
                repeatCount = ObjectAnimator.INFINITE
                repeatMode = ObjectAnimator.REVERSE
                duration = 1000
            }
            .start()
    }

    private fun animateFromObjectAnimatorFromXml(imageView: ImageView) {
        val animator =
            AnimatorInflater.loadAnimator(requireContext(), R.animator.custom_view_animator)
        animator.setTarget(imageView)
        animator.start()
    }

    private fun animateFromAnimate(imageView: ImageView) {
        imageView.animate()
            .setDuration(2000)
            .scaleX(0.5f)
            .scaleY(0.5f)
            .x(400f)
            .setListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator?) {
                }

                override fun onAnimationEnd(animation: Animator?) {

                }

                override fun onAnimationCancel(animation: Animator?) {
                }

                override fun onAnimationRepeat(animation: Animator?) {
                }

            })

    }
}

