package it.unibz.digidojo.activityplannerservice.domain.repository;

import it.unibz.digidojo.activityplannerservice.domain.model.place.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {
}
