package fr.miage.core.repository;

import fr.miage.core.entity.Category;
import fr.miage.core.entity.Customer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByName(String name);
    @Override
    List<Customer> findAll();
    List<Customer> findAllByOrderByName();
    // V0.6 #### Nouvelle requête findByCategory() ajoutée pour obtenir la liste
    // V0.6 #### de tous les clients d'une catégorie donnée.
    List<Customer> findByCategory(Category category);
}
