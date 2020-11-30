package com.oriolbellet.matchplayer.domain

interface Tactic {

    fun getNumDefenders(): Int

    fun getNumMidfielders(): Int

    fun getNumForwards(): Int

}