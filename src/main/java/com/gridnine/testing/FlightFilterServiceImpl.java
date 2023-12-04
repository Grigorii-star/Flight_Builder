package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for filtering a list of flights.
 */
public class FlightFilterServiceImpl implements FlightFilterService{
    /**
     * The method removes the flights until the current moment in time.
     * @param flights receives a list of flights.
     * @return the result of filtration.
     */
    @Override
    public List<Flight> flightDepartingInThePastRemover(List<Flight> flights) {
        List<Flight> result = new ArrayList<>();

        flights.forEach(flight -> {
            if (flight.getSegments().stream()
                    .anyMatch(segment -> !segment.getDepartureDate().isBefore(LocalDateTime.now()))) {
                result.add(flight);
            }
        });
        return result;
    }

    /**
     * The method removes departures from the arrival date earlier.
     * @param flights receives a list of flights.
     * @return the result of filtration.
     */
    @Override
    public List<Flight> flightThatDepartsBeforeItArrivesRemover(List<Flight> flights) {
        List<Flight> result = new ArrayList<>();

        flights.forEach(flight -> {
            if (flight.getSegments().stream()
                    .anyMatch(segment -> !segment.getDepartureDate().isAfter(segment.getArrivalDate()))) {
                result.add(flight);
            }
        });
        return result;
    }

    /**
     * The method removes departures with the total time of staying on the ground exceeding 3 hours.
     * @param flights receives a list of flights.
     * @return the result of filtration.
     */
    @Override
    public List<Flight> flightWithMoreThanTwoHoursGroundTimeRemover(List<Flight> flights) {
        List<Flight> result = new ArrayList<>();

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
                    result.add(flight);
                }
            } else {
                result.add(flight);
            }
        }
        return result;
    }
}
