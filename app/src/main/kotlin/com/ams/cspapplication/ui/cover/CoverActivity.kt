package com.ams.cspapplication.ui.cover

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.ComponentActivity
import com.ams.cspapplication.R
import com.ams.cspapplication.ui.homepage.HomepageActivity

class CoverActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cover) // 设置布局文件

        // 使用 Handler 实现延迟跳转到 HomepageActivity
        Handler().postDelayed({
            navigateToHomepage()
        }, 2000) // 2000 毫秒等于 2 秒
    }

    private fun navigateToHomepage() {
        val intent = Intent(this, HomepageActivity::class.java)
        startActivity(intent)
        finish() // 可选，如果你想关闭当前的 MainActivity
    }
}
