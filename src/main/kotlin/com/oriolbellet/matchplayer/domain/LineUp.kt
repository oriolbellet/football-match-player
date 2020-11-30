package com.oriolbellet.matchplayer.domain

class LineUp(val lineUp: List<Player>, private val tactic: BasicTactics) {

    private val numGoalkeepers = 1
    private val numPlayersLineUp = 11

    private fun getGoalKeeper(): Player {
        return lineUp[0]
    }

    private fun getDefenders(): List<Player> {
        val fromIndex = numGoalkeepers
        return lineUp.subList(fromIndex, fromIndex + tactic.getNumDefenders())
    }

    private fun getMidfielders(): List<Player> {
        val fromIndex = numGoalkeepers + tactic.getNumDefenders()
        return lineUp.subList(fromIndex, fromIndex + tactic.getNumMidfielders())
    }

    private fun getForwards(): List<Player> {
        val fromIndex = numGoalkeepers + tactic.getNumDefenders() + tactic.getNumMidfielders()
        return lineUp.subList(fromIndex, numPlayersLineUp)
    }

    fun getMidfieldPoints(): Int {
        return this.getMidfielders().stream().mapToInt{ x -> x.average }.sum()
    }

    fun getAttackPoints(): Int {
        return this.getForwards().stream().mapToInt{ x -> x.average }.sum()
    }

    fun getDefensePoints(): Int {
        return this.getDefenders().stream().mapToInt{ x -> x.average }.sum() +
                this.getGoalKeeper().average
    }


}