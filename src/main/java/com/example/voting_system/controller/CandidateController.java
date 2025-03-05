package com.example.voting_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.voting_system.Candidate;
import com.example.voting_system.CandidateRepository;

@RestController
@RequestMapping("/candidates")
@CrossOrigin(origins = "*") 
public class CandidateController {
	
	@Autowired
	private CandidateRepository candidateRepository;
	
	@PostMapping("/add")
	public ResponseEntity<String> addCandidate(@RequestBody Candidate candidate){
		candidateRepository.save(candidate);
		return ResponseEntity.ok("Candidate added successfully");
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Candidate>> getAllCandidates(){
		return ResponseEntity.ok(candidateRepository.findAll());
				
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateCandidate(@PathVariable Long id,@RequestBody Candidate updatedCandidate){
		Candidate candidate = candidateRepository.findById(id).orElseThrow(()->new RuntimeException("Candidate  not found"));
		candidate.setName(updatedCandidate.getName());
		candidate.setParty(updatedCandidate.getParty());
		candidate.setDescription(updatedCandidate.getDescription());
		candidateRepository.save(candidate);
		
		return ResponseEntity.ok("Candidate updated successfully");
	
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteCandidate(@PathVariable Long id){
		candidateRepository.deleteById(id);
		return ResponseEntity.ok("Candidate deleted successfully");
		
	}
	
	
	
	
	

}
