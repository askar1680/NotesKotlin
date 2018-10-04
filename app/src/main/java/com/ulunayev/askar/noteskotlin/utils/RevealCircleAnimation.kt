package com.ulunayev.askar.noteskotlin.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Build
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateInterpolator

class RevealCircleAnimation(val rootLayout: View?, val revealX: Int, val revealY: Int){


  fun revealActivity(onFinished: () -> (Unit)) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      rootLayout?.let {rootLayout ->
        val finalRadius = (Math.max(rootLayout.getWidth(), rootLayout.getHeight()) * 1.1).toFloat()

        val circularReveal = ViewAnimationUtils.createCircularReveal(rootLayout, revealX, revealY, 0f, finalRadius)
        circularReveal.duration = 400
        circularReveal.interpolator = AccelerateInterpolator()

        val animOfRootLayout = rootLayout.animate().alpha(0f)
        animOfRootLayout.duration = 400

        rootLayout?.setVisibility(View.VISIBLE)
        circularReveal.start()

        circularReveal.addListener( object : AnimatorListenerAdapter() {
          override fun onAnimationEnd(animation: Animator?) {
            super.onAnimationEnd(animation)
            animOfRootLayout.start()
          }
        })
      }
    }
    else {
      onFinished()
    }
  }

  fun unRevealActivity(onFinished: () -> (Unit)) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
      onFinished()
    } else {
      rootLayout?.let { rootLayout ->
        val finalRadius = (Math.max(rootLayout.width, rootLayout.height) * 1.1).toFloat()
        rootLayout.alpha = 1f
        val circularReveal = ViewAnimationUtils.createCircularReveal(rootLayout, revealX, revealY, finalRadius, 0f)
        circularReveal.duration = 400



        circularReveal.start()
        circularReveal.addListener(object : AnimatorListenerAdapter() {
          override fun onAnimationEnd(animation: Animator) {
            onFinished()
          }
        })
      }
    }
  }
}