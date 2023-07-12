package ru.koltunov.vitalii.moviet.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("tickets")
public class Ticket {
    @Id
    private int namber;
    private int seat;

    public Ticket(int namber, int seat) {
        this.namber = namber;
        this.seat = seat;
    }
}
