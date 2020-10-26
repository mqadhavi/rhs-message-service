package com.kalangga.rhsservice.entity.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.kalangga.rhsservice.entity.ShipmentNotifications;


@Repository
public interface ShipmentNotificationRepository extends PagingAndSortingRepository<ShipmentNotifications, Long> {
	
	
}
