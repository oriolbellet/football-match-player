package com.oriolbellet.matchplayer.domain

interface AttackSimulator {

    operator fun invoke(attackerLineUp: LineUp, defenderLineUp: LineUp): AttackResult

}