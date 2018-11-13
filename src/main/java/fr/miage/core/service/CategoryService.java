package fr.miage.core.service;

import java.util.List;
import java.util.Optional;

import fr.miage.core.entity.Category;

public interface CategoryService {
    Category save(Category entity);
    void delete(Long id);
    List<Category> findAll();
    Optional<Category> findById(Long id);
    Category findByName(String name);
    Category getOne(Long id);
}
