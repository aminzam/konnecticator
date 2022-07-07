# How to run the demo app
This section enables running konnecticator in demo mode. 
Start by running `docker-compose up -d` in the command line. This will start a Kafka broker and a Kafka Connect cluster. It will then run a container that calls the RestAPI endpoints of the Connect cluster to create a new datagen connector for demo purposes.