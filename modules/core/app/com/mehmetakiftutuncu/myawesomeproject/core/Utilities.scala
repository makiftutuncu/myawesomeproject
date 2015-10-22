package com.mehmetakiftutuncu.myawesomeproject.core

import java.util.Locale

import org.joda.money.{CurrencyUnit, Money}

object Utilities {
  def isEmptyString(s: String): Boolean = {
    // This is just to demonstrate different library dependencies are available.
    // Imagine we are doing something useful with org.joda.Money here.
    Money.of(CurrencyUnit.getInstance(Locale.getDefault), 1.0)

    s == null || s.isEmpty
  }
}
