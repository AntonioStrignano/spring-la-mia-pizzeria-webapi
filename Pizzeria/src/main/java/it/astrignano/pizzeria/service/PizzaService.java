package it.astrignano.pizzeria.service;

import java.util.Optional;

import it.astrignano.pizzeria.model.PizzaModel;

public interface PizzaService {

	public Optional<PizzaModel> findById(Integer id);
	
	
	public PizzaModel save(PizzaModel pizza);
	
	public PizzaModel update(Integer id, PizzaModel pizza);
	
	public void delete(Integer id);

}


