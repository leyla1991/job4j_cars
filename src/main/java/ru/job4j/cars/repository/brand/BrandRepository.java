package ru.job4j.cars.repository.brand;

import ru.job4j.cars.model.Brand;

import java.util.Collection;

public interface BrandRepository {

    Collection<Brand> findAll();
}
