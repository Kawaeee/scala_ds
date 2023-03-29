name := "Vegas"

version := "0.1-unable-to-run"

scalacOptions ++= Seq(
  "-unchecked", "-deprecation", "-feature"
)

libraryDependencies  ++= Seq(
  "org.scalacheck" %% "scalacheck" % "1.14.0" % "test",
  "org.scalatest" %% "scalatest" % "3.0.5" % "test",
  "org.vegas-viz" %% "vegas" % "0.3.10",
)

// libraryDependencies += "com.github.darrenjw" %% "scala-glm" % "0.3"
// libraryDependencies  += "org.ddahl" %% "rscala" % "2.5.3"
fork := true

// scalaVersion := "2.12.6"
scalaVersion := "2.11.12"

// Based on my local instance
// openjdk version "1.8.0_362"
// OpenJDK Runtime Environment (build 1.8.0_362-bre_2023_01_22_03_32-b00)
// OpenJDK 64-Bit Server VM (build 25.362-b00, mixed mode)
// I just keep getting this error: Exception in thread "main" java.lang.NoClassDefFoundError: javafx/embed/swing/JFXPanel
