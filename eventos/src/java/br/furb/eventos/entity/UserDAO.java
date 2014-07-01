
package br.furb.eventos.entity;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;

public class UserDAO {
    
    private static UserDAO instance = new UserDAO();
    
    public static UserDAO getInstance() {
        return instance;
    } 
    
    private UserDAO(){
    }
    
    public void save (User user) {
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

    public User getById(long id) {
        User u = null;
        
        EntityManager em = PersistenseUtil.getEntityManager();
        u = em.find(User.class, id);
        
        return u;
    }
    
    public void remove(User u) {
        EntityManager em = PersistenseUtil.getEntityManager();
        
        EntityTransaction et = em.getTransaction();
        
        et.begin();
        em.remove(em.find(User.class, u.getId()));
        et.commit();
        
        PersistenseUtil.close(em);
    }
    
    public boolean verify (User u) {
        
        if (u == null)
            return false;
        
        EntityManager em = PersistenseUtil.getEntityManager();
        
        //Query q = em.createQuery("select u from User u where u.name = :name AND u.email = :email AND u.lastname = :lastname");
        Query q = em.createQuery("select u from User u where u.name = :name AND u.email = :email");
        q.setParameter("name", u.getName());
        q.setParameter("email", u.getEmail());
        //q.setParameter("lastname", u.getLastname());
        
        try {
            User user = (User) q.getSingleResult();
        } catch (NoResultException e) {
            return false;
        }
        PersistenseUtil.close(em);
        return true;
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
