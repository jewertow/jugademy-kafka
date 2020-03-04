package pl.jugademy.offercache.domain

import pl.jugademy.offercache.api.OfferDto

import scala.concurrent.{ExecutionContext, Future}

class OfferFacade(offerRepository: OfferRepository)(implicit ec: ExecutionContext) {
  def findOfferById(offerId: String): Future[OfferDto] =
    offerRepository.findById(offerId).map { offer =>
      OfferDto(offer.id, offer.name, offer.description)
    }

  def consumeOfferEvent(offerEvent: OfferEvent): Future[Unit] = {
    val offer = Offer(offerEvent.id, offerEvent.name, offerEvent.description)
    offerRepository.upsert(offer)
  }
}
