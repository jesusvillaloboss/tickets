package com.tickets.demo.service;

import com.tickets.demo.entity.Ticket;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface TicketService {

    ResponseEntity<?> getById(Long id);

    ResponseEntity<?> getPaginated( Integer pageNo, Integer pageSize);

    ResponseEntity<?> post(Ticket entity);

    ResponseEntity<?> put(Long id, Ticket entity);

    ResponseEntity<?> patch(Long id, Map<Object, Object> fields);

    ResponseEntity<?> delete(Long id);
}