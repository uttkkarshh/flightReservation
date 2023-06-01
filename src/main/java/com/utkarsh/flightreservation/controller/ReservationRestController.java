package com.utkarsh.flightreservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utkarsh.flightreservation.dto.ReservationUpdate;
import com.utkarsh.flightreservation.entities.Reservation;
import com.utkarsh.flightreservation.repos.ReservationRespository;

@RestController
@CrossOrigin
public class ReservationRestController {
      @Autowired
      ReservationRespository reservationRepository;
	@RequestMapping("/reservation/{id}")
	public Reservation findReservation(@PathVariable("id") Long id) {
		
	return reservationRepository.findById(id).get();	
	}
	@RequestMapping("/reservation")
	public Reservation updateReservation(@RequestBody ReservationUpdate request) {
	    Reservation reservation =reservationRepository.findById(request.getId()).get();
	    reservation.setCheckedIn(request.getCheckedIn());
	    reservation.setNumberOfBags(request.getNumberOfBags());
	    Reservation updated=reservationRepository.save(reservation);
		
		return updated;
	}
}
