package fr.miage.core.repository;

import fr.miage.core.entity.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
    @Override
    List<Category> findAll();
    List<Category> findAllByOrderByName();
}
