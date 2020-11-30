package com.oriolbellet.matchplayer.application.port.`in`

import com.oriolbellet.matchplayer.application.port.out.PlayedMatchPublisher
import com.oriolbellet.matchplayer.domain.Match
import com.oriolbellet.matchplayer.domain.MatchPlayer
import javax.inject.Named

@Named
class PlayMatchService(private val matchPlayer: MatchPlayer, private val playedMatchPublisher: PlayedMatchPublisher) : PlayMatchUseCase {

    override fun invoke(match: Match) {

        match.play(matchPlayer)

        playedMatchPublisher(match)

    }
}