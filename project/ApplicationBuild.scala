import play.sbt.routes.RoutesKeys._
import sbt.Keys._
import sbt._

object ApplicationBuild extends Build {
  /**********************
   * COMMON DEFINITIONS *
   **********************/
  lazy val applicationName          = "myawesomeproject"
  lazy val applicationVersion       = "latest"
  lazy val applicationOrganization  = "com.mehmetakiftutuncu.myawesomeproject"
  lazy val applicationScalaVersion  = "2.11.7"
  lazy val applicationJavaOptions   = Seq()
  lazy val applicationJavacOptions  = Seq()
  lazy val applicationScalacOptions = Seq("-deprecation", "-unchecked", "-feature")

  // Base directory for our jars
  val libBase = file("lib")

  // Get default cross target at the beginning
  val defaultCrossTarget = crossTarget

  /*******************
   * MODULE SETTINGS *
   *******************/
  lazy val artifactSettings = Seq(
    // Don't generate jar for doc
    publishArtifact in packageDoc := false,

    // Don't generate jar for src
    publishArtifact in packageSrc := false
  )

  lazy val commonSettings = Seq(
    version          := applicationVersion,
    organization     := applicationOrganization,
    scalaVersion     := applicationScalaVersion,
    routesGenerator  := InjectedRoutesGenerator,
    exportJars       := true,

    // Use "lib" in root instead of inside each module
    unmanagedBase := libBase,

    // Put binary jars to "lib" in root
    crossTarget in packageBin := libBase,

    scalacOptions   ++= applicationScalacOptions,
    javacOptions    ++= applicationJavacOptions,
    javaOptions     ++= applicationJavaOptions,
    resolvers       ++= Seq(
      "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
    )
  ) ++ artifactSettings

  lazy val coreSettings = commonSettings ++ Seq()

  lazy val modelsSettings = commonSettings ++ Seq()

  /****************************
   * SBT PROJECTS AND MODULES *
   ****************************/
  lazy val root = Project(applicationName, file("."))
    .enablePlugins(play.sbt.PlayScala)
    .settings(commonSettings:_*)
    .settings(
      libraryDependencies ++= Dependencies.rootDependencies,

      // Put binary jar of root to default cross target
      crossTarget in packageBin := defaultCrossTarget.value
    )
    .aggregate(core, models)

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

  override def rootProject = Option(root)
}
