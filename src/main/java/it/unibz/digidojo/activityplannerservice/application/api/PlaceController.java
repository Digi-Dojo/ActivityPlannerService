package it.unibz.digidojo.activityplannerservice.application.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unibz.digidojo.activityplannerservice.domain.model.place.CreatePlaceDTO;
import it.unibz.digidojo.activityplannerservice.domain.model.place.Place;
import it.unibz.digidojo.activityplannerservice.domain.usecase.place.CRUDPlace;
import it.unibz.digidojo.activityplannerservice.domain.usecase.place.SearchPlace;

@CrossOrigin
@RestController
@RequestMapping(path = "api/place")
public class PlaceController {

    private final CRUDPlace crudPlace;
    private final SearchPlace searchPlace;

    @Autowired
    public PlaceController(CRUDPlace crudPlace, SearchPlace searchPlace) {
        this.crudPlace = crudPlace;
        this.searchPlace = searchPlace;
    }

    @PostMapping("/{id}")
    public Place findById(@PathVariable("id") Long id) {
        return searchPlace.findById(id);
    }

    @PostMapping("/create")
    public Place createPlace(@RequestBody CreatePlaceDTO createPlaceDTO) {
        return crudPlace.createPlace(createPlaceDTO.getName());
    }

    @PostMapping("/update/{id}")
    public Place updatePlace(@PathVariable("id") Long id, String name) {
        return crudPlace.updatePlace(id, name);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteTask(@PathVariable("id") Long id) {
        return crudPlace.deletePlace(id);
    }

    @GetMapping("/getAll")
    public List<Place> getAll() {
        return searchPlace.getAll();
    }
}