package org.example.jsfandjpa.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.Data;
import org.example.jsfandjpa.Entity.Internaut;
import org.example.jsfandjpa.Entity.Produit;

import java.io.Serializable;
import java.util.List;

@Data
@Named @ViewScoped
public class InternautBeans implements Serializable { //"passivation capable

    String name ;
    String numTele ;

    Internaut internautSelectionne ;

    private EntityManagerFactory emf ;
    private EntityManager em;

    public InternautBeans(){
            emf = Persistence.createEntityManagerFactory("default");
            em = emf.createEntityManager();
        }

        public List<Internaut> lister(){
        return em.createQuery("select  it from Internaut as it").getResultList();
        }

        public String ajouteInternaut(){

        em.getTransaction().begin();
        Internaut it =new Internaut();
        it.setNumTele(numTele);
        it.setName(name);
        em.persist(it);
        em.getTransaction().commit();
            return "ListClient.xhtml?faces-redirect=true";
        }

    public void chargerInternaut(Internaut internaut) {
        this.internautSelectionne = internaut;
    }

    public void modifierInternaut() {
        em.getTransaction().begin();
        em.merge(internautSelectionne);
        em.getTransaction().commit();
        System.out.println("Produit yarbi ysde9 : " + internautSelectionne);
    }

    public void deleteInternaut(Internaut internaut){
        try {
            em.getTransaction().begin();
            // Rendre le produit géré par le contexte de persistence
            Internaut internaut1Supprimer = em.find(Internaut.class, internaut.getId());
            if (internaut1Supprimer != null) {
                em.remove(internaut1Supprimer); // Suppression du produit
            }
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erreur lors de la suppression du produit : " + e.getMessage());
        }
    }

    public void annuler() {
        internautSelectionne = null; // Réinitialise la sélection
    }


    }


