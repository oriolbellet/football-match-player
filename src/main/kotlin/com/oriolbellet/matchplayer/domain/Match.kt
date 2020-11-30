package com.oriolbellet.matchplayer.domain

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class Match(val matchId: Long, private val homeTeam: Team, private val awayTeam: Team) {

    private val logger: Logger = LoggerFactory.getLogger(Match::class.java.simpleName)

    var score: Score? = null

    fun play(matchPlayer: MatchPlayer) {

        logger.info("Playing ${homeTeam.name} vs ${awayTeam.name} ...")

        this.score = matchPlayer(this.homeTeam.lineUp, this.awayTeam.lineUp)

    }

}