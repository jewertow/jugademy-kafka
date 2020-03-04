package pl.jugademy.offercache.api

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

trait OfferDtoSerializer extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val offerDtoFormat: RootJsonFormat[OfferDto] = jsonFormat3(OfferDto)
}

case class OfferDto(id: String, name: String, description: String)
