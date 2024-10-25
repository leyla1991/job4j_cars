package ru.job4j.cars.repository.history;

import ru.job4j.cars.model.History;

import java.util.Collection;

public interface HistoryRepository {

    Collection<History> findAll();
}
