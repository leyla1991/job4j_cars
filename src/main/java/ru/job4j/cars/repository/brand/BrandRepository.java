package ru.job4j.cars.repository.brand;

import ru.job4j.cars.model.Brand;

import java.util.Collection;

public interface BrandRepository {

    Brand save(Brand brand);

    Collection<Brand> findAll();
}
