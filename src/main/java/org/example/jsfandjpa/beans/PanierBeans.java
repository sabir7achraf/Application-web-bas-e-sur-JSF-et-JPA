package org.example.jsfandjpa.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import lombok.Data;
import org.example.jsfandjpa.Entity.Panier;
import org.example.jsfandjpa.Entity.Produit;

import java.util.List;

@Data
@Named
@RequestScoped
public class PanierBeans {
    long id ;
    float prixTotale;
    List<Produit> produitList;


    private EntityManager em ;

    public PanierBeans(){
        em = EntityMFsingluton.getEntityManagerFactory().createEntityManager();
    }

    public List<Panier> listerPanier(){
        List<Panier> paniers = em.createQuery("SELECT p FROM Panier p LEFT JOIN FETCH p.produitList", Panier.class).getResultList();
        for (Panier panier : paniers) {
            System.out.println("Panier ID: " + panier.getId() + ", Produits: " + panier.getProduitList());
        }
        return paniers;

    }

    public void addPanier(){
        em.getTransaction().begin();
        Panier p = new Panier();
        p.setPrixTotale(prixTotale);
        em.persist(p);
        em.getTransaction().commit();

    }
}
