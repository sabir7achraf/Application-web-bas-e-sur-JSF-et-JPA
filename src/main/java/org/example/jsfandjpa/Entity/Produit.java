package org.example.jsfandjpa.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id ;
    String nom;
    Float prix;
    String description;

    @ManyToMany
    List<Panier> panier;
}
