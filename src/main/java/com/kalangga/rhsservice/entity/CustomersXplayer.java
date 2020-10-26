package com.kalangga.rhsservice.entity;

import javax.persistence.*;

import com.kalangga.rhsservice.base.DateAudit;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customer_xplayers")
public class CustomersXplayer extends DateAudit  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "customer_id")
	private Customers customer;

	@Column(name = "token")
	private String token;
}
