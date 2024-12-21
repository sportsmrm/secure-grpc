val Scala212 = "2.12.20"

val Scala213 = "2.13.15"

val Scala33 = "3.3.4"

val Scala36 = "3.6.2"

ThisBuild / organization := "io.sportsmrm"

ThisBuild / scalaVersion := Scala33

lazy val core = (projectMatrix in file("core"))
  .defaultAxes()
  .settings(
    name := "secure-grpc-core"
  )
  .jvmPlatform(scalaVersions = Seq(Scala212, Scala213, Scala33, Scala36))

lazy val codeGen = (projectMatrix in file("code-gen"))
  .enablePlugins(BuildInfoPlugin)
  .defaultAxes()
  .settings(
     buildInfoKeys := Seq[BuildInfoKey](name, organization, version, scalaVersion, sbtVersion),
     buildInfoPackage := "io.sportsmrm.grpc.secure.compiler",
     libraryDependencies ++= Seq(
       "com.thesamet.scalapb" %% "compilerplugin" % scalapb.compiler.Version.scalapbVersion,
       "com.thesamet.scalapb" %% "scalapb-runtime" % scalapb.compiler.Version.scalapbVersion,
     )
  )
  .jvmPlatform(scalaVersions = Seq(Scala212, Scala213, Scala33, Scala36))

lazy val codeGenJVM212 = codeGen.jvm(Scala212)

lazy val protocGenSecureGrpc = protocGenProject("protoc-gen-secure-grpc", codeGenJVM212)
  .settings(
    Compile / mainClass := Some("io.sportsmrm.grpc.secure.compiler.CodeGenerator"),
    scalaVersion := Scala212
  )

lazy val e2e = (projectMatrix in file("e2e"))
  .dependsOn(core)
  .enablePlugins(LocalCodeGenPlugin)
  .defaultAxes()
  .settings(
    skip / publish := true,
    codeGenClasspath := (codeGenJVM212 / Compile / fullClasspath).value,
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.2.19" % Test
    ),
    Compile / PB.targets := Seq(
      scalapb.gen() -> (Compile / sourceManaged).value / "scalapb",
      genModule("io.sportsmrm.grpc.secure.compiler.CodeGenerator$") -> (Compile / sourceManaged).value / "scalapb"
    )
  )
  .jvmPlatform(scalaVersions = Seq(Scala212, Scala213, Scala33, Scala36))

lazy val root: Project =
  project
    .in(file("."))
    .settings(
      publishArtifact := false,
      publish := {},
      publishLocal := {}
    )
    .aggregate(protocGenSecureGrpc.agg)
    .aggregate(
      (codeGen.projectRefs ++ core.projectRefs ++ e2e.projectRefs) *
    )
