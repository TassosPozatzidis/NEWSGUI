# NEWSGUI
# Introduction

Install a library into the local maven repository
Create a JavaFx project
Create a DB and connect


# API
NewAPI - https://newsapi.org/
IP-API - https://geo.ipify.org/
MariaDB Database

# Requirements
You'll need to go create an account to get an API key (https://newsapi.org/),(https://geo.ipify.org/)

# Building & Running the project

In order to build the .jar file, a sequence of `Maven` commands should be executed.
These commands can be executed at once, by running the following script:

```
build_project.sh
```

In order to run the produced `.jar` file, run:

```bash
run_project.sh
```



# Dependencies

## Local Dependencies and prerequisite versions

In order for the project to run, the following Java and Maven versions should run locally:

```bash
	java --version
	
	openjdk 17 2021-09-14
	OpenJDK Runtime Environment Temurin-17+35 (build 17+35)
	OpenJDK 64-Bit Server VM Temurin-17+35 (build 17+35, mixed mode)
```

```bash
	mvn --version
	
	Apache Maven 3.8.4
	Maven home: C:\apache-maven-3.8.4
	Java version: 17.0.1, vendor: Oracle Corporation, runtime: C:\Program Files\Java\jdk-17.0.1
	Default locale: en_US, platform encoding: Cp1253
	OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"
```

 - NewsAPI library 
 
``` 
	<dependencies>
		<dependency>
			<groupId>org.openjfx</groupId>
			<artifactId>javafx-controls</artifactId>
			<version>13</version>
		</dependency>
		<dependency>
			<groupId>gr.unipi</groupId>
			<artifactId>NewsAPI</artifactId>
			<version>0.0.1-SNAPSHOT</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/org.mariadb.jdbc/mariadb-java-client -->
		<dependency>
			<groupId>org.mariadb.jdbc</groupId>
			<artifactId>mariadb-java-client</artifactId>
			<version>3.0.3</version>
		</dependency>
	</dependencies>
```

# Maven Coordinates

To add this library as a dependency add the following maven coordinates into your pom.xml file

		<dependency>
			<groupId>gr.unipi</groupId>
			<artifactId>NewsAPI</artifactId>
			<version>0.0.1-SNAPSHOT</version>
		</dependency>
