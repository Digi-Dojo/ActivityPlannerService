package it.unibz.digidojo.activityplannerservice.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.unibz.digidojo.activityplannerservice.domain.model.calendar.CalendarEvent;

@Repository
public interface CalendarEventRepository extends JpaRepository<CalendarEvent, Long>{
}