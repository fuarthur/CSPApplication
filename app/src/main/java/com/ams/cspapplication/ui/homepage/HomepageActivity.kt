package com.ams.cspapplication.ui.homepage

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ams.cspapplication.R
import com.ams.cspapplication.ui.enrollment.EnrollmentActivity
import android.widget.Button


class HomepageActivity : AppCompatActivity() {
    private val continueButton by lazy { findViewById<Button>(R.id.continueButton) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)
        continueButton.setOnClickListener{
            navigateToEnrollment()
        }
    }

    private fun navigateToEnrollment() {
        val intent = Intent(this, EnrollmentActivity::class.java)
        startActivity(intent)
        finish()
    }
}
