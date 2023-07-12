package ru.koltunov.vitalii.moviet.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.koltunov.vitalii.moviet.model.Ticket;

public interface TicketRepository extends CrudRepository<Ticket, Long> {

}
