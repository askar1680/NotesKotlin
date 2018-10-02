package com.ulunayev.askar.noteskotlin.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ulunayev.askar.noteskotlin.R
import android.os.Build
import android.view.View
import com.ulunayev.askar.noteskotlin.utils.RevealCircleAnimation


class NewNoteActivity : AppCompatActivity() {
  companion object {
    val EXTRA_CIRCULAR_REVEAL_X = "EXTRA_CIRCULAR_REVEAL_X"
    val EXTRA_CIRCULAR_REVEAL_Y = "EXTRA_CIRCULAR_REVEAL_Y"
  }

  private var revealX: Int = 0
  private var revealY: Int = 0

  private lateinit var revealCircleAnimation: RevealCircleAnimation

  var rootLayout: View? = null
  override fun onBackPressed() {
    super.onBackPressed()
    revealCircleAnimation.unRevealActivity { finish() }
  }


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_new_note)

    getExtrasFromIntent(savedInstanceState)
    setupRevealCircleAnimation()
  }

  fun getExtrasFromIntent(savedInstanceState: Bundle?){
    if (savedInstanceState == null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
      intent.hasExtra(EXTRA_CIRCULAR_REVEAL_X) &&
      intent.hasExtra(EXTRA_CIRCULAR_REVEAL_Y)) {

      revealX = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_X, 0)
      revealY = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_Y, 0)

    }
  }

  fun setupRevealCircleAnimation(){
    rootLayout = findViewById(R.id.root_layout)
    revealCircleAnimation = RevealCircleAnimation(rootLayout, revealX, revealY)

    val viewTreeObserver = rootLayout?.viewTreeObserver
    viewTreeObserver?.let { viewTreeObserver ->
      if (viewTreeObserver!!.isAlive) {
        viewTreeObserver.addOnGlobalLayoutListener {
          revealCircleAnimation.revealActivity { finish() }
        }
      }
    }
  }


}