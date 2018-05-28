package br.com.oak.services;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.oak.events.CarEvents;
import br.com.oak.models.Car;
import br.com.oak.repositories.CarRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Service
public class FluxCarService {

	@Autowired
	private CarRepository carRepository;
	
	FluxCarService(CarRepository carRepository) {
		this.carRepository = carRepository;
	}

	public Flux<Car> all () {
		return carRepository.findAll();
	}

	public Mono<Car> byId(String carId) {
		return carRepository.findById(carId);
	}

	public Flux<CarEvents> streams(String carId) {
		return byId(carId).flatMapMany(car -> {
			Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));
				Flux<CarEvents> events = Flux.fromStream(Stream.generate(() -> new CarEvents(car, new Date())));
				return Flux.zip(interval, events).map(Tuple2::getT2);
			});
   }
	
}
