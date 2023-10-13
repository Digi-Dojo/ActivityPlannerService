package it.unibz.digidojo.activityplannerservice.domain.model.place;

import lombok.Getter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Getter
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;

    public Place() {
    }

    public void setName(String name) {
        this.name = name;
    }

    @Autowired
    public Place(String name){
        this.name = name;
    }

    @Autowired
    public Place(Long id, String name){
        this.id = id;
        this.name = name;
    }
}