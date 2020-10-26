package com.kalangga.rhsservice.entity.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.kalangga.rhsservice.entity.Couriers;
import com.kalangga.rhsservice.entity.CouriersXplayer;


@Repository
public interface CourierXplayerRepository extends PagingAndSortingRepository<CouriersXplayer, Long> {
	    
	Optional<CouriersXplayer> findByCourier(Couriers couriers);
}
