package com.gridnine.testing;

import java.util.List;

public interface FlightFilterService{
    List<Flight> flightDepartingInThePastRemover(List<Flight> flights);
    List<Flight> flightThatDepartsBeforeItArrivesRemover(List<Flight> flights);
    List<Flight> flightWithMoreThanTwoHoursGroundTimeRemover(List<Flight> flights);
}
