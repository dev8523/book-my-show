package com.debasish.bookmyshow.controller;

import com.debasish.bookmyshow.exception.ShowSeatNotAvailableException;
import com.debasish.bookmyshow.model.Ticket;
import com.debasish.bookmyshow.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public Ticket bookTicket(Long showId, List<Long> showSeatIds, Long userId) throws ShowSeatNotAvailableException {
        return ticketService.bookTicket(showId, showSeatIds, userId);
    }
}
