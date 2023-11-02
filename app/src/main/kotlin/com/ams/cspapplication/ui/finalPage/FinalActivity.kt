package com.ams.cspapplication.ui.finalPage

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.ams.cspapplication.R
import com.ams.cspapplication.ui.homepage.HomepageActivity
import kotlin.random.Random

class FinalActivity : ComponentActivity() {
    private lateinit var restartButton: Button
    private lateinit var resultTextView: TextView
    private lateinit var university: String
    private var score: Double = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final) // 设置布局文件
        resultTextView = findViewById(R.id.result_text)
        score = intent.getDoubleExtra("score", 0.0)
        university = chooseUniversity(score)
        val resultText = getString(R.string.final_statement) + " " + university
        resultTextView.text = resultText
        restartButton = findViewById(R.id.restartButton)

        restartButton.setOnClickListener {
            navigateToHomepage()
        }
    }

    fun chooseUniversity(score: Double): String {
        return when {
            score > 120 -> chooseRandomUniversity(listOf("Harvard, MIT, Stanford, Oxford, Johns Hopkins, Cambridge, Princeton, Cornell"))
            score > 100 -> chooseRandomUniversity(listOf("Duke, UC Berkeley, UCLA, UCL, CIT"))
            score > 80 -> chooseRandomUniversity(listOf("Northwest University, Rice University , The University of Notre Dame du Lac, Brown University"))
            score > 60 -> chooseRandomUniversity(listOf("UIUC", "Case Western Reserve University", "Northeastern University", "University of Rochester", "OSU"))
            else -> "No university"
        }
    }

    private fun chooseRandomUniversity(universities: List<String>): String {
        val randomIndex = Random.nextInt(universities.size)
        return universities[randomIndex]
    }

    private fun navigateToHomepage() {
        val intent = Intent(this, HomepageActivity::class.java)
        startActivity(intent)
        finish()
    }
}
