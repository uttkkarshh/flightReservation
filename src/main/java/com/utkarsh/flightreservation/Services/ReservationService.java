package com.utkarsh.flightreservation.Services;

import com.utkarsh.flightreservation.dto.ReservationRequest;
import com.utkarsh.flightreservation.entities.Reservation;

public interface ReservationService {
      public  Reservation bookFlight(ReservationRequest request) ;
}
