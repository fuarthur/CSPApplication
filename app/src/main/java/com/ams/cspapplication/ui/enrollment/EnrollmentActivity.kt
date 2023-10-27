package com.ams.cspapplication.ui.enrollment

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.ams.cspapplication.R
class EnrollmentActivity : AppCompatActivity() {
    private lateinit var titleTextView: TextView
    private lateinit var introTextView: TextView
    private lateinit var optionGroup: RadioGroup
    private lateinit var option1: RadioButton
    private lateinit var option2: RadioButton
    private lateinit var option3: RadioButton
    private lateinit var option4: RadioButton
    private lateinit var continueButton: Button

    private var selectedOption = ""

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

        // 设置标题文本
        titleTextView.text = getString(R.string.title_game_enrollment)

        // 设置中间内容部分文本
        introTextView.text = getString(R.string.intro_enrollment)

        // 设置选项部分的监听器
        optionGroup.setOnCheckedChangeListener { _, checkedId ->
            // 获取用户选择的选项
            selectedOption = when (checkedId) {
                R.id.option1 -> getString(R.string.student_template_1)
                R.id.option2 -> getString(R.string.student_template_2)
                R.id.option3 -> getString(R.string.student_template_3)
                R.id.option4 -> getString(R.string.student_template_4)
                else -> ""
            }
        }

        // 设置按钮点击事件的监听器
        continueButton.setOnClickListener {
            if (selectedOption.isEmpty()) {
                Toast.makeText(this, "请先选择一个选项", Toast.LENGTH_SHORT).show()
            } else {
                // 处理按钮点击的逻辑，例如提交用户选择的选项
                Toast.makeText(this, "你选择了：$selectedOption", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
