addSbtPlugin("com.eed3si9n" % "sbt-projectmatrix" % "0.10.1")

addSbtPlugin("com.thesamet" % "sbt-protoc" % "1.0.7")

libraryDependencies += "com.thesamet.scalapb" %% "compilerplugin" % "0.11.17"

addSbtPlugin("com.thesamet" % "sbt-protoc-gen-project" % "0.1.8")

// Build info is used to make the version number of the core library available
// to the code generator so it can automatically add the correct version of
// the core library to the build.
addSbtPlugin("com.eed3si9n" % "sbt-buildinfo" % "0.13.1")

addSbtPlugin("org.apache.pekko" % "pekko-grpc-sbt-plugin" % "1.1.1")