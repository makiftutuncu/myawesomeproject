import play.sbt.PlayImport._
import sbt._

object Dependencies extends Build {
  /*****************************************
   * CUSTOM LIBRARY DEPENDENCY DEFINITIONS *
   *****************************************/
  lazy val jodaDateTime = "joda-time" % "joda-time"  % "2.8.2"
  lazy val jodaMoney    = "org.joda"  % "joda-money" % "0.10.0"

  /*********************************************
   * DEPENDENCY SEQUENCES FOR EACH SBT PROJECT *
   *********************************************/
  lazy val commonDependencies = Seq(
    jdbc,
    cache,
    ws,
    specs2 % Test,
    jodaDateTime
  )

  // Add customized dependencies to modules if necessary.
  lazy val rootDependencies   = commonDependencies
  lazy val coreDependencies   = commonDependencies ++ Seq(jodaMoney)
  lazy val modelsDependencies = commonDependencies
}
