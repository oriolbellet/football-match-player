package com.oriolbellet.matchplayer.application.port.`in`

import com.oriolbellet.matchplayer.domain.Match

interface PlayMatchUseCase {

    operator fun invoke(match: Match)

}