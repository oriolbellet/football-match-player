package com.oriolbellet.matchplayer.application.port.out

import com.oriolbellet.matchplayer.domain.Match

interface PlayedMatchPublisher {

    operator fun invoke(match: Match)

}