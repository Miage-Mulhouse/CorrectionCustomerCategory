package fr.miage.core.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"name"})})
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 2, max = 30)
    private String name;
    
    // #### V0.4 Un client appartient à 0 ou 1 catégorie.
    // #### V0.4 Mais une catégorie peut-être partagée par 0, 1 ou plusieurs clients.
    // #### V0.4 Pour établir cette relation avec JPA, il faut utiliser 
    // #### V0.4 l'annotation ManyToOne
    // #### V0.4 Cette table sera la table maître
    @ManyToOne
    private Category category;
    

    public Customer() {
    }

    public Customer(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
            return name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    
}
