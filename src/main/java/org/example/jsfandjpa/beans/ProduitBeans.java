package org.example.jsfandjpa.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import lombok.Data;
import org.example.jsfandjpa.Entity.Produit;

import java.io.Serializable;
import java.util.List;
@Data
@Named
@ViewScoped
public class ProduitBeans  implements Serializable {
    private String nom;
    private Float prix;
    private String description;
    private Produit produitSelectionne;

    private EntityManager em;

    public ProduitBeans() {
        em = EntityMFsingluton.getEntityManagerFactory().createEntityManager();
    }

    public void ajouteProduit() {
        em.getTransaction().begin();
        Produit pr = new Produit();
        pr.setDescription(description);
        pr.setNom(nom);
        pr.setPrix(prix);
        em.persist(pr);
        em.getTransaction().commit();
    }

    public List<Produit> lister() {
        return em.createQuery("select pr from Produit as pr", Produit.class).getResultList();
    }

    public void chargerProduit(Produit produit) {
        this.produitSelectionne = produit;
    }

    public void modifierProduit() {
        em.getTransaction().begin();
        em.merge(produitSelectionne);
        em.getTransaction().commit();
        System.out.println("Produit yarbi ysde9 : " + produitSelectionne);
    }

    public void annuler() {
        produitSelectionne = null; // Réinitialise la sélection
    }
    public void deleteProduit(Produit produit) {
        try {
            em.getTransaction().begin();
            // Rendre le produit géré par le contexte de persistence
            Produit produitASupprimer = em.find(Produit.class, produit.getId());
            if (produitASupprimer != null) {
                em.remove(produitASupprimer); // Suppression du produit
            }
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erreur lors de la suppression du produit : " + e.getMessage());
        }
    }
}

