package com.flightreservation.model.domain;
import java.util.ArrayList;
import java.util.Objects;

public class Composite {
	private Flight flight;
	private Reservation reservation;
	private Traveler traveler;
	private ArrayList<Flight> flightList;
	private ArrayList<Reservation> reservationList;
	
	// Constructor
	public Composite() {
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public Traveler getTraveler() {
		return traveler;
	}

	public void setTraveler(Traveler traveler) {
		this.traveler = traveler;
	}

	public ArrayList<Flight> getFlightList() {
		return flightList;
	}

	public void setFlightList(ArrayList<Flight> flightList) {
		this.flightList = flightList;
	}

	public ArrayList<Reservation> getReservationList() {
		return reservationList;
	}

	public void setReservationList(ArrayList<Reservation> reservationList) {
		this.reservationList = reservationList;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Composite)) return false;
		Composite composite = (Composite) o;
		return Objects.equals(flight, composite.flight) && Objects.equals(reservation, composite.reservation) && Objects.equals(traveler, composite.traveler) && Objects.equals(flightList, composite.flightList) && Objects.equals(reservationList, composite.reservationList);
	}

	@Override
	public int hashCode() {
		return Objects.hash(flight, reservation, traveler, flightList, reservationList);
	}

	@Override
	public String toString() {
		return "Composite{" +
				"flight=" + flight +
				", reservation=" + reservation +
				", traveler=" + traveler +
				", flightList=" + flightList +
				", reservationList=" + reservationList +
				'}';
	}
}
