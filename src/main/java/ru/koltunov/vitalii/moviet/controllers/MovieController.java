package ru.koltunov.vitalii.moviet.controllers;

import ru.koltunov.vitalii.moviet.model.Ticket;
import ru.koltunov.vitalii.moviet.repositories.MovieRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.koltunov.vitalii.moviet.model.Movie;
import ru.koltunov.vitalii.moviet.repositories.TicketRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/movies")
public class MovieController {
    private final MovieRepository movieRepository;
    private final TicketRepository ticketRepository;

    public MovieController(MovieRepository movieRepository, TicketRepository ticketRepository) {
        this.movieRepository = movieRepository;
        this.ticketRepository = ticketRepository;
    }

    @GetMapping
    public String getAllMovies(Model model) {
        Iterable<Movie> movies = movieRepository.findAll();
        model.addAttribute("movies", movies);
        return "movies";
    }

    @GetMapping("/movieDetails/{id}")
    public String getMovie(@PathVariable("id") Long id, Model model) {
        Optional<Movie> optMovie = movieRepository.findById(id);
        model.addAttribute("movie", optMovie.get());
        return "movieDetails";
    }

    @GetMapping("/{id}/edit")
    public String editMovieForm(@PathVariable("id") Long id, Model model) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid movie ID: " + id));
        model.addAttribute("movie", movie);
        return "addMovie";
    }

    @PostMapping("/{id}/edit")
    public String editMovie(
            @ModelAttribute("movie") Movie editedMovie,
            @PathVariable("id") Long id,
            @RequestParam("title") String title,
            @RequestParam("director") String director,
            @RequestParam("year") int year) {
        movieRepository.update(id, title, director, year);
        return "redirect:/movies";
    }

    @GetMapping("/add")
    public String addNew(Model model) {
        Movie movie = new Movie(0, "", "", 0);
        model.addAttribute("movie", movie);
        return "addMovie";
    }

    @PostMapping("/add")
    public String addNew(@RequestParam("title") String title, @RequestParam("director") String director, @RequestParam("year") int year) {
        Movie movie = movieRepository.save(new Movie(0, title, director, year));
        return "redirect:/movies";
    }

    @PostMapping("/{id}/delete")
    public String deleteMovie(@PathVariable("id") Long id) {
        Iterable<Ticket> tickets = ticketRepository.findAll();
        for (Ticket ticket : tickets) {
            if (ticket.getMovie() == id) {
                ticketRepository.delete(ticket);
            }
        }
        movieRepository.deleteById(id);
        return "redirect:/movies";
    }
}
