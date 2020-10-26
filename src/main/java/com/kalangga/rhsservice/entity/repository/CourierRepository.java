package com.kalangga.rhsservice.entity.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.kalangga.rhsservice.entity.Couriers;

@Repository
public interface CourierRepository extends PagingAndSortingRepository<Couriers, Long> {
	
	Optional<Couriers> findByCode(String memberCode);
	
}
