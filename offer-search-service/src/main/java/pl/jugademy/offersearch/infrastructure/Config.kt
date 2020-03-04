package pl.jugademy.offersearch.infrastructure

class Config(private val config: Map<String, Any>) {

    fun <T: Any> getProperty(property: String): T =
            config[property] as T

    companion object {
        fun load(): Config {
            val config = mutableMapOf<String, Any>()

            val host = System.getProperty("es.host") ?: "127.0.0.1"
            config["host"] = host

            val port = System.getProperty("es.port")?.toInt() ?: 9300
            config["port"] = port

            val clusterName = System.getProperty("es.cluster-name") ?: "offer-search-cluster"
            config["clusterName"] = clusterName

            val bootstrapServers = System.getProperty("kafka.consumer.bootstrap") ?: "localhost:9092"
            config["bootstrapServers"] = bootstrapServers

            val topic = System.getProperty("kafka.consumer.topic") ?: "pl.jugademy.OfferChanged"
            config["topic"] = topic

            val consumerGroup = System.getProperty("kafka.consumer.group") ?: "offer-search-service"
            config["consumerGroup"] = consumerGroup

            return Config(config)
        }
    }
}
