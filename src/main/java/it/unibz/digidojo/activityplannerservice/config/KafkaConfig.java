package it.unibz.digidojo.activityplannerservice.config;

import java.util.Map;

import org.springframework.context.annotation.Configuration;

import it.unibz.digidojo.activityplannerservice.util.CRUD;

@Configuration
public class KafkaConfig {
    public static final Map<CRUD, String> topics = Map.of(
            CRUD.CREATE, "whacponi-create",
            CRUD.UPDATE, "whacponi-update",
            CRUD.DELETE, "whacponi-delete"
    );

    public static final String EVENT_TYPE_KEY = "eventType";
}
