package pl.jugademy.offercache.domain

import scala.concurrent.Future

trait OfferRepository {
  def upsert(offer: Offer): Future[Unit]
  def findById(id: String): Future[Offer]
}
