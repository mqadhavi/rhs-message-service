package com.kalangga.rhsservice.entity.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.kalangga.rhsservice.entity.Customers;
import com.kalangga.rhsservice.entity.CustomersXplayer;


@Repository
public interface CustomerXplayerRepository extends PagingAndSortingRepository<CustomersXplayer, Long> {
	
	Optional<CustomersXplayer> findByCustomer(Customers customers);
	
}
