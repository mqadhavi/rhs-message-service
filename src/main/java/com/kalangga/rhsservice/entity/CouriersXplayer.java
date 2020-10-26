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
@Table(name = "courier_xplayers")
public class CouriersXplayer extends DateAudit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "courier_id")
	private Couriers courier;

	@Column(name = "token")
	private String token;
}
