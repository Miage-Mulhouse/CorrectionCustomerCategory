package fr.miage.core.repository;

import fr.miage.core.entity.Customer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByName(String name);
    @Override
    List<Customer> findAll();
    // #### V0.3 Pour avoir la liste ordonnée, il suffit de déclarer cette
    // #### V0.3 méthode ainsi. Spring se charge de faire l'implémentation qui
    // #### V0.3 va bien.
    
    // #### V0.3 https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
    List<Customer> findAllByOrderByName();
}
