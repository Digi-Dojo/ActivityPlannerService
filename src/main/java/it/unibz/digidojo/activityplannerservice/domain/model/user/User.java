package it.unibz.digidojo.activityplannerservice.domain.model.user;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;

    public User() {
    }

    public User(String name){
        this.name = name;
    }

    public User(Long id, String name){
        this.id = id;
        this.name = name;
    }

}