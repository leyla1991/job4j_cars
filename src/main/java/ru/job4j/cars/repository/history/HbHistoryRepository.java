package ru.job4j.cars.repository.history;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.History;
import ru.job4j.cars.repository.CrudRepository;

import java.util.Collection;

@AllArgsConstructor
@Repository
public class HbHistoryRepository implements HistoryRepository {

    private CrudRepository crudRepository;

    @Override
    public Collection<History> findAll() {
        return crudRepository.query("FROM History", History.class);
    }
}
