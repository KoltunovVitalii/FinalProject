package ru.koltunov.vitalii.moviet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@Table("tickets")
public class Ticket {
    @Id
    private long id;

    @Column("number")
    private int number;

    @Column("seat")
    private int seat;

    @Column("movie")
    private long movie;

}
