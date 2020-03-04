package pl.jugademy.offerstorage.infrastructure;

import org.springframework.stereotype.Repository;
import pl.jugademy.offerstorage.domain.Offer;
import pl.jugademy.offerstorage.domain.OfferRepository;

@Repository
public class JpaOfferRepository implements OfferRepository {

    private final SpringJpaOfferRepository springJpaOfferRepository;

    public JpaOfferRepository(SpringJpaOfferRepository springJpaOfferRepository) {
        this.springJpaOfferRepository = springJpaOfferRepository;
    }

    @Override
    public void save(Offer offer) {
        springJpaOfferRepository.save(offer);
    }
}
