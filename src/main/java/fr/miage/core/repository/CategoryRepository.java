package fr.miage.core.repository;

import fr.miage.core.entity.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // V0.6 #### Nouvelle requête findByName() ajoutée pour obtenir la catégorie
    // V0.6 #### dont le nom est donné. Remarque, il y 0 ou 1 grâce à la contrainte 
    // V0.6 #### d'unicité.
    Category findByName(String name);
    @Override
    List<Category> findAll();
    List<Category> findAllByOrderByName();
}
