package it.unibz.digidojo.activityplannerservice.domain;

import it.unibz.digidojo.activityplannerservice.domain.model.calendar.CalendarEvent;
import it.unibz.digidojo.activityplannerservice.domain.model.calendar.CreateCalendarEventDTO;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CalendarEventControllerIntegrationTest {

    private int port = 8080;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createCalendarEventTest() throws Exception {

        // Preparing the DTO
        CreateCalendarEventDTO calendarEventDTO = new CreateCalendarEventDTO();
        calendarEventDTO.setTitle("Test Event");
        calendarEventDTO.setDescription("This is a test event");
        calendarEventDTO.setStartDate(LocalDateTime.now());
        calendarEventDTO.setEndDate(LocalDateTime.now().plusDays(1));

        // Post Request to create an event
        ResponseEntity<CalendarEvent> responseEntity =
                this.restTemplate.postForEntity("http://localhost:" + port + "/api/calendar-events/create", calendarEventDTO, CalendarEvent.class);

        // Checks
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        CalendarEvent createdEvent = responseEntity.getBody();
        assertThat(createdEvent).isNotNull();
        assertThat(createdEvent.getTitle()).isEqualTo(calendarEventDTO.getTitle());
        assertThat(createdEvent.getDescription()).isEqualTo(calendarEventDTO.getDescription());
        assertThat(createdEvent.getStartDate()).isEqualTo(calendarEventDTO.getStartDate());
        assertThat(createdEvent.getEndDate()).isEqualTo(calendarEventDTO.getEndDate());
        //You may want to add assertions for place and user
    }
}