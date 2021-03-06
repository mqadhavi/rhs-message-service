package com.kalangga.rhsservice.entity.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.kalangga.rhsservice.entity.AgentXplayer;
import com.kalangga.rhsservice.entity.Agents;

@Repository
public interface AgentXplayerRepository extends PagingAndSortingRepository<AgentXplayer, Long> {

	Optional<AgentXplayer> findByAgent(Agents agent);

}
