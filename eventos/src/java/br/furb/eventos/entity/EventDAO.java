
package br.furb.eventos.entity;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class EventDAO {
    private static EventDAO instance = new EventDAO();
    
    private EventDAO() {
        
    }
    
    public static EventDAO getInstance() {
        return instance;
    }
    
    public void salvar (Event evt) {
        EntityManager em = PersistenseUtil.getEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            if (evt.getId() != 0)
                em.persist(evt);
            else
                em.merge(evt);    
            et.commit();
        } catch (Exception e) {
            if (et != null && et.isActive())
                et.rollback();
            throw new RuntimeException(e);
        } finally {
            PersistenseUtil.close(em);
        }
    }
    
    public List<Event> getAllProfiles() {
        EntityManager em = PersistenseUtil.getEntityManager();
        EntityTransaction et = em.getTransaction();
        List<Event> events = null;
        try {
            et.begin();
            TypedQuery<Event> query = (TypedQuery<Event>) em.createQuery("select p from Profile p");

            events = query.getResultList();
            
            for (Event e : events) {
                System.out.println("Nome: " + e.getName());
            }
            
            et.commit();
        } catch (Exception e) {
            if (et != null && et.isActive())
                et.rollback();
            throw new RuntimeException(e);
        } finally {
            PersistenseUtil.close(em);
        }
        return events;
    }
}
