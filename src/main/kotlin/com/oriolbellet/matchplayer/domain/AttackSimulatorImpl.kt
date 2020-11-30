package com.oriolbellet.matchplayer.domain

import com.oriolbellet.matchplayer.domain.AttackResult.GOAL
import com.oriolbellet.matchplayer.domain.AttackResult.NO_GOAL
import java.util.concurrent.ThreadLocalRandom
import javax.inject.Named

@Named
class AttackSimulatorImpl: AttackSimulator {

    override fun invoke(attackerLineUp: LineUp, defenderLineUp: LineUp): AttackResult {

        val attackerPoints = attackerLineUp.getAttackPoints()
        val defenderPoints = defenderLineUp.getDefensePoints()

        val bound = attackerPoints + defenderPoints

        val random = ThreadLocalRandom.current().nextInt(1, bound)

        return if (random <=  attackerPoints) GOAL else NO_GOAL

    }

}