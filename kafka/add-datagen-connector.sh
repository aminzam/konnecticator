#!/bin/sh
until $(curl --head --fail http://connect:8083/); do
    printf '...\n'
    sleep 3
done

curl -X POST -H "Content-Type: application/json" --data /etc/connector.config http://connect:8083/connectors