package com.mehmetakiftutuncu.myawesomeproject.models

object Suckiness {
  object Levels {
    val bad: String       = "bad"
    val reallyBad: String = "really bad"
    val balls: String     = "balls"
  }

  def howBad(awesomeness: Awesomeness): String = {
    val length: Int = awesomeness.name.length

    if (length > 0 && length <= 3) {
      Levels.bad
    } else if (length > 3 && length <= 6) {
      Levels.reallyBad
    } else {
      Levels.balls
    }
  }
}
