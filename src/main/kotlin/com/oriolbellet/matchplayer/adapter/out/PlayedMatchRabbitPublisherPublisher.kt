package com.oriolbellet.matchplayer.adapter.out

import com.oriolbellet.matchplayer.application.port.out.PlayedMatchPublisher
import com.oriolbellet.matchplayer.domain.Match
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.AmqpException
import org.springframework.amqp.rabbit.core.RabbitTemplate
import javax.inject.Named

@Named
class PlayedMatchRabbitPublisherPublisher(private val rabbitTemplate: RabbitTemplate): PlayedMatchPublisher {

    private val logger: Logger = LoggerFactory.getLogger(PlayedMatchRabbitPublisherPublisher::class.java.simpleName)

    override fun invoke(match: Match) {

        val scoreDto = ScoreDto(match.score!!.homeTeamGoals, match.score!!.awayTeamGoals)
        val matchScoreDto = MatchScoreDto(match.matchId, scoreDto)

        try {
            rabbitTemplate.convertAndSend("football-exchange", "com.oriolbellet.football.match-played", Json.encodeToString(matchScoreDto))
        } catch (e: AmqpException) {
           logger.error("Error publishing to rabbit", e)
        }

    }

}