package com.tickets.demo.controller;

import com.tickets.demo.entity.Ticket;
import com.tickets.demo.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {
    @Autowired
    TicketService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getById(@PathVariable(name = "id") final Long id) {
        return service.getById(id);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPaginated( @RequestParam(value = "pageNo") final Integer pageNo, @RequestParam(value = "pageSize") final Integer pageSize) {
        return service.getPaginated( pageNo, pageSize);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> post(@RequestBody final Ticket entity) {
        return service.post(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> put(@PathVariable(name = "id") final Long id, @RequestBody final Ticket entity) {
        return service.put(id, entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> patch(@PathVariable(name = "id") final Long id, @RequestBody final Map<Object, Object> fields) {
        return service.patch(id, fields);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable(name = "id") final Long id) {
        return service.delete(id);
    }
}