#FROM openjdk:17
#
#RUN microdnf install ca-certificates
#ADD zscalerRoot.cer /etc/pki/ca-trust/source/anchors/zscalerRoot.cer
#RUN update-ca-trust extract

FROM k-base
COPY ./target/*.jar /usrl/local/bin/konnecticator.jar
CMD ["java", "-jar", "/usrl/local/bin/konnecticator.jar"]

# docker build . -t k1
# docker run --env-file ./.env.dev -p 8081:8081 -p 5005:5005 k1