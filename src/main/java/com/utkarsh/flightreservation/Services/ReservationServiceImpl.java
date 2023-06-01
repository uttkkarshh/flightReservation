package com.utkarsh.flightreservation.Services;
import com.utkarsh.flightreservation.entities.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import com.utkarsh.flightreservation.dto.ReservationRequest;
import com.utkarsh.flightreservation.entities.Reservation;
import com.utkarsh.flightreservation.repos.FlightRepository;
import com.utkarsh.flightreservation.repos.PassengerRepository;
import com.utkarsh.flightreservation.repos.ReservationRespository;
import com.utkarsh.flightreservation.util.EmailUtil;
import com.utkarsh.flightreservation.util.PDFGenerator;
@Service
public class ReservationServiceImpl implements ReservationService {
	@Value("${com.utkarsh.flightreservation.itinerary.dirpath}")
	private  String ITINERARY_DIR;
@Autowired
	FlightRepository flightRepository;
  @Autowired
  PassengerRepository passengerRepository;
  @Autowired
  ReservationRespository reservationRepository;
	
  @Autowired
  PDFGenerator pdfGenerator;
  @Autowired
  EmailUtil emailUtil;
  private static final Logger LOGGER=LoggerFactory.getLogger(ReservationServiceImpl.class);
  @Override
	public Reservation bookFlight(ReservationRequest request) {
	     //MAKE PAYMENT
	    LOGGER.info("INside bookFlight with request" +request);
		Long flightId =request.getFlightId();
		Flight flight=flightRepository.findById(flightId).get();
		Passenger passenger=new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setPhone(request.getPassengerPhone());
		passenger.setEmail(request.getPassengerEmail());
		LOGGER.info("Saving Passenger"+passenger);
		Passenger savedPassenger=passengerRepository.save(passenger);
		Reservation reservation=new Reservation();
        reservation.setFlight(flight);
        reservation.setPassenger(savedPassenger);
        reservation.setCheckedIn(false);
        Reservation savedReservation =reservationRepository.save(reservation);
        String filePath = ITINERARY_DIR+reservation.getId()+".pdf";
        LOGGER.info("generating pdf ");
		pdfGenerator.generateItinerary(reservation, filePath);
		LOGGER.info("Emailing the Itinerary");
        emailUtil.sendItinerary(passenger.getEmail(), filePath);
		return savedReservation;
	}

}
