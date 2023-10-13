//package it.unibz.taskcalendarservice.database;
//
//import it.unibz.taskcalendarservice.common.domain.Place;
//import it.unibz.taskcalendarservice.common.domain.User;
//import CalendarEventController;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.function.Executable;
//
//import java.sql.SQLException;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class DatabaseConfigTest {
//
//    @Test
//    void createCalendarEventTest() throws SQLException {
//
//        CalendarEventController cec0 = new CalendarEventController("",null, null);
//        assertNull(cec0,"Method should return null");
//
//        User user0 = new User(1L,"John");
//        Place place0 = new Place(1L,"Something");
//
//        LocalDate date1 = LocalDate.of(2023, 3, 20);
//        LocalTime time1 = LocalTime.of(22,0,0);
//        LocalDateTime startDate = LocalDateTime.of(date1,time1);
//
//        LocalDate date2 =  LocalDate.of(2023, 3, 21);
//        LocalTime time2 = LocalTime.of(24,0,0);
//        LocalDateTime endDate = LocalDateTime.of(date2,time2);
//
//        String[] list = {"Something, Otherthing"};
//        List<String> tags = Arrays.asList(list);
//
//        CalendarEventController cec1 = new CalendarEventController("First Calendar", startDate, endDate, user0, tags );
//        assertAll((Executable) cec1);
//
//        assertNotNull(cec1.getCalendarUser());
//        assertNotNull(cec1.getCalendarEvent());
//        assertNotNull(cec1.getCalendarPlace());
//
//        cec1.createCalendarEvent();
//        /*Task task1 = new Task("New task", null);
//        task1.setUser(user0);
//        task1.setTags(tags);
//        assertNotNull(task1.getId());
//        assertTrue(task1.getDescription().length()>0);
//
//        cec1.updateCalendarEvent(task1.getId().intValue());
//
//        assertNotNull(cec1.getCalendarTags(task1.getId().intValue()));
//        assertNotNull(cec1.getCalendarDescription(task1.getId().intValue()));
//
//        assertEquals(task1.getDescription(),"New task");*/
//
//
//
//    }
//}
