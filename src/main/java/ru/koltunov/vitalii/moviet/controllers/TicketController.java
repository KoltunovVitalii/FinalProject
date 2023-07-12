package ru.koltunov.vitalii.moviet.controllers;
import ru.koltunov.vitalii.moviet.repositories.TicketRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.koltunov.vitalii.moviet.model.Ticket;

@Controller
@RequestMapping("/tickets")
public class TicketController {
    private final TicketRepository ticketRepository;

    public TicketController(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @GetMapping
    public String getAllTickets(Model model) {
        Iterable<Ticket> tickets = ticketRepository.findAll();
        model.addAttribute("tickets", tickets);
        return "tickets";
    }

    @GetMapping("/{id}/edit")
    public String editTicketForm(@PathVariable("id") Long id, Model model) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ticket ID: " + id));
        model.addAttribute("ticket", ticket);
        return "edit-ticket";
    }

    @PostMapping("/{id}/edit")
    public String editTicket(@PathVariable("id") Long id, @ModelAttribute("ticket") Ticket editedTicket) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ticket ID: " + id));
        ticket.setMovieTitle(editedTicket.getMovieTitle());
        ticket.setCustomerName(editedTicket.getCustomerName());
        ticketRepository.save(ticket);
        return "redirect:/tickets";
    }

    @GetMapping("/{id}/delete")
    public String deleteTicket(@PathVariable("id") Long id) {
        ticketRepository.deleteById(id);
        return "redirect:/tickets";
    }
}
