
package br.furb.eventos.entity;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class ProfileDAO {

    private static ProfileDAO instance = new ProfileDAO();
    
    private ProfileDAO (){
    }
    
    public static ProfileDAO getInstance() {
        return instance;
    }
    
    public void salvar (Profile prof) {
        EntityManager em;
        em = PersistenseUtil.getEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            if (prof.getId() != 0)
                em.persist(prof);
            else
                em.merge(prof);
            
            et.commit();
        } catch (Exception e) {
            if (et != null && et.isActive())
                et.rollback();
            throw new RuntimeException(e);
        } finally {
            PersistenseUtil.close(em);
        }
    }
    
    public List<Profile> getAllProfiles() {
        EntityManager em = PersistenseUtil.getEntityManager();
        EntityTransaction et = em.getTransaction();
        List<Profile> profiles = null;
        try {
            et.begin();
            TypedQuery<Profile> query = (TypedQuery<Profile>) em.createQuery("select p from Profile p");

            profiles = query.getResultList();
            
            for (Profile p : profiles) {
                System.out.println("Nome: " + p.getName());
            }
            
            et.commit();
        } catch (Exception e) {
            if (et != null && et.isActive())
                et.rollback();
            throw new RuntimeException(e);
        } finally {
            PersistenseUtil.close(em);
        }
        return profiles;
    }
}