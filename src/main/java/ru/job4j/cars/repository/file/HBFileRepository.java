package ru.job4j.cars.repository.file;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.File;
import ru.job4j.cars.repository.CrudRepository;

import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HBFileRepository implements FileRepository {

    private final CrudRepository crudRepository;

    @Override
    public File save(File file) {
        crudRepository.run(session -> session.save(file));
        return file;
    }

    @Override
    public Optional<File> findById(int id) {
        return crudRepository.optional("FROM File WHERE id = :fId", File.class,
                Map.of("fId", id));
    }

    @Override
    public void deleteById(int id) {
        crudRepository.run("DELETE FROM File WHERE id = :fId",
                Map.of("fId", id));
    }
}
