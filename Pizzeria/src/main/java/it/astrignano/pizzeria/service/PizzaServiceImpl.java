package it.astrignano.pizzeria.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.astrignano.pizzeria.model.PizzaModel;
import it.astrignano.pizzeria.repositoiry.PizzaRepository;

@Service
public class PizzaServiceImpl implements PizzaService {

	@Autowired
	private PizzaRepository pizzaRepo;

	@Override
	public Optional<PizzaModel> findById(Integer id) {
		return pizzaRepo.findById(id);
	}

	@Override
	public PizzaModel save(PizzaModel pizza) {

		return pizzaRepo.save(pizza);
	}

	@Override
	public PizzaModel update(Integer id, PizzaModel inputPizza) {

		Optional<PizzaModel> pizzaCercata = pizzaRepo.findById(id);

		if (pizzaCercata.isEmpty()) {
			throw new IllegalArgumentException("La pizza cercata con id" + id + " non esiste.");
		}

		PizzaModel pizza = pizzaCercata.get();

		pizza.setDescrizione(inputPizza.getDescrizione());
		pizza.setFotoUrl(inputPizza.getFotoUrl());
		pizza.setIngredienti(inputPizza.getIngredienti());
		pizza.setNome(inputPizza.getNome());
		pizza.setOfferte(inputPizza.getOfferte());
		pizza.setPrice(inputPizza.getPrice());

		return pizzaRepo.save(pizza);
	}

	@Override
	public void delete(Integer id) {
		pizzaRepo.deleteById(id);
	}
 
}
