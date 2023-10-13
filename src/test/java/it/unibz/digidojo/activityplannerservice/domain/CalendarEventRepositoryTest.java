//package it.unibz.taskcalendarservice.domain;
//
//import it.unibz.taskcalendarservice.common.domain.Place;
//import it.unibz.taskcalendarservice.common.domain.User;
//import CalendarEvent;
//import CalendarEventController;
//import CalendarEventRepository;
//import it.unibz.taskcalendarservice.domain.calendar.CalendarEventService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@RunWith(MockitoJUnitRunner.class)
//public class CalendarEventRepositoryTest {
//    @InjectMocks
//    private CalendarEventRepository calendarEventRepository;
//
//    @Mock
//    private CalendarEventService calendarEventDAO;  // database access object, even though I suppose that to instantiate should have been the controller! But if we do so, the method findAll doesn't exist in the controller
//
//    @Test
//    public void testFindAll() {
//        // Define test data
//        CalendarEvent calendarEvent1 = new CalendarEvent("first event", LocalDateTime.of(2001, 03, 17, 10, 15, 30), LocalDateTime.of(2001, 03, 17, 10, 15, 30), new User((long) Math.pow(-9.223372,18),"Mirko"), new ArrayList<>());
//        CalendarEvent calendarEvent2 = new CalendarEvent("Birthday Party", LocalDateTime.of(2023, 04, 30, 18, 30, 0), LocalDateTime.of(2023, 04, 30, 22, 0, 0), new User(1234567890L, "John"),new ArrayList<>());
//
//        // continue to do other tests for all possible contructors and add to the expectedEvents list!
//
//        List<CalendarEvent> expectedEvents = Arrays.asList(calendarEvent1, calendarEvent2);
//
//        // Mock the DAO method
//        Mockito.when(calendarEventDAO.findAll()).thenReturn(expectedEvents);
//
//        // Call the repository method
//        List<CalendarEvent> actualEvents = calendarEventRepository.findAll();
//
//        // Assert the results for the calendarEvent1
//        assertEquals(expectedEvents.size(), actualEvents.size());
//        assertEquals(expectedEvents.get(0).getDescription(), actualEvents.get(0).getDescription());
//        assertEquals(expectedEvents.get(0).getStartDate(), actualEvents.get(0).getStartDate());
//        assertEquals(expectedEvents.get(0).getEndDate(), actualEvents.get(0).getEndDate());
//        assertEquals(expectedEvents.get(0).getUser(), actualEvents.get(0).getUser());
//        assertEquals(expectedEvents.get(0).getTags(), actualEvents.get(0).getTags());
//
//        // Assert the results for the calendarEvent2
//        assertEquals(expectedEvents.get(1).getDescription(), actualEvents.get(1).getDescription());
//        assertEquals(expectedEvents.get(1).getStartDate(), actualEvents.get(1).getStartDate());
//        assertEquals(expectedEvents.get(1).getEndDate(), actualEvents.get(1).getEndDate());
//        assertEquals(expectedEvents.get(1).getUser(), actualEvents.get(1).getUser());
//        assertEquals(expectedEvents.get(1).getTags(), actualEvents.get(1).getTags());
//
//        // Verify the DAO method is called
//        Mockito.verify(calendarEventDAO, Mockito.times(1)).findAll();
//    }
//}
//
