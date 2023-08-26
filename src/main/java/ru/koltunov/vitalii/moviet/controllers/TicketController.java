package ru.koltunov.vitalii.moviet.controllers;
import lombok.AllArgsConstructor;
import ru.koltunov.vitalii.moviet.model.Movie;
import ru.koltunov.vitalii.moviet.repositories.MovieRepository;
import ru.koltunov.vitalii.moviet.repositories.TicketRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.koltunov.vitalii.moviet.model.Ticket;

@Controller
@RequestMapping("/tickets")
public class TicketController {
    private final TicketRepository ticketRepository;
    private final MovieRepository movieRepository;


    public TicketController(TicketRepository ticketRepository, MovieRepository movieRepository) {
        this.ticketRepository = ticketRepository;
        this.movieRepository = movieRepository;
    }

    @GetMapping
    public String getAllTickets(Model model) {
        Iterable<Ticket> tickets = ticketRepository.findAll();
        model.addAttribute("tickets", tickets);
        return "tickets";
    }

    @GetMapping("/add")
    public String addNew(Model model) {
        Iterable<Movie> movies = movieRepository.findAll();
        model.addAttribute("movies", movies);
        return "addTickets";
    }

    @PostMapping("/add")
    public String addNew(
            @RequestParam("number") int number,
            @RequestParam("seat") int seat,
            @RequestParam("movie") long movie) {
        Ticket ticket = ticketRepository.save(new Ticket(0, number, seat, movie));
        return "redirect:/tickets";
    }

    @PostMapping ("/{id}/delete")
    public String deleteTicket(@PathVariable("id") Long id) {
        ticketRepository.deleteById(id);
        return "redirect:/tickets";
    }

    @GetMapping ("ticketDetails/{id}")
    public String ticketDetails(@PathVariable("id") Long id, Model model) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ticket ID: " + id));
        Movie movie = movieRepository.findById(ticket.getMovie())
                .orElseThrow(() -> new IllegalArgumentException("Invalid movie ID: " + ticket.getMovie()));
        model.addAttribute("ticket", ticket);
        model.addAttribute("movie", movie);
        return "ticketDetails";
    }

}
