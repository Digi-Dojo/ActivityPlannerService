package it.unibz.digidojo.activityplannerservice.domain.usecase.place;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

import it.unibz.digidojo.activityplannerservice.domain.repository.PlaceRepository;
import it.unibz.digidojo.activityplannerservice.domain.model.place.Place;

@Component
public class CRUDPlace {

    private final PlaceRepository placeRepository;
    private final SearchPlace searchPlace;

    @Autowired
    public CRUDPlace(PlaceRepository placeRepository, SearchPlace searchPlace) {
        this.placeRepository = placeRepository;
        this.searchPlace = searchPlace;
    }

    public Place createPlace(String name){
        return placeRepository.save(new Place(name));
    }

    public Place updatePlace(Long placeID,String name){
        Place toBeModified = searchPlace.findById(placeID);
        toBeModified.setName(name);
        return placeRepository.save(toBeModified);
    }

    public boolean deletePlace(Long id){
        Optional<Place> toBeRemoved = placeRepository.findById(id);
        if(toBeRemoved.isEmpty()) throw new IllegalArgumentException("Place with id '" + id + "' does not exist");
        placeRepository.delete(toBeRemoved.get());
        try{
            placeRepository.findById(id);
        }catch (Exception e){
            System.out.println("Place successfully deleted");
            return true;
        }
        System.out.println("Process unsuccessful, Place has not been deleted");
        return false;
    }
}
