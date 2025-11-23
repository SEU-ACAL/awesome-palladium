val chisel6Version = "6.5.0"
val scalaVersionFromChisel = "2.13.12"

lazy val chisel6Settings = Seq(
  libraryDependencies ++= Seq("org.chipsalliance" %% "chisel" % chisel6Version),
  addCompilerPlugin("org.chipsalliance" % "chisel-plugin" % chisel6Version cross CrossVersion.full)
)

lazy val chipyard = ProjectRef(file("../../arch/thirdparty/chipyard"), "chipyard")
lazy val fpga_shells = ProjectRef(file("../../arch/thirdparty/chipyard"), "fpga_shells")

lazy val palladium = (project in file("."))
  .dependsOn(chipyard, fpga_shells)
  .settings(
    name := "palladium",
    organization := "com.seu-acal",
    version := "1.0.0",
    scalaVersion := scalaVersionFromChisel,
    scalacOptions ++= Seq(
      "-deprecation",
      "-unchecked",
      "-Ymacro-annotations"
    ),
    resolvers ++= Seq(
      Resolver.sonatypeRepo("snapshots"),
      Resolver.sonatypeRepo("releases")
    ),
    chisel6Settings
  )
