package br.com.oak.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.oak.events.CarEvents;
import br.com.oak.models.Car;
import br.com.oak.services.FluxCarService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@RestController
//@RequestMapping("/car")
public class CarResource {

	@Autowired
	private FluxCarService fluxCarService;

	@GetMapping("/cars")
	public Flux<Car> all() {
		return fluxCarService.all();
	}

	@GetMapping("/cars/{carId}")
	public Mono<Car> byId(@PathVariable String carId) {
		return fluxCarService.byId(carId);
	}

	@GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE, value = "/cars/{carId}/events")
	public Flux<CarEvents> eventsOfStreams(@PathVariable String carId) {
		return fluxCarService.streams(carId);
	}

}
