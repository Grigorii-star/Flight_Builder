package com.gridnine.testing;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        FlightFilterService service = new FlightFilterServiceImpl();
        List<Flight> flights = FlightBuilder.createFlights();

        System.out.println();
        System.out.println("All flights:");
        printer(flights);
        System.out.println();
        System.out.println("Removed flights departing in the past:");
        List<Flight> filter1 = service.flightDepartingInThePastRemover(flights);
        printer(filter1);
        System.out.println();
        System.out.println("Removed flights that departs before it arrives:");
        List<Flight> filter2 = service.flightThatDepartsBeforeItArrivesRemover(flights);
        printer(filter2);
        System.out.println();
        System.out.println("Removed flights with more than two hours ground time:");
        List<Flight> filter3 = service.flightWithMoreThanTwoHoursGroundTimeRemover(flights);
        printer(filter3);
    }
    private static void printer(List<Flight> flights) {
        for (Flight f : flights) {
            System.out.println(f);
        }
    }
}