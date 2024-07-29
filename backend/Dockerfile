FROM maven:3.9.3-eclipse-temurin-17
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

EXPOSE 7200
CMD ["mvn", "spring-boot:run"]