package com.example.voting_system.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.voting_system.Candidate;
import com.example.voting_system.CandidateRepository;
import com.example.voting_system.Vote;
import com.example.voting_system.VoteRepository;

@RestController
@RequestMapping("/votes")
@CrossOrigin(origins = "*")
public class VotingController {
	
	@Autowired
	private VoteRepository voteRepository;
	
	@Autowired
	private CandidateRepository candidateRepository;
	
	@PostMapping("/cast/{candidateId}")
	public ResponseEntity<String> castVote(@PathVariable Long candidateId,@RequestBody Long userId){
		if(voteRepository.existsByUserId(userId)) {
			return ResponseEntity.badRequest().body("You have already voted");
			
		}
		Candidate candidate = candidateRepository.findById(candidateId).orElseThrow(()->new RuntimeException("Candidate not found"));
		candidate.setVotes(candidate.getVotes()+1);
		candidateRepository.save(candidate);
		
		Vote vote = new Vote();
		vote.setUserId(userId);
		vote.setCandidateId(candidateId);
		vote.setTimestamp(LocalDateTime.now());
		voteRepository.save(vote);
		
		return ResponseEntity.ok("Your vote is casted successfully.");
		
	}
	
	@GetMapping("/results")
	public ResponseEntity<List<Candidate>> getResults(){
		return ResponseEntity.ok(candidateRepository.findAll());
	}
	
	
	
	

}
