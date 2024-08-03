package it.astrignano.pizzeria.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.astrignano.pizzeria.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByUsername(String username);

}
