import play.sbt.routes.RoutesKeys._
import sbt._
import sbt.Keys._

object ApplicationBuild extends Build {
  /**********************
   * COMMON DEFINITIONS *
   **********************/
  val applicationName         = "myawesomeproject"
  val applicationVersion      = "1.0-SNAPSHOT"
  val applicationOrganization = "com.mehmetakiftutuncu.myawesomeproject"
  val applicationScalaVersion = "2.11.7"
  val applicationJavaOptions  = Seq()
  val applicationScalaOptions = Seq("-deprecation", "-unchecked", "-feature")

  /*******************
   * MODULE SETTINGS *
   *******************/
  val commonSettings = Seq(
    version         := applicationVersion,
    organization    := applicationOrganization,
    scalaVersion    := applicationScalaVersion,
    routesGenerator := InjectedRoutesGenerator,
    scalacOptions  ++= applicationScalaOptions,
    javacOptions   ++= applicationJavaOptions,
    resolvers      ++= Seq(
      "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
    )
  )

  val coreSettings = commonSettings ++ Seq()

  val modelsSettings = commonSettings ++ Seq()

  /****************************
   * SBT PROJECTS AND MODULES *
   ****************************/
  lazy val root = Project(applicationName, file("."))
    .enablePlugins(play.sbt.PlayScala)
    .settings(commonSettings:_*)
    .settings(
      libraryDependencies ++= Dependencies.commonDependencies
    )
    .dependsOn(
      core % "compile->compile;test->test",
      models % "compile->compile;test->test"
    )

  lazy val core = Project("core", file("modules/core"))
    .enablePlugins(play.sbt.PlayScala)
    .settings(coreSettings:_*)
    .settings(
      libraryDependencies ++= Dependencies.coreDependencies
    )

  lazy val models = Project("models", file("modules/models"))
    .enablePlugins(play.sbt.PlayScala)
    .settings(modelsSettings:_*)
    .settings(
      libraryDependencies ++= Dependencies.modelsDependencies
    )
    .dependsOn(
      core % "compile->compile;test->test"
    )

  override def rootProject = Option(root)
}