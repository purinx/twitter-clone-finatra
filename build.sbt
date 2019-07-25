import org.apache.logging.log4j.core.config.composite.MergeStrategy

lazy val root = (project in file(".")).
  settings(
    name := "twitter-clone",
    scalaVersion := "2.12.8",
    libraryDependencies ++= dervy,
    resolvers ++= resolve
  )

lazy val dervy = {
  Seq(
    "com.twitter" %% "finatra-http" % "19.7.0",
    "mysql" % "mysql-connector-java" % "5.1.38",
    "io.getquill" %% "quill-jdbc" % "3.0.1",
    "com.typesafe" % "config" % "1.3.2",
    "org.slf4j" % "slf4j-simple" % "1.6.4",
    "org.scala-lang" % "scala-compiler" % "2.12.8",
    "org.springframework.security" % "spring-security-web" % "4.1.3.RELEASE",
    "javax.enterprise" % "cdi-api" % "2.0" % "provided"

  )
}

lazy val test = {
  Seq("com.twitter" %% "finatra-http" % "19.7.0" % "test" classifier "tests")
}

lazy val resolve = {
  Seq(
    Resolver.sonatypeRepo("releases"),
    "Twitter Maven" at "https://maven.twttr.com",
  )
}

unmanagedResourceDirectories in Compile += baseDirectory.value / "src" / "main" / "resources" / "view"
