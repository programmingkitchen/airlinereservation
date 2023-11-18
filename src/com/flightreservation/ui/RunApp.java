package com.flightreservation.ui;
import java.util.List;
import java.time.LocalDateTime;

import com.flightreservation.model.business.manager.*;
import com.flightreservation.model.services.factory.ServiceFactory;
import com.flightreservation.model.services.reservationservice.IReservationService;
import com.flightreservation.model.domain.*;
import com.flightreservation.model.business.exception.*;
import com.flightreservation.model.services.exception.ReservationException;

/**
 * We need to pass in the app.properties file using the -D option.  We configure this in:
 * 
 * Run As (right click on project > 
 * Run Configurations (select from menu) > 
 * Arguments (tab of new window) > 
 * VM Arguments (bottom window)
 * 
 * FULL ARGUMENT (PUT THIS IN THE BOX AFTER CHANGING PATH)
 * WARNING:  follow this format exactly, no spaces anywhere. 
 * -Dprop_location:prop_location=C:\Users\rgran\Dropbox\ECLIPSE-WORKSPACE\MSSE670-AIRLINE-RESERVATION\AirlineReservation\config\application.properties
 *
		 * `-Dprop_location=D:\Dropbox\JAVA-MSSE670\WORKSPACE\AirlineReservation\config\application.properties`
 *
 * In IntelliJ
 * Click the 3 dots next to the arrow and the bug.
 * "Add VM Properties"
 * Put the path below that.
 *
 * @author Randall Granier
 *
 */

public class RunApp {
	
	/**
	 * Changed the old "ServiceFactory" to "SimpleServiceFactory."   We still have both options.
	 * The newly renamed SimpleServiceFactory was called with the following code:
	 * 
	 * SimpleServiceFactory factory = new SimpleServiceFactory();
	 * IReservationService reservation = factory.getReservationService();
	 *
	 * @param args
	 */

public static void main(String[] args) {

	String message = "";
	Boolean isBooked;
	Boolean ret;
	FlightReservationManager manager = FlightReservationManager.getInstance();

	// Reservation #1

	Traveler traveler1 = new Traveler(2001, "Miles", "Davis", "miles@jazz.com");
	Flight flight1 = new Flight(200, "New York", "Los Angeles",
			LocalDateTime.parse("2021-04-15T12:00"),
			LocalDateTime.parse("2021-04-15T14:00"),
			"Airbus A320", 150);

	// Reservation #1
	Traveler traveler2 = new Traveler(3001, "Jaco", "Pastorious", "jaco@jazz.com");
	Flight flight2 = new Flight(300, "Miami", "Seattle",
			LocalDateTime.parse("2022-03-15T12:00"),
			LocalDateTime.parse("2022-03-15T14:00"),
			"Airbus A320", 150);


	Composite composite = new Composite();

	/**
	 * Reservation #1
	 */
	System.out.println();
	composite.setTraveler(traveler1);
	composite.setFlight(flight1);
	isBooked = manager.performAction("BOOKRESERVATION", composite);

	// Ignore the weird code in the sample code, which does the same thing as this.   It's a Java short cut.
	if (isBooked) {
		message = "SUCCESS:  FlightReservationMain:: - Traveler resistered.";
	} else {
		message = "FAIL:  FlightReservationMain:: - Traveler not registered.";
	}
	System.out.println(message);

	/**
	 * Reservation #2
	 * Add a second reservation (test the simulator database)
	 * The bug in the simulator is that the previous reservation was not persisted.
	 * Each time we call the manager with manager.performAction(), we get a new instance, which clears
	 * memory.
	 * 
	 * Wait for the DB to fix.
	 */
	System.out.println();
	composite.setTraveler(traveler2);
	composite.setFlight(flight2);
	isBooked = manager.performAction("BOOKRESERVATION", composite);

	if (isBooked) {
		message = "SUCCESS:  FlightReservationMain:: - Traveler resistered.";
	} else {
		message = "FAIL:  FlightReservationMain:: - Traveler not registered.";
	}
	System.out.println(message);

	/**
	 * LIST FLIGHTS
	 */
	System.out.println();
	manager.performAction("LISTFLIGHTS", composite);
	List<Flight> flightList = composite.getFlightList();

	for (Flight f : flightList) {
		System.out.println("Flight ID: " + f.getId());
		System.out.println("Origin Airport: " + f.getOriginAirport() + "\t\tDeparture Time: " + f.getDepartureTime());
		System.out.println("Destination Airport: " + f.getOriginAirport() + "\t\tArrival Time: " + f.getArrivalTime());
	}

} // end main



} // end class RunApp
