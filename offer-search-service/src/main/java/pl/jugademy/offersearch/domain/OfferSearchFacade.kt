package pl.jugademy.offersearch.domain

class OfferSearchFacade(private val offerRepository: OfferRepository) {
    fun findByNameOrDescription(query: String): List<Offer> =
        offerRepository.getByNameOrDescription(name = query, description = query)

    fun consumeOfferEvent(offerEvent: OfferEvent) {
        val offer = Offer.fromEvent(offerEvent)
        offerRepository.save(offer)
    }
}
