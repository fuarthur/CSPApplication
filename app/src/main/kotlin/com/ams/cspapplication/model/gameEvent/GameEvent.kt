package com.ams.cspapplication.model.gameEvent

data class GameEvent(
    val id: Int,
    val conditions: List<Int>,
    val attribute: List<Int>,
    val description: String,
    val effects: List<Float>
)
