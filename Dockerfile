FROM openjdk:8
COPY ./target/konnecticator-0.0.1.jar /usrl/local/bin/konnecticator.jar
CMD ["java", "-jar", "/usrl/local/bin/konnecticator.jar"]
