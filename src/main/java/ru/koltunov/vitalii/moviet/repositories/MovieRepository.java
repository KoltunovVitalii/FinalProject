package ru.koltunov.vitalii.moviet.repositories;


import org.springframework.data.repository.CrudRepository;
import ru.koltunov.vitalii.moviet.model.Movie;

public interface MovieRepository extends CrudRepository<Movie, Long> {

}
