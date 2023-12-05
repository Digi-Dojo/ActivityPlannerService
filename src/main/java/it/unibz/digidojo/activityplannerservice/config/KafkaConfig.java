package it.unibz.digidojo.activityplannerservice.config;

import java.util.Map;

import org.springframework.context.annotation.Configuration;

import it.unibz.digidojo.activityplannerservice.util.CRUD;

@Configuration
public class KafkaConfig {
    public static final String CREATE_TOPIC = "whacponi-create";
    public static final String UPDATE_TOPIC = "whacponi-update";
    public static final String DELETE_TOPIC = "whacponi-delete";
    public static final Map<CRUD, String> TOPICS = Map.of(
            CRUD.CREATE, CREATE_TOPIC,
            CRUD.UPDATE, UPDATE_TOPIC,
            CRUD.DELETE, DELETE_TOPIC
    );
    public static final String EVENT_TYPE_KEY = "eventType";
}
