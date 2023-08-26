package ru.koltunov.vitalii.moviet.repositories;


import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.koltunov.vitalii.moviet.model.Movie;

public interface MovieRepository extends CrudRepository<Movie, Long> {
    @Modifying
    @Query("""
        UPDATE movies
        SET
          nm = :title,
          director = :director,
          prod_year = :year
        WHERE id = :id  
        """)
    void update(
            @Param("id") long id,
            @Param ("title") String title,
            @Param ("director") String director,
            @Param("year") int year);
}
