package com.oriolbellet.matchplayer.domain

enum class BasicTactics : Tactic {

    T343 {
        override fun getNumDefenders(): Int {
            return 3
        }

        override fun getNumMidfielders(): Int {
            return 4
        }

        override fun getNumForwards(): Int {
            return 3
        }
    },
    T442 {
        override fun getNumDefenders(): Int {
            return 4
        }

        override fun getNumMidfielders(): Int {
            return 4
        }

        override fun getNumForwards(): Int {
            return 2
        }
    },
    T532 {
        override fun getNumDefenders(): Int {
            return 5
        }

        override fun getNumMidfielders(): Int {
            return 3
        }

        override fun getNumForwards(): Int {
            return 2
        }
    },

}