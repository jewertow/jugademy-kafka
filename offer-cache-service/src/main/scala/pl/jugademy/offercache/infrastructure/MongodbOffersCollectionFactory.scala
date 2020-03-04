package pl.jugademy.offercache.infrastructure

import ch.qos.logback.classic.LoggerContext
import ch.qos.logback.classic.Level.ERROR
import org.mongodb.scala.{MongoClient, MongoCollection}
import org.mongodb.scala.bson.collection.immutable.Document
import org.slf4j.LoggerFactory

object MongodbOffersCollectionFactory {

  def provide(config: Config): MongoCollection[Document] = {
    val username = config.getProperty("mongodbUsername")
    val password = config.getProperty("mongodbPassword")
    val host = config.getProperty("mongodbHost")
    val port = config.getProperty("mongodbPort")
    val database = config.getProperty("mongodbDatabase")
    val mongoClient = MongoClient(s"mongodb://$username:$password@$host:$port/?authSource=$database")
    val db = mongoClient.getDatabase(database)
    val offersCollection = db.getCollection("offers")

    // disable DEBUG logs
    val loggerContext = LoggerFactory.getILoggerFactory.asInstanceOf[LoggerContext]
    val mongoLogger = loggerContext.getLogger("org.mongodb.driver")
    mongoLogger.setLevel(ERROR)

    offersCollection
  }
}
