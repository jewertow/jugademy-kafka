package pl.jugademy.offersearch.domain

import java.time.Instant

data class Offer(val id: Long, val name: String, val description: String) {
    companion object {
        fun fromEvent(offerEvent: OfferEvent): Offer =
                Offer(offerEvent.id, offerEvent.name, offerEvent.description)
    }
}

data class OfferEvent(val id: Long, val name: String, val description: String, val timestamp: Instant)
