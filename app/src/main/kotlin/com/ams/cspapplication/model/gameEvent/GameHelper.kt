package com.ams.cspapplication.model.gameEvent

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader

class GameHelper {
    companion object {
        fun loadGameEvents(context: Context): List<GameEvent> {
            val gson = Gson()
            val gameEventsList = mutableListOf<GameEvent>()

            try {
                val inputStream = context.assets.open("game_events.json")
                val bufferedReader = BufferedReader(InputStreamReader(inputStream))

                val json = bufferedReader.readText()
                val listType = object : TypeToken<List<GameEvent>>() {}.type
                gameEventsList.addAll(gson.fromJson(json, listType))
                bufferedReader.close()
            } catch (exception: Exception) {
                exception.printStackTrace()
            }

            return gameEventsList
        }
    }
}
