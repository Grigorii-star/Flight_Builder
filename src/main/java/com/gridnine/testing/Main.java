package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        for (Flight f:flights) {
            System.out.println(f);
        }
        System.out.println();

        filter1();
        System.out.println();
        filter2();
        System.out.println();
        filter3();
    }

    public static void filter1() {
        List<Flight> flights = FlightBuilder.createFlights();

        flights.forEach(flight -> {
                    if (flight.getSegments().stream()
                            .anyMatch(segment -> !segment.getDepartureDate().isBefore(LocalDateTime.now()))) {
                        System.out.println(flight);
                    }
                });
    }

    public static void filter2() {
        List<Flight> flights = FlightBuilder.createFlights();

        flights.forEach(flight -> {
            if (flight.getSegments().stream()
                    .anyMatch(segment -> !segment.getDepartureDate().isAfter(segment.getArrivalDate()))) {
                System.out.println(flight);
            }
        });
    }

    public static void filter3() {
        List<Flight> flights = FlightBuilder.createFlights();

//        for (Flight f:flights) {
//            List<Segment> segments = f.getSegments();
////            for (Segment s:segments) {
////                Duration duration = Duration.between(s.getDepartureDate(), s.getArrivalDate());
////                System.out.println(duration.toHoursPart());
////            }
//            for (int i = 0; i < segments.size(); i++) {
//                for (int j = i+1; j < segments.size(); j++) {
//                    Long duration1 =
//                }
//            }
//        }
    }
}