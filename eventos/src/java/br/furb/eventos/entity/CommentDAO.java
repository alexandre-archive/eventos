/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.furb.eventos.entity;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

/**
 *
 * @author Edson
 */
public class CommentDAO {
   private static CommentDAO instance = new CommentDAO();
   
   private CommentDAO() {
       
   }
   
   public static CommentDAO getInstance() {
       return instance;
   }
   
   public void salvar (Comment comm) {
        EntityManager em = PersistenseUtil.getEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            if (comm.getId() == 0)
                em.persist(comm);
            else
                em.merge(comm);    
            et.commit();
        } catch (Exception e) {
            if (et != null && et.isActive())
                et.rollback();
            throw new RuntimeException(e);
        } finally {
            PersistenseUtil.close(em);
        }
    }
    
    public List<Comment> getAllProfiles() {
        EntityManager em = PersistenseUtil.getEntityManager();
        EntityTransaction et = em.getTransaction();
        List<Comment> comments = null;
        try {
            et.begin();
            TypedQuery<Comment> query = (TypedQuery<Comment>) em.createQuery("select p from Profile p");

            comments = query.getResultList();
            
            for (Comment c : comments) {
                System.out.println("Nome: " + c.getComment());
            }
            
            et.commit();
        } catch (Exception e) {
            if (et != null && et.isActive())
                et.rollback();
            throw new RuntimeException(e);
        } finally {
            PersistenseUtil.close(em);
        }
        return comments;
    }
}
