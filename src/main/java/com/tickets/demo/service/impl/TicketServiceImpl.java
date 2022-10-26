package com.tickets.demo.service.impl;

import com.tickets.demo.service.TicketService;
import com.tickets.demo.entity.Ticket;
import com.tickets.demo.entity.TicketResume;
import com.tickets.demo.repository.TicketRepository;
import com.tickets.demo.repository.TicketResumeRepository;
import org.apache.commons.beanutils.ConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

@Service("TicketService")
public class TicketServiceImpl implements TicketService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    TicketRepository repository;



    @Override
    public ResponseEntity<?> getPaginated( Integer pageNo, Integer pageSize) {
        try {
            Pageable paging = PageRequest.of(pageNo, pageSize);
            Page<Ticket> pagedResult = repository.findAll( paging);
            return new ResponseEntity<>(pagedResult, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ERROR: " + e.getMessage());
            return new ResponseEntity<>("ERROR: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        try {
            Ticket response = repository.findById(id).orElse(null);
            if (response != null) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ERROR: " + e.getMessage());
            return new ResponseEntity<>("ERROR: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> post(Ticket entity) {
        try {
            Ticket response = repository.save(entity);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ERROR: " + e.getMessage());
            return new ResponseEntity<>("ERROR: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> put(Long id, Ticket entity) {
        try {
            Ticket entityDB = repository.findById(id).orElse(null);
            if (entityDB != null) {
                entity.setId(id);
                Ticket response = repository.save(entity);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ERROR: " + e.getMessage());
            return new ResponseEntity<>("ERROR: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> patch(Long id, Map<Object, Object> fields) {
        try {
            Ticket entityDB = repository.findById(id).orElse(null);
            if (entityDB != null) {
                fields.forEach((key, value) -> {
                    Field f = ReflectionUtils.findField(Ticket.class, (String) key);
                    if (f != null) {
                        f.setAccessible(true);
                        ReflectionUtils.setField(f, entityDB, ConvertUtils.convert(value, f.getType()));
                    }
                });
                entityDB.setId(id);
                Ticket response = repository.save(entityDB);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ERROR: " + e.getMessage());
            return new ResponseEntity<>("ERROR: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        try {
            Ticket entityDB = repository.findById(id).orElse(null);
            if (entityDB != null) {
                entityDB.setArchived("archived");
                repository.save(entityDB);
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ERROR: " + e.getMessage());
            return new ResponseEntity<>("ERROR: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}