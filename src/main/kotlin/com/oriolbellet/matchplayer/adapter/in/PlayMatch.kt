package com.oriolbellet.matchplayer.adapter.`in`

import com.oriolbellet.matchplayer.application.port.`in`.PlayMatchUseCase
import com.oriolbellet.matchplayer.domain.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

import org.springframework.amqp.rabbit.annotation.RabbitListener
import javax.inject.Named

@Named
class PlayMatch (private val playMatchUseCase: PlayMatchUseCase) {

    @RabbitListener(queues = ["play-match"])
    fun listen(match: String) {

        val parse = Json.decodeFromString<MatchDto>(match)

        //TODO: Handle errors
        val homeTeamPlayers = getTeamPlayers(parse!!.homeTeam)
        val homeTeamLineUp = LineUp(homeTeamPlayers, BasicTactics.valueOf(parse.homeTeam.lineUp.tactic))
        val homeTeam = Team(parse.homeTeam.name, homeTeamLineUp)

        val awayTeamPlayers = getTeamPlayers(parse.awayTeam)
        val awayTeamLineUp = LineUp(awayTeamPlayers, BasicTactics.valueOf(parse.awayTeam.lineUp.tactic))
        val awayTeam = Team(parse.awayTeam.name, awayTeamLineUp)

        println("Message read from myQueue : $match")

        playMatchUseCase(Match(parse.matchId, homeTeam,awayTeam))

    }

    private fun getTeamPlayers(teamDto: TeamDto): List<Player> {

        return teamDto.lineUp.lineUp.map { x -> Player(x.playerId, x.alias, x.average) }.toList()

    }

}