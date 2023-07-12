package ru.koltunov.vitalii.moviet.controllers;

import ru.koltunov.vitalii.moviet.repositories.MovieRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.koltunov.vitalii.moviet.model.Movie;


@Controller
@RequestMapping("/movies")
public class MovieController {
    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping
    public String getAllMovies(Model model) {
        Iterable<Movie> movies = movieRepository.findAll();
        model.addAttribute("movies", movies);
        return "movies";
    }

    @GetMapping("/{id}/edit")
    public String editMovieForm(@PathVariable("id") Long id, Model model) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid movie ID: " + id));
        model.addAttribute("movie", movie);
        return "edit-movie";
    }

    @PostMapping("/{id}/edit")
    public String editMovie(@PathVariable("id") Long id, @ModelAttribute("movie") Movie editedMovie) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid movie ID: " + id));
        movie.setTitle(editedMovie.getTitle());
        movie.setDirector(editedMovie.getDirector());
        movieRepository.save(movie);
        return "redirect:/movies";
    }

    @GetMapping("/{id}/delete")
    public String deleteMovie(@PathVariable("id") Long id) {
        movieRepository.deleteById(id);
        return "redirect:/movies";
    }
}
