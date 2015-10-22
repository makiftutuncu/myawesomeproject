package com.mehmetakiftutuncu.myawesomeproject.models

import com.mehmetakiftutuncu.myawesomeproject.core.Utilities

case class Awesomeness(name: String) {
  def isAwesome: Boolean = !Utilities.isEmptyString(name) && name.charAt(0).toLower == 'a'
}
