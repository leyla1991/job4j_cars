package ru.job4j.cars.repository.engine;

import ru.job4j.cars.model.Engine;

import java.util.Collection;

public interface EngineRepository {

    Engine save(Engine engine);

    Collection<Engine> findAll();
}
