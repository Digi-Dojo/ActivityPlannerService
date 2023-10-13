package it.unibz.digidojo.activityplannerservice.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.unibz.digidojo.activityplannerservice.domain.model.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}