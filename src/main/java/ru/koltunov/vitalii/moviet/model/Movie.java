package ru.koltunov.vitalii.moviet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@Table("movies")
public class Movie {
    @Id
    private long id;

    @Column("nm")
    private String title;

    @Column("director")
    private String director;

    @Column("prod_year")
    private int year;

}

