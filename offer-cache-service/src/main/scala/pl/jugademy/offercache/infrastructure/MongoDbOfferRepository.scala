package pl.jugademy.offercache.infrastructure

import com.mongodb.client.model.UpdateOptions
import org.mongodb.scala.MongoCollection
import org.mongodb.scala.bson.collection.immutable.Document
import pl.jugademy.offercache.domain.{Offer, OfferRepository}

import scala.concurrent.{ExecutionContext, Future}

class MongoDbOfferRepository(offersCollection: MongoCollection[Document])(
  implicit ex: ExecutionContext
) extends OfferRepository {

  override def findById(id: String): Future[Offer] =
    offersCollection
      .find(Document("_id" -> id))
      .first()
      .toFuture()
      .map { doc =>
        Offer(doc.getString("_id"), doc.getString("name"), doc.getString("description"))
      }

  override def upsert(offer: Offer): Future[Unit] = {
    val filter = Document("_id" -> offer.id)
    val update = Document("$set" -> Document("name" -> offer.name, "description" -> offer.description))
    val upsertOption = new UpdateOptions().upsert(true)
    offersCollection
      .updateOne(filter, update, upsertOption)
      .toFuture()
      .map(_ => ())
  }
}
