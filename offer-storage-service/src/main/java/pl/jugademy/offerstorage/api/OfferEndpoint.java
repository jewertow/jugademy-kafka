package pl.jugademy.offerstorage.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.jugademy.offerstorage.api.dto.OfferDto;
import pl.jugademy.offerstorage.domain.OfferFacade;

@RestController
public class OfferEndpoint {

    private final OfferFacade offerFacade;

    public OfferEndpoint(OfferFacade offerFacade) {
        this.offerFacade = offerFacade;
    }

    @PostMapping("/offers")
    public void createOffer(@RequestBody OfferDto offer) {
        this.offerFacade.createOffer(offer);
    }
}
