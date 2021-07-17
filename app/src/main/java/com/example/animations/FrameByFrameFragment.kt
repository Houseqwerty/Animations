package com.example.animations

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment


class FrameByFrameFragment : Fragment() {

    private lateinit var frameAnimation: AnimationDrawable

    private lateinit var image: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_frame_by_frame, container, false)
        .apply(::initViews)

    private fun initViews(view: View) {
        with(view) {
            image = findViewById(R.id.myFrameByFrameImageView)
            frameAnimation = image.drawable as AnimationDrawable

            findViewById<View>(R.id.btnStartStop).setOnClickListener {
                frameAnimation.apply { if (isRunning) stop() else start() }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        frameAnimation.start()
    }

    override fun onStop() {
        super.onStop()
        frameAnimation.stop()
    }

}

