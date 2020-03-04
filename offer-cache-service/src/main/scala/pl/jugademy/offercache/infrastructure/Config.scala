package pl.jugademy.offercache.infrastructure

object Config {
  private val config = collection.mutable.Map[String, String]()

  def load(): Config = {
    val bootstrapServers = Option(System.getProperty("kafka.consumer.bootstrap")).getOrElse("localhost:9092")
    config.put("bootstrapServers", bootstrapServers)

    val topic = Option(System.getProperty("kafka.consumer.topic")).getOrElse("pl.jugademy.OfferChanged")
    config.put("topic", topic)

    val consumerGroup = Option(System.getProperty("kafka.consumer.group")).getOrElse("offer-search-service")
    config.put("consumerGroup", consumerGroup)

    val mongodbHost = Option(System.getProperty("mongodb.host")).getOrElse("localhost")
    config("mongodbHost") = mongodbHost

    val mongodbPort = Option(System.getProperty("mongodb.port")).getOrElse("27017")
    config("mongodbPort") = mongodbPort

    val mongodbUsername = Option(System.getProperty("mongodb.username")).getOrElse("admin")
    config("mongodbUsername") = mongodbUsername

    val mongodbPassword = Option(System.getProperty("mongodb.password")).getOrElse("admin")
    config("mongodbPassword") = mongodbPassword

    val mongodbDatabase = Option(System.getProperty("mongodb.database")).getOrElse("offers")
    config("mongodbDatabase") = mongodbDatabase

    new Config(config.toMap)
  }
}

class Config(config: Map[String, String]) {
  def getProperty(key: String): String =
    config(key)
}
