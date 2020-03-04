package pl.jugademy.offerstorage.infrastructure;

import pl.jugademy.offerstorage.domain.Offer;
import pl.jugademy.offerstorage.domain.OfferRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SpringJpaOfferRepository extends JpaRepository<Offer, Long> {
}
