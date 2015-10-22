package com.mehmetakiftutuncu.myawesomeproject.controllers

import com.mehmetakiftutuncu.myawesomeproject.models.{Suckiness, Awesomeness}
import org.joda.time.DateTime
import play.api.mvc._

class Application extends Controller {
  def index = Action {
    Ok("Go to /amIAwesome/:name to see if you are awesome.")
  }

  def amIAwesome(name: String) = Action {
    // This is just to demonstrate different library dependencies are available.
    // Imagine we are doing something useful with org.joda.DateTime here.
    DateTime.now()

    val awesomeness = Awesomeness(name)

    val result = if (awesomeness.isAwesome) {
      s"$name, you my friend, are awesome!"
    } else {
      val level: String = Suckiness.howBad(awesomeness)

      s"Sorry $name but you just suck $level."
    }

    Ok(result)
  }
}
