package com.ams.cspapplication.ui.enrollment

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.ams.cspapplication.R
import com.ams.cspapplication.ui.simulator.SimulatorActivity

class EnrollmentActivity : AppCompatActivity() {
    private lateinit var titleTextView: TextView
    private lateinit var introTextView: TextView
    private lateinit var optionGroup: RadioGroup
    private lateinit var option1: RadioButton
    private lateinit var option2: RadioButton
    private lateinit var option3: RadioButton
    private lateinit var option4: RadioButton
    private lateinit var continueButton: Button

    private var selectedOption = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enrollment)

        titleTextView = findViewById(R.id.textView)
        introTextView = findViewById(R.id.app_name)
        optionGroup = findViewById(R.id.optionGrid)
        option1 = findViewById(R.id.option1)
        option2 = findViewById(R.id.option2)
        option3 = findViewById(R.id.option3)
        option4 = findViewById(R.id.option4)
        continueButton = findViewById(R.id.continueButton)

        selectTemplate()

        // 设置按钮点击事件的监听器
        continueButton.setOnClickListener {
            if (selectedOption == -1) {
                Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show()
            } else {
                navigateToSimulator()
            }
        }
    }

    private fun selectTemplate() {
        // 设置选项部分的监听器
        optionGroup.setOnCheckedChangeListener { _, checkedId ->
            // 获取用户选择的选项
            selectedOption = when (checkedId) {
                R.id.option1 -> 1
                R.id.option2 -> 2
                R.id.option3 -> 3
                R.id.option4 -> 4
                else -> -1
            }
        }
    }
    private fun navigateToSimulator() {
        val intent = Intent(this, SimulatorActivity::class.java)
        intent.putExtra("template", selectedOption)
        startActivity(intent)
        finish()
    }
}
