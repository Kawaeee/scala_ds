name := "LogRegOnSpark"

version := "0.1"

scalacOptions ++= Seq(
  "-unchecked", "-deprecation", "-feature"
)

libraryDependencies  ++= Seq(
  "org.scalacheck" %% "scalacheck" % "1.14.0" % "test",
  "org.scalatest" %% "scalatest" % "3.0.5" % "test",
  "org.typelevel" %% "spire" % "0.15.0",
  "org.scalanlp" %% "breeze" % "0.13.2",
  "org.scalanlp" %% "breeze-natives" % "0.13.2",
  "org.scalanlp" %% "breeze-viz" % "0.13.2"
)

libraryDependencies  ++= Seq(
  "org.apache.spark" %% "spark-core" % "3.3.2",
  "org.apache.spark" %% "spark-sql" % "3.3.2",
  "org.apache.spark" %% "spark-mllib" % "3.3.2"
)

cleanupCommands in console := """
  println("In case spark is not stopped: ignore the message for spark.stop.")
  spark.stop()
"""

// libraryDependencies  += "org.ddahl" %% "rscala" % "2.5.3"
// libraryDependencies  += "me.shadaj" %% "scalapy" % "0.1.0-SNAPSHOT"

fork := true
javaOptions += "-Djdk.attach.allowAttachSelf=true"
javaOptions += "--add-exports=java.base/sun.nio.ch=ALL-UNNAMED"

// scalaVersion := "2.12.6"
scalaVersion := "2.12.16"
