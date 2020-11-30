package com.oriolbellet.matchplayer.domain

interface PossessionCalculator {

    operator fun invoke(homeTeamLineUp: LineUp, awayTeamLineUp: LineUp): MatchTeams

}