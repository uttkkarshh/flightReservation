package com.utkarsh.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utkarsh.flightreservation.entities.Reservation;


public interface ReservationRespository extends JpaRepository<Reservation, Long> {

}
