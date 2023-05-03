package com.debasish.bookmyshow.service;

import com.debasish.bookmyshow.exception.ShowSeatNotAvailableException;
import com.debasish.bookmyshow.model.ShowSeat;
import com.debasish.bookmyshow.model.ShowSeatStatus;
import com.debasish.bookmyshow.model.Ticket;
import com.debasish.bookmyshow.repository.ShowRepository;
import com.debasish.bookmyshow.repository.ShowSeatRepository;
import com.debasish.bookmyshow.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final ShowSeatRepository showSeatRepository;
    private final ShowRepository showRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository, ShowSeatRepository showSeatRepository, ShowRepository showRepository) {
        this.ticketRepository = ticketRepository;
        this.showSeatRepository = showSeatRepository;
        this.showRepository = showRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE) //sets the Transaction isolation level as SERIALIZABLE
    public Ticket bookTicket(Long showId, List<Long> showSeatIds, Long userId) throws ShowSeatNotAvailableException {
        //Fetch all the given showSeats from the table
        List<ShowSeat> showSeats = showSeatRepository.findByIdIn(showSeatIds);

        //Check if all of them are available
        for (ShowSeat showSeat : showSeats) {
            if (!showSeat.getSeatStatus().equals(ShowSeatStatus.AVAILABLE)) {
                throw new ShowSeatNotAvailableException("Show seat is not available");
            }
        }

        //If all are available then make them locked
        for (ShowSeat showSeat : showSeats) {
            showSeat.setSeatStatus(ShowSeatStatus.LOCKED);
            showSeatRepository.save(showSeat);
        }

        //wait for payment confirmation
        //assuming payment is done
        //create and return the ticket object
        Ticket ticket = new Ticket();
        ticket.setShowSeats(showSeats);
        ticket.setShow(showRepository.findById(showId).get());
        //set other details for ticket
        ticket = ticketRepository.save(ticket);

        //mark them booked after ticket is done
        for (ShowSeat showSeat : showSeats) {
            showSeat.setSeatStatus(ShowSeatStatus.BOOKED);
            showSeatRepository.save(showSeat);
        }
        return ticket;
    }
}
