FROM openjdk:8-jre-alpine

ENV KAFKA_CONSUMER_BOOTSTRAP=""
ENV KAFKA_CONSUMER_TOPIC=""
ENV KAFKA_CONSUMER_GROUP="offer-cache-service-group"

ENV MONGODB_HOST=""
ENV MONGODB_PORT=""
ENV MONGODB_USERNAME=""
ENV MONGODB_PASSWORD=""
ENV MONGODB_DATABASE=""

EXPOSE 8080

WORKDIR /
COPY target/scala-2.12/offer-cache-service*.jar ./offer-cache-service.jar
ENTRYPOINT java -jar \
    -Dkafka.consumer.bootstrap=${KAFKA_CONSUMER_BOOTSTRAP} \
    -Dkafka.consumer.topic=${KAFKA_CONSUMER_TOPIC} \
    -Dkafka.consumer.group=${KAFKA_CONSUMER_GROUP} \
    -Dmongodb.host=${MONGODB_HOST} \
    -Dmongodb.port=${MONGODB_PORT} \
    -Dmongodb.username=${MONGODB_USERNAME} \
    -Dmongodb.password=${MONGODB_PASSWORD} \
    -Dmongodb.database=${MONGODB_DATABASE} \
    ./offer-cache-service.jar

