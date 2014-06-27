/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.furb.eventos.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Edson
 */
public class testEntity {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("eventosPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        try {
            tx.begin();
            User u = new User("Marcos", "marcos@gmal.com", "Souza", "mpsouza", "");
            em.persist(u);
            tx.commit();
            System.out.println("OK!");
        } catch ( Exception e) {
            if (tx != null && tx.isActive())
                tx.rollback();
        } finally {
            em.close();
        }
        
        System.out.println("Fim!");
        
        emf.close();
    }
}
