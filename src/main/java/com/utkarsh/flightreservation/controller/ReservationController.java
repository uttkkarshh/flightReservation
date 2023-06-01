package com.utkarsh.flightreservation.controller;
import com.utkarsh.flightreservation.Services.ReservationService;
import com.utkarsh.flightreservation.dto.ReservationRequest;
import com.utkarsh.flightreservation.entities.Flight;
import com.utkarsh.flightreservation.entities.Reservation;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.utkarsh.flightreservation.repos.FlightRepository;


@Controller
public class ReservationController {
	@Autowired
	FlightRepository flightRepository;
	private static final Logger LOGGER=LoggerFactory.getLogger(ReservationController.class);
	@Autowired
	ReservationService reservationService;
	@RequestMapping("/showCompleteReservation")
    public String showCompleteReservation(@RequestParam("flightId") Long flightId,ModelMap m) {
		 LOGGER.info("Indide ShowCompleteResrvation with Flight Id:"+flightId);
		Flight flight=flightRepository.findById(flightId).get();
		 System.out.print(flight);
		 m.addAttribute("flight", flight);
   	 return "completeReservation";
   	 
   	 
    }
	@RequestMapping(value="completeReservation",method=RequestMethod.POST)
	public String completeReservation(ReservationRequest reservationRequest,ModelMap m) {
		 LOGGER.info("Inside complteResevation for"+reservationRequest);
		Reservation reservation=reservationService.bookFlight(reservationRequest);
		m.addAttribute("msg", "Rservation successfull and id is"+reservation.getId());
		return "reservationConfirmation";
	}
	
}
