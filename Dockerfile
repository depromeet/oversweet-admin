FROM azul/zulu-openjdk:17
CMD ["./gradlew", "clean", "build"]
ARG JAR_FILE_PATH=admin-api/build/libs/admin-api-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE_PATH} app.jar
ENV	PROFILE local
ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILE}", "-jar","/app.jar"]
