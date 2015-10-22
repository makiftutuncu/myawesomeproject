import play.sbt.PlayImport._
import sbt._

object Dependencies extends Build {
  /*****************************************
   * CUSTOM LIBRARY DEPENDENCY DEFINITIONS *
   *****************************************/
  val jodaDateTime = "joda-time" % "joda-time" % "2.8.2"
  val jodaMoney    = "org.joda" % "joda-money" % "0.10.0"

  /*********************************************
   * DEPENDENCY SEQUENCES FOR EACH SBT PROJECT *
   *********************************************/
  val commonDependencies = Seq(
    jdbc,
    cache,
    ws,
    specs2 % Test,
    jodaDateTime
  )

  val coreDependencies = commonDependencies ++ Seq(
    jodaMoney
  )

  val modelsDependencies = commonDependencies ++ Seq()
}