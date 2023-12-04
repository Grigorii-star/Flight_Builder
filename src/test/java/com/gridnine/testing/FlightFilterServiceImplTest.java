package com.gridnine.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightFilterServiceImplTest {
    private final FlightFilterServiceImpl service = new FlightFilterServiceImpl();
    private Flight flight1;
    private Flight flight2;
    private Flight flight3;
    private Flight flight4;
    private Flight flight5;
    private Flight flight6;
    private List<Flight> flights;
    @BeforeEach
    void init() {
        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);
        LocalDateTime threeDaysFromNowPlusHours2 = threeDaysFromNow.plusHours(2);
        LocalDateTime threeDaysFromNowPlusHours3 = threeDaysFromNow.plusHours(3);
        LocalDateTime threeDaysFromNowPlusHours4 = threeDaysFromNow.plusHours(4);
        LocalDateTime threeDaysFromNowPlusHours5 = threeDaysFromNow.plusHours(5);
        LocalDateTime threeDaysFromNowPlusHours6 = threeDaysFromNow.plusHours(6);
        LocalDateTime threeDaysFromNowPlusHours7 = threeDaysFromNow.plusHours(7);
        LocalDateTime threeDaysFromNowMinusHours6 = threeDaysFromNow.minusHours(6);
        LocalDateTime threeDaysFromNowMinusDays6 = threeDaysFromNow.minusDays(6);

        Segment segment1 = new Segment(threeDaysFromNow, threeDaysFromNowPlusHours2);
        Segment segment2 = new Segment(threeDaysFromNowPlusHours3, threeDaysFromNowPlusHours5);
        Segment segment3 = new Segment(threeDaysFromNowMinusDays6, threeDaysFromNow);
        Segment segment4 = new Segment(threeDaysFromNow, threeDaysFromNowMinusHours6);
        Segment segment5 = new Segment(threeDaysFromNowPlusHours5, threeDaysFromNowPlusHours6);
        Segment segment6 = new Segment(threeDaysFromNowPlusHours3, threeDaysFromNowPlusHours4);
        Segment segment7 = new Segment(threeDaysFromNowPlusHours6, threeDaysFromNowPlusHours7);

        List<Segment> list1 = new ArrayList<>();
        list1.add(segment1);
        List<Segment> list2 = new ArrayList<>();
        list2.add(segment1);
        list2.add(segment2);
        List<Segment> list3 = new ArrayList<>();
        list3.add(segment3);
        List<Segment> list4 = new ArrayList<>();
        list4.add(segment4);
        List<Segment> list5 = new ArrayList<>();
        list5.add(segment1);
        list5.add(segment5);
        List<Segment> list6 = new ArrayList<>();
        list6.add(segment1);
        list6.add(segment6);
        list6.add(segment7);

        flight1 = new Flight(list1);
        flight2 = new Flight(list2);
        flight3 = new Flight(list3);
        flight4 = new Flight(list4);
        flight5 = new Flight(list5);
        flight6 = new Flight(list6);

        flights = new ArrayList<>();
        flights.add(flight1);
        flights.add(flight2);
        flights.add(flight3);
        flights.add(flight4);
        flights.add(flight5);
        flights.add(flight6);
    }

    @Test
    void shouldRemoveFlightsDepartingInThePast() {
        List<Flight> expected = new ArrayList<>();
        expected.add(flight1);
        expected.add(flight2);
        expected.add(flight4);
        expected.add(flight5);
        expected.add(flight6);
        List<Flight> serviceResult = service.flightDepartingInThePastRemover(flights);
        assertIterableEquals(expected,serviceResult);
    }

    @Test
    void shouldRemoveFlightsThatDepartsBeforeItArrives() {
        List<Flight> expected = new ArrayList<>();
        expected.add(flight1);
        expected.add(flight2);
        expected.add(flight3);
        expected.add(flight5);
        expected.add(flight6);
        List<Flight> serviceResult = service.flightThatDepartsBeforeItArrivesRemover(flights);
        assertIterableEquals(expected,serviceResult);
    }

    @Test
    void shouldRemoveFlightsWithMoreThanTwoHoursGroundTime() {
        List<Flight> expected = new ArrayList<>();
        expected.add(flight1);
        expected.add(flight2);
        expected.add(flight3);
        expected.add(flight4);
        List<Flight> serviceResult = service.flightWithMoreThanTwoHoursGroundTimeRemover(flights);
        assertIterableEquals(expected,serviceResult);
    }
}