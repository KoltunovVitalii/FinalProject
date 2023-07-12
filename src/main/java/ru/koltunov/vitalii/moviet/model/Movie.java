package ru.koltunov.vitalii.moviet.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("movies")
public class Movie {
    @Id
    private int year;
    private String title;
    private String director;

}

