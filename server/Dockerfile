FROM openjdk:17

COPY ./target/*.jar /usrl/local/bin/konnecticator.jar
CMD ["java", "-jar", "/usrl/local/bin/konnecticator.jar"]

# docker build . -t k1
# docker run --env-file ./.env.dev -p 8081:8081 -p 5005:5005 k1