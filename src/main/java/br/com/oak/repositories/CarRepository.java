package br.com.oak.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import br.com.oak.models.Car;

public interface CarRepository extends ReactiveMongoRepository<Car, String> {

}
