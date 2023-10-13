package it.unibz.digidojo.activityplannerservice.application.kafka.consumer;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;
import org.springframework.stereotype.Component;

import it.unibz.digidojo.activityplannerservice.domain.model.place.Place;
import it.unibz.digidojo.sharedmodel.dto.PlaceDTO;
import it.unibz.digidojo.sharedmodel.event.place.PlaceCreatedEvent;
import it.unibz.digidojo.sharedmodel.event.place.PlaceDeletedEvent;

@Component
@Getter
public class PlaceConsumer extends BaseConsumer {
    private static final String EVENT_TYPE_PATTERN = "CALENDAR_EVENT_.+";
    public static final RecordFilterStrategy<String, String> filterStrategy = generateFilterStrategy(EVENT_TYPE_PATTERN);

    private final List<Place> placeList = new ArrayList<>();

    @Autowired
    public PlaceConsumer() {
        super();
    }

    //TODO: add to the database
    @KafkaListener(
            topics = "#{@kafkaConfig.topics[T(CRUD).CREATE]}",
            filter = "#{T(PlaceConsumer).filterStrategy}"
    )
    public void consumePlaceCreatedEvent(String jsonMessage) {
        try {
            System.out.printf("Processing a new Kafka event. jsonMessage={%s}", jsonMessage);
            PlaceCreatedEvent event = marshaller.unmarshal(jsonMessage, PlaceCreatedEvent.class);
            PlaceDTO place = event.place();

            //list of created Places
            placeList.add(new Place(place.id(), place.name()));
            System.out.println(place.name());
        } catch (Exception e) {
            System.err.println("Error processing place created event: " + e.getMessage());
        }
    }

    @KafkaListener(
            topics = "#{@kafkaConfig.topics[T(CRUD).DELETE]}",
            filter = "#{T(PlaceConsumer).filterStrategy}"
    )
    public void consumePlaceDeletedEvent(String jsonMessage) {
        try {
            System.out.printf("Processing a new Kafka event. jsonMessage={%s}", jsonMessage);
            PlaceDeletedEvent event = marshaller.unmarshal(jsonMessage, PlaceDeletedEvent.class);
            Long placeId = event.placeId();
            placeList.removeIf(place -> place.getId().equals(placeId));
        } catch (Exception e) {
            System.err.println("Error processing place deleted event: " + e.getMessage());
        }
    }
}
