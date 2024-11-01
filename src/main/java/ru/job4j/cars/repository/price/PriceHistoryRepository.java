package ru.job4j.cars.repository.price;

import ru.job4j.cars.model.PriceHistory;

import java.util.Collection;

public interface PriceHistoryRepository {

     PriceHistory save(PriceHistory priceHistory);

     Collection<PriceHistory> findAll();
}
