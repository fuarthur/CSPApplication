package com.ams.cspapplication.ui.simulator

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ams.cspapplication.R
import com.ams.cspapplication.model.gameEvent.GameEvent
import com.ams.cspapplication.model.gameEvent.GameHelper
import com.ams.cspapplication.model.studentTemplate.*
import com.ams.cspapplication.ui.finalPage.FinalActivity
import java.math.BigDecimal


class SimulatorActivity : AppCompatActivity() {
    private lateinit var gameEvents: List<GameEvent>
    private lateinit var literatureValue: TextView
    private lateinit var sciencesValue: TextView
    private lateinit var sportsValue: TextView
    private lateinit var artsValue: TextView
    private lateinit var gpaValue: TextView
    private lateinit var student: Student
    private lateinit var continueButton: Button
    private lateinit var eventDisplay: TextView
    private var isOver: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simulator)

        val templateOption = intent.getIntExtra("template", 1)
        student = when (templateOption) {
            1 -> Bookworm()
            2 -> Artistic()
            3 -> Techie()
            4 -> Athlete()
            else -> Bookworm()
        }
        Log.d("debug", student.toString())

        literatureValue = findViewById(R.id.literatureValue)
        sciencesValue = findViewById(R.id.sciencesValue)
        sportsValue = findViewById(R.id.sportsValue)
        artsValue = findViewById(R.id.artsValue)
        gpaValue = findViewById(R.id.gpaValue)
        eventDisplay = findViewById(R.id.event)

        updateAttributes()

        // 获取所有的游戏事件
        gameEvents = GameHelper.loadGameEvents(this)

        // 筛选出符合条件的gameEvent
        gameEvents = gameEvents.filter { gameEvent ->
            gameEvent.conditions.contains(templateOption)
        }

        val mediaPlayer = MediaPlayer.create(this, R.raw.button_click)
        mediaPlayer.setVolume(1.0f, 1.0f)

        continueButton = findViewById(R.id.continueButton)
        continueButton.setOnClickListener {
            mediaPlayer.start()
            if (isOver) {
                navigateToFinal()
            } else {
                student.daysInSchool++
                val event = getRandomEvent()
                val dayString = getString(R.string.day)
                val daysInSchool = student.daysInSchool.toString()
                val eventDescription = event.description
                val displayText = "$dayString $daysInSchool $eventDescription"
                eventDisplay.text = displayText
                student.literature = (student.literature + event.effects[0].toInt()).coerceIn(0, 10)
                student.sciences = (student.sciences + event.effects[1].toInt()).coerceIn(0, 10)
                student.arts = (student.arts + event.effects[2].toInt()).coerceIn(0, 10)
                student.sports = (student.sports + event.effects[3].toInt()).coerceIn(0, 10)

                val effect = BigDecimal(event.effects[4].toString())
                student.GPA = (student.GPA.toBigDecimal() + effect).toDouble().coerceAtMost(4.0)
                if (student.GPA < 2.0) isOver = true
                if (student.daysInSchool >= 30) isOver = true
                updateAttributes()
            }
        }

    }

    private fun getRandomEvent(): GameEvent {
        val events: List<GameEvent> = gameEvents.filter { gameEvent ->
            student.literature >= gameEvent.attribute[0] && student.sciences >= gameEvent.attribute[1] && student.sports >= gameEvent.attribute[2] && student.arts >= gameEvent.attribute[3]
        }
        val randomIndex = (events.indices).random()
        return events[randomIndex]
    }

    private fun updateAttributes() {
        literatureValue.text = student.literature.toString()
        sciencesValue.text = student.sciences.toString()
        sportsValue.text = student.sports.toString()
        artsValue.text = student.arts.toString()
        gpaValue.text = student.GPA.toString()
    }

    private fun navigateToFinal() {
        val intent = Intent(this, FinalActivity::class.java)
        intent.putExtra("score", (student.literature+student.sciences+student.arts+student.sports)*student.GPA)
        startActivity(intent)
        finish()
    }
}
