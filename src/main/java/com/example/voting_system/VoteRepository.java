package com.example.voting_system;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote,Long>{
	boolean existsByUserId(Long userId);

}
