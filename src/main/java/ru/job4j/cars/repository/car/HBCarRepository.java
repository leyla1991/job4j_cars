package ru.job4j.cars.repository.car;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.repository.CrudRepository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class HBCarRepository implements CarRepository {

    private CrudRepository crudRepository;

    @Override
    public Car save(Car car) {
        crudRepository.run(session -> session.save(car));
        return car;
    }

    @Override
    public Optional<Car> findById(int id) {
        return crudRepository.optional("FROM Car as i WHERE i.Id = :fId", Car.class,
                Map.of("fId", id));
    }

    @Override
    public Collection<Car> findAll() {
        return crudRepository.query("FROM Car", Car.class);
    }
}
