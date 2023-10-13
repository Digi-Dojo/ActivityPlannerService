package it.unibz.digidojo.activityplannerservice.application.kafka.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;
import org.springframework.stereotype.Component;

import it.unibz.digidojo.activityplannerservice.domain.usecase.user.CRUDUser;
import it.unibz.digidojo.sharedmodel.dto.UserDTO;
import it.unibz.digidojo.sharedmodel.event.user.UserCreatedEvent;
import it.unibz.digidojo.sharedmodel.event.user.UserDeletedEvent;

@Component
public class UserConsumer extends BaseConsumer {
    private static final String EVENT_TYPE_PATTERN = "USER_.+";
    public static final RecordFilterStrategy<String, String> filterStrategy = generateFilterStrategy(EVENT_TYPE_PATTERN);
    private final CRUDUser crudUser;

    @Autowired
    public UserConsumer(final CRUDUser crudUser) {
        super();
        this.crudUser = crudUser;
    }

    @KafkaListener(
            topics = "#{@kafkaConfig.topics[T(CRUD).CREATE]}",
            filter = "#{T(UserConsumer).filterStrategy}"
    )
    public void consumeUserCreatedEvent(String jsonMessage) {
        try {
            System.out.printf("Processing a new Kafka event. jsonMessage={%s}", jsonMessage);
            UserCreatedEvent event = marshaller.unmarshal(jsonMessage, UserCreatedEvent.class);
            UserDTO user = event.user();
            String username = user.name();

            crudUser.createUser(username);
            System.out.println(username);
        } catch (Exception e) {
            System.err.println("Error processing user created event: " + e.getMessage());
        }
    }

    @KafkaListener(
            topics = "#{@kafkaConfig.topics[T(CRUD).DELETE]}",
            filter = "#{T(UserConsumer).filterStrategy}"
    )
    public void consumeUserDeletedEvent(String jsonMessage) {
        try {
            System.out.printf("Processing a new Kafka event. jsonMessage={%s}", jsonMessage);
            UserDeletedEvent event = marshaller.unmarshal(jsonMessage, UserDeletedEvent.class);
            Long userId = event.userId();
            crudUser.deleteUser(userId);
        } catch (Exception e) {
            System.err.println("Error processing user deleted event: " + e.getMessage());
        }
    }
}
