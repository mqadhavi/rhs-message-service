package com.kalangga.rhsservice.entity.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.kalangga.rhsservice.entity.Customers;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customers, Long> {
	Optional<Customers> findByMemberCode(String memberCode);

}
