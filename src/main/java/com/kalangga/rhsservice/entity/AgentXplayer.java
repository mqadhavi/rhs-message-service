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
@Table(name = "agent_xplayers")
public class AgentXplayer extends DateAudit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "agent_id")
	private Agents agent;

	@Column(name = "token")
	private String token;
}
