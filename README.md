# Project 02

Spring - Rsocket

[https://gitorko.github.io/spring-rsocket/](https://gitorko.github.io/spring-rsocket/)

### Version

Check version

```bash
$java --version
openjdk 21.0.3 2024-04-16 LTS
```

### Dev

To run the code.

```bash
./gradlew clean build

java -jar rserver/build/libs/rserver-1.0.0.jar
java -jar rclient/build/libs/rclient-1.0.0.jar

./gradlew :rserver:build
./gradlew :rclient:build
./gradlew :rcommon:build

./gradlew :rserver:bootRun
./gradlew :rclient:bootRun

./gradlew bootJar
```
