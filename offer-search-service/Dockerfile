FROM openjdk:8-jre-alpine

ENV KAFKA_CONSUMER_BOOTSTRAP=""
ENV KAFKA_CONSUMER_TOPIC=""
ENV KAFKA_CONSUMER_GROUP="offer-search-service-group"

ENV ES_HOST=""
ENV ES_PORT=""
ENV ES_CLUSTER_NAME=""

EXPOSE 8080

WORKDIR /
COPY ./build/libs/offer-search-service*.jar ./offer-search-service.jar
ENTRYPOINT java -jar \
    -Dkafka.consumer.bootstrap=${KAFKA_CONSUMER_BOOTSTRAP} \
    -Dkafka.consumer.topic=${KAFKA_CONSUMER_TOPIC} \
    -Dkafka.consumer.group=${KAFKA_CONSUMER_GROUP} \
    -Des.host=${ES_HOST} \
    -Des.port=${ES_PORT} \
    -Des.cluster-name=${ES_CLUSTER_NAME} \
    ./offer-search-service.jar
