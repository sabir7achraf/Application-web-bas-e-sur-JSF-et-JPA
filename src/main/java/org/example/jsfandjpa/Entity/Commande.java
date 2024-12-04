package org.example.jsfandjpa.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Entity
@Data
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id ;
    Date date ;
    String Statue ;

    @ManyToOne
    Internaut internaut;
}
