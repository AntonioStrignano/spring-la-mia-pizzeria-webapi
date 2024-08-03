package it.astrignano.pizzeria.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
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

import it.astrignano.pizzeria.model.PizzaModel;
import it.astrignano.pizzeria.repositoiry.PizzaRepository;
import it.astrignano.pizzeria.response.Payload;
import it.astrignano.pizzeria.service.PizzaService;
import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/pizzeria/")
public class PizzaRestController {

	@Autowired

	private PizzaService pizzaService;

	@GetMapping("{id}")
	public ResponseEntity<Payload<PizzaModel>> get(@PathVariable("id") Integer idPizza) {

		Optional<PizzaModel> pizza = pizzaService.findById(idPizza);

		if (pizza.isPresent()) {
			return ResponseEntity.ok(new Payload<PizzaModel>(pizza.get(), null, HttpStatus.OK));
		} else {
			return new ResponseEntity<Payload<PizzaModel>>(
					new Payload<PizzaModel>(null, "Pizza con id " + idPizza + " non trovato.", HttpStatus.NOT_FOUND),
					HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<Payload<PizzaModel>> store(@Valid @RequestBody PizzaModel pizza) {
		PizzaModel pizzaSalvata = pizzaService.save(pizza);
		Optional<PizzaModel> pizzaSalvataOpt = pizzaService.;
		if (pizzaSalvata.isPre){
			return ResponseEntity.ok(new Payload<PizzaModel>(pizzaSalvata, null, HttpStatus.OK));
		} else {
			return new ResponseEntity<Payload<PizzaModel>>( new Payload<PizzaModel>(null, "Errore nel salvataggio della pizza.", HttpStatus.INTERNAL_SERVER_ERROR),HttpStatus.INTERNAL_SERVER_ERROR); {
//			return new ResponseEntity<>("Errore nel salvataggio della pizza.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("{id}")
	public ResponseEntity<Payload<PizzaModel>> update(@PathVariable("id") Integer idPizza,
			@RequestBody PizzaModel pizza) {

		try {
			PizzaModel pizzaUpdate = pizzaService.update(idPizza, pizza);
			return ResponseEntity.ok(pizzaUpdate);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>("Pizza non trovata.", HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>("Errore nel salvataggio della pizza.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Payload<PizzaModel>> delete(@PathVariable("id") Integer idPizza) {
		try {
			pizzaService.delete(idPizza);
			return ResponseEntity.ok("Cancellazione effettuata.");
		} catch (Exception e) {
			return new ResponseEntity<>("Errore nel salvataggio della pizza.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
