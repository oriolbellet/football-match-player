package com.oriolbellet.matchplayer.adapter.`in`

import kotlinx.serialization.Serializable

@Serializable data class MatchDto(val matchId: Long, val homeTeam: TeamDto, val awayTeam: TeamDto)
@Serializable data class TeamDto (val name: String, val lineUp: LineUpDto)
@Serializable data class LineUpDto (val lineUp: List<PlayerDto>, val tactic: String)
@Serializable data class PlayerDto(val playerId: String, val alias: String, val average: Int)