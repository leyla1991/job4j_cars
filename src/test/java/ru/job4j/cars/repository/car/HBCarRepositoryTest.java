package ru.job4j.cars.repository.car;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.repository.CrudRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class HBCarRepositoryTest {

    private static HBCarRepository carRepository;
    private static SessionFactory sf;

    @BeforeAll
    public static void initRepository() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();
        carRepository = new HBCarRepository(new CrudRepository(sf));
    }

    @Test
    public void whenSaveCar() {
        Car car = new Car();
        car.setNameCar("test");

        carRepository.save(car);
        var result = carRepository.findById(car.getId());

        assertThat(result.get()).isEqualTo(car);
    }

    @Test
    public void whenFindAll() {
        Car car = new Car();
        Car car1 = new Car();

        car.setNameCar("test");
        car1.setNameCar("test1");

        carRepository.save(car);
        carRepository.save(car1);

        var result = carRepository.findAll();

        assertThat(result).isEqualTo(List.of(car, car1));
    }
}