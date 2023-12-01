package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        System.out.println();
        for (Flight f : flights) {
            System.out.println(f);
        }
        System.out.println("------------------------------------------------------------------------------------------------------");
        filter1();
        System.out.println("------------------------------------------------------------------------------------------------------");
        filter2();
        System.out.println("------------------------------------------------------------------------------------------------------");
        filter3();
        System.out.println("------------------------------------------------------------------------------------------------------");
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

        for (Flight flight : flights) {
            List<Segment> segments = flight.getSegments();
            if (segments.size() > 1) {
                LocalDateTime arrival = null;
                LocalDateTime departure;
                int waitingPeriod = 0;

                for (int i = 0; i < segments.size(); i++) {
                    if (i == 0) {
                        Segment rightPart = segments.get(i);
                        arrival = rightPart.getArrivalDate();
                    }
                    else {
                        Segment leftPart = segments.get(i);
                        departure = leftPart.getDepartureDate();
                        Duration duration = Duration.between(arrival, departure);
                        waitingPeriod += duration.toHoursPart();
                        Segment rightPart = segments.get(i);
                        arrival = rightPart.getArrivalDate();
                    }
                }
                if (waitingPeriod < 3) {
                    System.out.println(flight);
                }
            } else {
                System.out.println(flight);
            }
        }
    }
}