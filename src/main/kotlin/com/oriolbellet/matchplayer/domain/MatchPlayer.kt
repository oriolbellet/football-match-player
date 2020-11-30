package com.oriolbellet.matchplayer.domain

interface MatchPlayer {

    operator fun invoke(homeTeamLineUp: LineUp, awayTeamLineUp: LineUp): Score

}