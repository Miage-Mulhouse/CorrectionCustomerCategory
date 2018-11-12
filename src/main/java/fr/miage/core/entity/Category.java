package fr.miage.core.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"name"})})
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 1, max = 1)
    private String name;
    // #### V0.4 Un client appartient à 0 ou 1 catégorie.
    // #### V0.4 Mais une catégorie peut-être partagée par 0, 1 ou plusieurs clients.
    // #### V0.4 Pour établir cette relation avec JPA, il faut utiliser 
    // #### V0.4 l'annotation OneToMany
    // #### V0.4 l'attribut mappedBy = "category" indique que cette table sera
    // #### V0.4 l'esclave, c'est-à-dire c'est l'identifiant de category qui
    // #### V0.4 sera la clé étrangère dans la table customer.
    @OneToMany(mappedBy = "category")
    private List<Customer> customers;

    public Category() {
    }

    public Category(String name) {
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
}
