package com.ams.cspapplication.ui.homepage

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ams.cspapplication.R
import com.ams.cspapplication.ui.enrollment.EnrollmentActivity
import android.widget.Button
import android.media.MediaPlayer



class HomepageActivity : AppCompatActivity() {
    private val continueButton by lazy { findViewById<Button>(R.id.continueButton) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)
        val mediaPlayer = MediaPlayer.create(this, R.raw.button_click)
        mediaPlayer.setVolume(1.0f, 1.0f)
        continueButton.setOnClickListener{
            mediaPlayer.start()
            navigateToEnrollment()
        }
    }

    private fun navigateToEnrollment() {
        val intent = Intent(this, EnrollmentActivity::class.java)
        startActivity(intent)
        finish()
    }
}
