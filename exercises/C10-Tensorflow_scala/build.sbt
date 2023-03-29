name := "TensorFlowOnScala"

version := "0.1"

scalacOptions ++= Seq(
  "-unchecked", "-deprecation", "-feature"
)

libraryDependencies  ++= Seq(
  "org.scalacheck" %% "scalacheck" % "1.14.0" % "test",
  "org.scalatest" %% "scalatest" % "3.0.5" % "test",
  "org.scalanlp" %% "breeze" % "0.13.2",
  "org.scalanlp" %% "breeze-natives" % "0.13.2",
  "org.scalanlp" %% "breeze-viz" % "0.13.2"
)

// libraryDependencies  += "org.typelevel" %% "spire" % "0.15.0"  // This version is incompatible with breeze 0.13.2

libraryDependencies  ++= Seq(
  // TODO: change classifier based on your operating system (windows, linux, darwin)
  "org.platanios" %% "tensorflow" % "0.6.5" classifier "darwin"
)

// libraryDependencies  += "org.ddahl" %% "rscala" % "2.5.3"
// libraryDependencies  += "me.shadaj" %% "scalapy" % "0.1.0-SNAPSHOT"

// scalaVersion := "2.12.6"
scalaVersion := "2.12.16"
