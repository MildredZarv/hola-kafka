Architecture Overview:

Producer Application:
A Spring Boot application that sends serialized messages (in Avro format) to a Kafka topic.
Utilizes the KafkaAvroSerializer to serialize data based on an Avro schema.

Consumer Application:
A Spring Boot application that consumes messages from the Kafka topic, deserializes them using Avro, and processes the data.
Utilizes KafkaAvroDeserializer to deserialize the data into a strongly typed object.

Kafka:
Acts as the message broker that facilitates communication between the producer and consumer.

Schema Registry:
A service provided by Confluent that stores Avro schemas and ensures the compatibility of data between the producer and consumer.

Docker:
The Kafka broker, Zookeeper, Schema Registry, and both Spring Boot applications (producer and consumer) are containerized using Docker to ensure easy deployment and environment parity across development, staging, and production.
  - Command to bring up Docker containers: docker compose up -d
