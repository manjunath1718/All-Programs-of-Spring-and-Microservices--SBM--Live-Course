package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Passenger;
import com.example.model.Ticket;
import com.example.service.ITicketBookingService;

@RestController
@RequestMapping("/provider/api")
public class TicketBookingRestController {
	
	@Autowired
	ITicketBookingService service;

	@PostMapping("/getTicketNumber")
	public ResponseEntity<Ticket> registerPassenger(@RequestBody Passenger passenger){
		
		Passenger pass = service.registerPassenger(passenger);
		Ticket ticket = new Ticket();
		ticket.setTicketNumber(pass.getPid());
		return new ResponseEntity<>(ticket, HttpStatus.OK);
	}
	
	@GetMapping("/getTicket/{ticketNumber}")
	public ResponseEntity<Ticket> getMyFullTicket(@PathVariable("ticketNumber") Integer id){
		
		Ticket ticket = new Ticket();
		
		Passenger passenger = service.fetchPassenger(id);
		
		ticket.setStatus("confirmed");
		ticket.setTicketCost(5000.0);
		ticket.setTicketNumber(passenger.getPid());
		ticket.setName(passenger.getName());
		ticket.setArrival(passenger.getArrival());
		ticket.setDeparture(passenger.getDeparture());		
		ticket.setDateOfJourney(passenger.getDateOfJourney());
		
		return new ResponseEntity<>(ticket, HttpStatus.OK);
	}
	
	
	
	
}
