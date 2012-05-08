name := "moforum"

version := "0.0"

scalaVersion := "2.9.2"

//resolvers += "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository"


resolvers += "Codehaus Releases" at "https://nexus.codehaus.org/content/repositories/releases/"

resolvers += "Sonatype OSS Releases" at "http://oss.sonatype.org/content/repositories/releases/"

libraryDependencies += "com.thoughtworks.paranamer" % "paranamer" % "2.5"

//libraryDependencies += "net.liftweb" %% "lift-json" % "2.4"
libraryDependencies += "net.liftweb" % "lift-json_2.9.1" % "2.4"

//libraryDependencies += "nu.validator.htmlparser" % "htmlparser" % "1.3.1"

libraryDependencies += "org.scalatest" %% "scalatest" % "1.7.2" % "test"

libraryDependencies += "org.scalamock" % "scalamock-scalatest-support_2.9.1" % "2.2" % "test"

libraryDependencies += "junit" % "junit" % "4.10" % "test"

libraryDependencies += "org.easymock" % "easymock" % "3.1" % "test"