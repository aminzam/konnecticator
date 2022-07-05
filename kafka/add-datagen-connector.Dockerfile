#docker build -f .\add-datagen-connector.Dockerfile -t datagen-connect:v1 .

FROM curlimages/curl

COPY add-datagen-connector.sh /usr/local/bin/script.sh
COPY datagen_connector.config /etc/connector.config

ENTRYPOINT ["/usr/local/bin/script.sh"]
