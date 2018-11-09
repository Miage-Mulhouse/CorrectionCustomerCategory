package fr.miage.core.repository;

import fr.miage.core.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
// #### V0.1 Couche repository
        
// #### V0.1 (HTTP/POST) Pour rendre persistant l'entité Category en utilisant
// #### V0.1 Hibernate à travers JPA, il suffit, pour l'essentiel, de déclarer    
// #### V0.1 une interface qui hérite de JpaRepository<Category, Long>   
// #### V0.1 dont le 1er paramètre générique est le type à "persister" (Category)  
// #### V0.1 et le 2eme est le type de son identifiant.

// #### V0.1 L'interface JpaRepository possède déjà un certain nombres de 
// #### V0.1 méthodes pour faire des requêtes dans la table, comme par exemple
// #### V0.1   - findAll() 
// #### V0.1   - deleteById(ID id)
// #### V0.1   - save(S entity)
// #### V0.1   - ...
// #### V0.1 https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html

public interface CategoryRepository extends JpaRepository<Category, Long> {
// #### V0.1 Mais en plus il est possible de composer sa propre méthode
// #### V0.1 Par exemple, findByName qui est possible puisqu'il y a un champ name.
    Category findByName(String name);
// #### V0.1 Il est possible d'imaginer des méthodes plus compliquées comme
// #### V0.1 par exemple findByNameAndSurname(String name, String surname);
// #### V0.1 pour peu que surname soit un champ.    
// #### V0.1 https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
// #### V0.1 Et le plus important et qu'il n'est même pas nécessaire d'écrire 
// #### V0.1 l'implémentation de cette interface. Spring s'en charge !    
}
