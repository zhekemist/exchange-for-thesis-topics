JAR=executable/backend.jar

java -jar ${JAR} --spring.profiles.active=filler    &&
java -jar ${JAR} --spring.profiles.active=mariadb   &&
java -jar ${JAR} --spring.profiles.active=migrator  &&
java -jar ${JAR} --spring.profiles.active=mongodb
