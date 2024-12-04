package org.example.jsfandjpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.jsfandjpa.Entity.Internaut;
import org.example.jsfandjpa.Entity.Panier;

public class test {

    public static void main(String[] args) {

        Internaut et = new Internaut();
        et.setName("client1");
        et.setNumTele("062144785");


        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Panier pn= new Panier();

        pn.setPrixTotale(900);
        pn.setInternaut(et);

        System.out.println("COMIITING");
        em.persist(et);
        em.persist(pn);
        em.getTransaction().commit();

    }
}
