package ru.job4j.cars.repository.engine;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.repository.CrudRepository;
import java.util.Collection;

@Repository
@AllArgsConstructor
public class HBEngineRepository implements EngineRepository {

    private CrudRepository crudRepository;

    @Override
    public Engine save(Engine engine) {
       crudRepository.run(session -> session.save(engine));
       return engine;
    }

    @Override
    public Collection<Engine> findAll() {
        return crudRepository.query("FROM Engine", Engine.class);
    }
}
