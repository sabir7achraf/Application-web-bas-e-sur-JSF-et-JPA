package org.example.jsfandjpa.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Internaut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id ;
    String name ;
    String numTele;

    @OneToMany(mappedBy = "internaut",cascade = CascadeType.ALL,orphanRemoval = true)
    List<Panier> panier;

    @OneToMany(cascade = CascadeType.ALL)
    List<Commande> commandeList ;


}
