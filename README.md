# Traffic Junction Modeller

A project for CS261 - Software Engineering where we model a single traffic junction.

# Prerequisites

This is a list of prerequisite software (that might need to be downloaded) for the project. It is important that everyone uses the same kit to ensure that we don't get errors when we attempt to integrate changes made in different versions of development tools.

- [Maven](https://maven.apache.org/)
- [OpenJDK23](https://openjdk.org/projects/jdk/23/)
- [gson](https://github.com/google/gson) -- Not required to download

My output for `mvn --version` is:

```
Maven home: /opt/homebrew/Cellar/maven/3.9.9/libexec
Java version: 23.0.2, vendor: Homebrew, runtime: /opt/homebrew/Cellar/openjdk/23.0.2/libexec/openjdk.jdk/Contents/Home
Default locale: en_GB, platform encoding: UTF-8
OS name: "mac os x", version: "15.2", arch: "aarch64", family: "mac"
```
- You should probably have different `OS name`, and a different `Maven home`
- Java version (23.0.2) and Maven version (3.9.9) should be the same

In order to resolve dependencies (namely the `gson` library, which will be used to serialise and deserialise JSON files), run the following command from the project:
```
mvn dependency:resolve
```

This will resolve dependencies without compiling.

# Useful Links

If a certain link is useful for you, please add it to this list before committing.

- [Java Swing (for GUI)](https://www.javatpoint.com/java-swing)
- [JUnit for unit testing](https://www.vogella.com/tutorials/JUnit/article.html)
- [JUnit for unit testing 2](https://www.tutorialspoint.com/junit/index.htm)
- [Git cheat sheet](https://education.github.com/git-cheat-sheet-education.pdf)
- [GSON library Github page](https://github.com/google/gson)
