package com.flightreservation.model.business.manager;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import com.flightreservation.model.business.exception.*;
import com.flightreservation.model.services.factory.ServiceFactory;
import com.flightreservation.model.services.reservationservice.*;
import com.flightreservation.model.services.exception.*;
import com.flightreservation.model.domain.*;

/**
 *  FULL ARGUMENT (PUT THIS IN THE BOX AFTER CHANGING PATH)
 * WARNING:  follow this format exactly, no spaces anywhere. 
 * -Dprop_location:prop_location=C:\Users\rgran\Dropbox\ECLIPSE-WORKSPACE\MSSE670-AIRLINE-RESERVATION\AirlineReservation\config\application.properties
 * 
 * @author Randall Granier
 *
 */
public class FlightReservationManager extends ManagerSuperType {
	
	private static FlightReservationManager myInstance;

	/**
	 * keep the constructor private to prevent instantiation by outside callers.
	 */
	private FlightReservationManager() {
		// construct object . . .
	}

	/**
	 * Assures that there is only one FleetRentalManager created.
	 * @return FleetRentalManager instance
	 */
	public static synchronized FlightReservationManager getInstance() {
		if (myInstance == null) {
			myInstance = new FlightReservationManager();
		}
		return myInstance;
	}

	/**
	 * Generic method that all clients of this class can call to perform certain
	 * actions.
	 * 
	 * @param commandString
	 *            Holds the service name to be invoked *
	 * @param Composite
	 *            Holds application specific domain state
	 * @return false if action failed true if action is successful
	 */
	@Override
	public void performAction(String commandString, Composite composite) {
		Boolean ret = false;
		List<Flight> flightList;
		if (commandString.equals("BOOKRESERVATION")) {
			ret = bookReservation(IReservationService.NAME, composite);
			composite.setRet(ret);
		}
		else if (commandString.equals("LISTFLIGHTS")) {
			flightList = getFlightList(IReservationService.NAME, composite);
			composite.setFlightList((ArrayList)flightList);
			ret = true;
			composite.setRet(ret);
		}

		else {
			 System.out.println("INFO:  Add new workflows here using here using if/else.");
		}
	}

	/**
	 * Method delegates to the ServiceFactory to execute a service. Good part of
	 * this approach is that the Manager knows the service by a string name -
	 * thus achieving the decoupling effect that we so desire in the MVC
	 * approach.
	 *
	 * @param commandString
	 *            contains the service that needs to be performed
	 * @param  Composite
	 *            contains the customer registration info needed
	 *
	 */
	private boolean bookReservation(String commandString, Composite composite) {
			boolean isBooked = false;

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		IReservationService reservationService;

		try {
			reservationService = (IReservationService) serviceFactory.getService(commandString);
			isBooked = reservationService.bookReservation(composite);
		} catch (ServiceLoadException e1) {
			System.out.println("ERROR: FlightReservationManager::failed to load Reservation Service.");																			
		} catch (ReservationException re) {
			System.out.println("ERROR:  FlightReservationManager::bookReservation() failed"); 
			re.printStackTrace();
		} catch (Exception ex) {
			System.out.println("ERROR: FlightReservationManager::Unknown error."); 
		}

		return isBooked;
	}// end bookReservation

	/*
	Get Flight List
	*/
	private List getFlightList(String commandString, Composite composite) {
		List<Flight> flightList = null;

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		IReservationService reservationService;

		try {
			reservationService = (IReservationService) serviceFactory.getService(commandString);
			 flightList = reservationService.listFlights();
		} catch (ServiceLoadException e1) {
			System.out.println("ERROR: FlightReservationManager::failed to load Reservation Service.");
		} catch (ReservationException re) {
			System.out.println("ERROR:  FlightReservationManager::bookReservation() failed");
			re.printStackTrace();
		} catch (Exception ex) {
			System.out.println("ERROR: FlightReservationManager::Unknown error.");
		}

		return flightList;
	}// end bookReservation

}  // end class Flight Reservation Manager
