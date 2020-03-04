package pl.jugademy.offercache.domain

import java.time.Instant

case class OfferEvent(id: String, name: String, description: String, timestamp: Instant)
