package com.kalangga.rhsservice.entity;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "shipment_notifications")
public class ShipmentNotifications {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "member_code")
	private String memberCode;

	@Column(name = "shipment_code")
	private String shipmentCode;

	private String status;
	
	private String message;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	@JsonProperty("created_at")
	private Instant createdAt;
}
