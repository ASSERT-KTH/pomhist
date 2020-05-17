# PomHist

![Build](https://github.com/castor-software/pomhist//workflows/Build/badge.svg)

### What is PomHist

PomHist is a tool to analyze the evolution of the dependencies of a Maven project throughout its history.

### How does it work?

PomHist analyzes the Git history of a Maven project. To do so, it parses the output of the Git log command to analyze the dependencies added or removed from the `pom.xml` build file.

### Usage

Clone this repository:

```shell script
git clone https://github.com/castor-software/pomhist.git
```

Install PomHist:

```shell script
cd pomhist
mvn clean install
```

Run PomHist:

```shell script
`java -jar target/<pomhist-version-jar-with-dependencies.jar> <path-to-a-maven-project>
```
