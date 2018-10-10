package com.ulunayev.askar.noteskotlin.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ulunayev.askar.noteskotlin.R
import android.os.Build
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.ulunayev.askar.noteskotlin.dialogs.ColorDialog
import com.ulunayev.askar.noteskotlin.dialogs.ReminderDialog
import com.ulunayev.askar.noteskotlin.models.Color
import com.ulunayev.askar.noteskotlin.utils.RevealCircleAnimation
import kotlinx.android.synthetic.main.activity_new_note.*


class NewNoteActivity : AppCompatActivity(), ColorDialog.ColorDialogListener {
  override fun colorClicked(color: Color?) {
    color?.let {
      backgroundView.setBackgroundColor(ContextCompat.getColor(this, it.color))
      titleEditText.setTextColor(ContextCompat.getColor(this, it.textColor))
      noteEditText.setTextColor(ContextCompat.getColor(this, it.textColor))
    }
  }

  companion object {
    val EXTRA_CIRCULAR_REVEAL_X = "EXTRA_CIRCULAR_REVEAL_X"
    val EXTRA_CIRCULAR_REVEAL_Y = "EXTRA_CIRCULAR_REVEAL_Y"
  }

  private var revealX: Int = 0
  private var revealY: Int = 0


  private lateinit var revealCircleAnimation: RevealCircleAnimation

  override fun onBackPressed() {
    super.onBackPressed()
    if (revealX != 0 && revealY != 0)
      revealCircleAnimation.unRevealActivity { finish() }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_new_note)

    setSupportActionBar(toolbar)
    supportActionBar?.let { it.setDisplayHomeAsUpEnabled(true) }

    getExtrasFromIntent(savedInstanceState)
    setupRevealCircleAnimation()
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_new_note, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId){
      android.R.id.home -> {
        finish()
      }
      R.id.time_picker -> {
        val reminderDialog = ReminderDialog(this)
        reminderDialog.showDialog {

        }
      }
      R.id.color_picker -> {
        val colorDialog = ColorDialog(this)
        colorDialog.colorDialogListener = this
        colorDialog.showDialog()
      }
      R.id.share -> {

      }
      R.id.delete -> {
        finish()
      }
    }
    return super.onOptionsItemSelected(item)
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
    if (revealX != 0 && revealY != 0) {
      rootLayout.visibility = View.VISIBLE
      revealCircleAnimation = RevealCircleAnimation(rootLayout, revealX, revealY)

      val viewTreeObserver = rootLayout?.viewTreeObserver
      viewTreeObserver?.let { viewTreeObserver ->
        if (viewTreeObserver.isAlive) {
          viewTreeObserver.addOnGlobalLayoutListener {
            revealCircleAnimation.revealActivity { finish() }
          }
        }
      }
    }
  }
}
