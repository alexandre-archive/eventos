
package br.furb.eventos.entity;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class UserDAO {
    
    private static UserDAO instance = new UserDAO();
    
    public static UserDAO getInstance() {
        return instance;
    } 
    
    private UserDAO(){
    }
    
    @SuppressWarnings("null")
    public void salvar (User user) {
        EntityManager em;
        em = PersistenseUtil.getEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            if (user.getId() != 0)
                em.persist(user);
            else
                em.merge(user);
            
            et.commit();
        } catch (Exception e) {
            if (et != null && et.isActive())
                et.rollback();
            throw new RuntimeException(e);
        } finally {
            PersistenseUtil.close(em);
        }
    }
    
    public List<User> getAllUsers() {
        EntityManager em = PersistenseUtil.getEntityManager();
        EntityTransaction et = em.getTransaction();
        List<User> users = null;
        try {
            et.begin();
            TypedQuery<User> query = (TypedQuery<User>) em.createQuery("select u from User u");

            users = query.getResultList();
            
            for (User u : users) {
                System.out.println("Nome: " + u.getName());
            }
            
            et.commit();
        } catch (Exception e) {
            if (et != null && et.isActive())
                et.rollback();
            throw new RuntimeException(e);
        } finally {
            PersistenseUtil.close(em);
        }
        return users;
    }
}
