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


# Dependencies

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
