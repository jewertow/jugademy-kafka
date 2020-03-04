package pl.jugademy.offercache.domain

trait OfferSubscriber {
  def subscribe(consume: OfferEvent => Unit): Unit
}
