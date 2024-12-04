package org.example.jsfandjpa.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.Data;
import org.example.jsfandjpa.Entity.Commande;

import java.util.Date;
import java.util.List;

@Data
@Named
@RequestScoped
public class CommandeBeans {

    Date date ;
    String statue ;


    private EntityManager em ;

    public CommandeBeans(){
        em = EntityMFsingluton.getEntityManagerFactory().createEntityManager();
    }

    public List<Commande> lister(){

        return   em.createQuery("select cmd from Commande as cmd").getResultList();
    }

    public void addCommande(){
        em.getTransaction().begin();
        Commande cmd = new Commande();
        cmd.setDate(date);
        cmd.setStatue(statue);
    }


}

