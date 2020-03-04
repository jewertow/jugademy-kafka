FROM openjdk:8-jre-alpine

# Kafka properties
ENV KAFKA_PRODUCER_BOOTSTRAP=""
ENV KAFKA_OFFER_CHANGE_TOPIC=""

# Postgres properties
ENV POSTGRES_HOST=""
ENV POSTGRES_USERNAME=""
ENV POSTGRES_PASSWORD=""
ENV POSTGRES_DB=""

EXPOSE 8080

WORKDIR /
COPY ./target/offer-storage*.jar ./app.jar
ENTRYPOINT java -jar \
    -Dspring.datasource.url=jdbc:postgresql://${POSTGRES_HOST}:5432/${POSTGRES_DB} \
    -Dspring.datasource.username=${POSTGRES_USERNAME} \
    -Dspring.datasource.password=${POSTGRES_PASSWORD} \
    -Dkafka.producer.bootstrap=${KAFKA_PRODUCER_BOOTSTRAP} \
    -Dkafka.producer.topic=${KAFKA_OFFER_CHANGE_TOPIC} \
    ./app.jar
