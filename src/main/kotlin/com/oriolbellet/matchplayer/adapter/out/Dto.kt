package com.oriolbellet.matchplayer.adapter.out

import kotlinx.serialization.Serializable

@Serializable
data class MatchScoreDto(val matchId: Long, val score: ScoreDto)

@Serializable
data class ScoreDto(val homeTeamGoals: Int, val awayTeamGoals: Int)
