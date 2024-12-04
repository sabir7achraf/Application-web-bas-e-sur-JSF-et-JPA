package org.example.jsfandjpa.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="panier")
@Data
public class Panier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id ;
     private float prixTotale;

     @ManyToMany(cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
     List<Produit> produitList;

     @ManyToOne(cascade = CascadeType.ALL)
    Internaut internaut;


}
