package it.unibz.digidojo.activityplannerservice.domain.usecase.place;

import it.unibz.digidojo.activityplannerservice.domain.repository.PlaceRepository;
import it.unibz.digidojo.activityplannerservice.domain.model.place.Place;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SearchPlace {
    PlaceRepository placeRepository;

    @Autowired
    public SearchPlace(PlaceRepository placeRepository){
        this.placeRepository = placeRepository;
    }

    public Place findById(Long id){
        Optional<Place> place = placeRepository.findById(id);
        if (place.isEmpty()) throw new IllegalArgumentException("Place with id '" + id + "' does not exist");
        return place.get();
    }

    public List<Place> getAll(){
        List<Place> places = placeRepository.findAll();
        System.out.println("\nPlaceList size: " + places.size());
        return places;
    }
}