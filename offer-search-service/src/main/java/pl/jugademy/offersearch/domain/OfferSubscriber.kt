package pl.jugademy.offersearch.domain

interface OfferSubscriber {
    fun subscribe(consume: (offer: OfferEvent) -> Unit)
}
