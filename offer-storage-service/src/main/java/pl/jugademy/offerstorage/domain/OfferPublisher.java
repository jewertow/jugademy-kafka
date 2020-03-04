package pl.jugademy.offerstorage.domain;

public interface OfferPublisher {
    void publish(OfferEvent offerEvent);
}
