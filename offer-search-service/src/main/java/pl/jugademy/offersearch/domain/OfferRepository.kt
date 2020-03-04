package pl.jugademy.offersearch.domain

interface OfferRepository {
    fun save(offer: Offer)
    fun getByNameOrDescription(name: String, description: String): List<Offer>
}
