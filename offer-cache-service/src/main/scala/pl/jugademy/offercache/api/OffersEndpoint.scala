package pl.jugademy.offercache.api

import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import org.slf4j.LoggerFactory
import pl.jugademy.offercache.domain.OfferFacade

class OffersEndpoint(offerFacade: OfferFacade) extends OfferDtoSerializer {
  private val logger = LoggerFactory.getLogger(classOf[OffersEndpoint].getName)

  def getOfferRoute: Route =
    path("offers" / Segment) { offerId =>
      logger.info(s"Received request offerId = $offerId")
      get {
        val offerDto = offerFacade.findOfferById(offerId)
        onSuccess(offerDto) { offer =>
          complete(OK, offer)
        }
      }
  }
}
