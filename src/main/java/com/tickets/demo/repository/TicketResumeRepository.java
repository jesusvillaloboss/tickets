package com.tickets.demo.repository;


import com.tickets.demo.entity.Ticket;
import com.tickets.demo.entity.TicketResume;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

public interface TicketResumeRepository extends PagingAndSortingRepository<TicketResume, Serializable> {



}