FROM openjdk:17
COPY ./target/server-0.0.1.jar /usrl/local/bin/konnecticator.jar

RUN microdnf install ca-certificates
ADD zscalerRoot.cer /etc/pki/ca-trust/source/anchors/zscalerRoot.cer
RUN update-ca-trust extract

CMD ["java", "-jar", "/usrl/local/bin/konnecticator.jar"]

# docker build . -t k1
# docker run --env-file ./.env.dev -p 8081:8081 -p 5005:5005 k1