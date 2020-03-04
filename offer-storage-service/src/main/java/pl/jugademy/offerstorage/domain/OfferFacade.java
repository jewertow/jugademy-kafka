package pl.jugademy.offerstorage.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jugademy.offerstorage.api.dto.OfferDto;

import java.time.Instant;

@Service
public class OfferFacade {

    private final Logger logger = LoggerFactory.getLogger(OfferFacade.class);

    private final OfferRepository offerRepository;
    private final OfferPublisher offerPublisher;

    @Autowired
    public OfferFacade(OfferRepository offerRepository, OfferPublisher offerPublisher) {
        this.offerRepository = offerRepository;
        this.offerPublisher = offerPublisher;
    }

    @Transactional
    public void createOffer(OfferDto offerDto) {
        Offer offer = Offer.fromDto(offerDto);
        offerRepository.save(offer);
        Instant timestamp = Instant.now();
        offerPublisher.publish(OfferEvent.fromOffer(offer, timestamp));
        logger.info("Successfully saved offer {}", offer.getId());
    }
}
