package com.oriolbellet.matchplayer.domain

import com.oriolbellet.matchplayer.domain.MatchTeams.HOME_TEAM
import com.oriolbellet.matchplayer.domain.BasicMatchPlayer.PlayResult.*

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Named

@Named
class BasicMatchPlayer(val possessionCalculator: PossessionCalculator,
                       val attackSimulator: AttackSimulator) : MatchPlayer {

    private val logger: Logger = LoggerFactory.getLogger(BasicMatchPlayer::class.java.simpleName)

    private val numberOfAttacks = 10

    override fun invoke(homeTeamLineUp: LineUp, awayTeamLineUp: LineUp): Score {

        var homeTeamGoals = 0
        var awayTeamGoals = 0

        for (x in 0 until this.numberOfAttacks) {

            logger.info("Starting round $x...")

            val playResult = this.simulateAttack(homeTeamLineUp, awayTeamLineUp)

            //Thread.sleep(4000)

            if (playResult == HOME_GOAL) {
                logger.info("Home Goal!")
                homeTeamGoals++
            } else if (playResult == AWAY_GOAL) {
                logger.info("Away Goal!")
                awayTeamGoals++
            } else {
                logger.info("No goal!")
            }

            logger.info("Current result: $homeTeamGoals - $awayTeamGoals")

        }

        logger.info("Final result: $homeTeamGoals - $awayTeamGoals")

        return Score(homeTeamGoals, awayTeamGoals)

    }


    private fun simulateAttack(homeTeamLineUp: LineUp, awayTeamLineUp: LineUp): PlayResult {

        val teamWithPossession = this.possessionCalculator(homeTeamLineUp, awayTeamLineUp)

        if (teamWithPossession == HOME_TEAM) {
            logger.info("Home team attack...")
            return this.simulateHomeTeamAttack(homeTeamLineUp, awayTeamLineUp)
        }

        logger.info("Away team attack...")
        return this.simulateAwayTeamAttack(homeTeamLineUp, awayTeamLineUp)

    }

    private fun simulateHomeTeamAttack(homeTeamLineUp: LineUp, awayTeamLineUp: LineUp): PlayResult {

        return if (this.attackSimulator(homeTeamLineUp, awayTeamLineUp) == AttackResult.GOAL) HOME_GOAL else NO_GOAL

    }

    private fun simulateAwayTeamAttack(homeTeamLineUp: LineUp, awayTeamLineUp: LineUp): PlayResult {

        return if (this.attackSimulator(awayTeamLineUp, homeTeamLineUp) == AttackResult.GOAL) AWAY_GOAL else NO_GOAL

    }

    enum class PlayResult {
        HOME_GOAL,
        AWAY_GOAL,
        NO_GOAL
    }

}