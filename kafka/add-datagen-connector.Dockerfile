#docker build -f .\add-datagen-connector.Dockerfile -t datagen-connect:v1 .
# This container creates the connector by calling REST api to Connect cluster container

FROM curlimages/curl

COPY add-datagen-connector.sh /usr/local/bin/script.sh
COPY datagen_connector.config /etc/connector.config

ENTRYPOINT ["sh", "/usr/local/bin/script.sh"]
