package com.ams.cspapplication.ui.homepage

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ams.cspapplication.R

class HomepageActivity : AppCompatActivity() {
    private val recyclerViewContainer by lazy { findViewById<LinearLayout>(R.id.recyclerViewContainer) }
    private val addRecyclerViewButton by lazy { findViewById<Button>(R.id.addRecyclerViewButton) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        addRecyclerViewButton.setOnClickListener {
            // 创建新的 RecyclerView
            val newRecyclerView = RecyclerView(this)
            newRecyclerView.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            val stringList: List<String> = listOf("Item 1", "Item 2", "Item 3")
            // 设置适配器和布局管理器
            val adapter = EventAdapter(stringList) // 你的适配器类
            newRecyclerView.adapter = adapter
            newRecyclerView.layoutManager = LinearLayoutManager(this)

            // 将新的 RecyclerView 添加到容器中
            recyclerViewContainer.addView(newRecyclerView)
        }
    }
}
