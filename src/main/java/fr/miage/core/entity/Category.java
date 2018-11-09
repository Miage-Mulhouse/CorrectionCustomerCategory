package fr.miage.core.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// #### V0.1 @Entity pour indiquer que c'est une entité JPA qui sera traduit
// #### V0.1 en table.
@Entity
// #### V0.1 En l'absence de nom de table, celle-ci s'appelle Category.
// #### V0.1 Par ailleurs, il est précisé que le nom doit être unique.
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"name"})})
public class Category implements Serializable {

  
// #### V0.1 Pour définir l'identifiant de la table.
    @Id
// #### V0.1 Pour laisser à Spring le soin de générer un identifiant unique.
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

// #### V0.1 Pour préciser que ce champ ne doit pas rester nul    
    @NotNull
// #### V0.1 Pour préciser que sa taille est de un et un seul caractère.   
    @Size(min = 1, max = 1)
    private String name;


// #### V0.1 Pour pouvoir être instanciée et manipulée par Spring, une entité
// #### V0.1 (comme tout composant en général) doit posséder un constructeur
// #### V0.1 par défaut et des getters et setters pour tous les attributs.    
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
