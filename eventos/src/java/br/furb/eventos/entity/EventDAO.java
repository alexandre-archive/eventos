
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
    
    public void save (Event evt) {
        EntityManager em = PersistenseUtil.getEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            if (evt.getId() == 0)
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
    
    public Event getById(long id) {
        Event e;
        
        EntityManager em = PersistenseUtil.getEntityManager();
        e = em.find(Event.class, id);
        PersistenseUtil.close(em);
        
        return e;
    }
    
    public List<Event> getEventsUser(long idUser)
    {
        EntityManager em = PersistenseUtil.getEntityManager();
        
        List l = em.createNativeQuery("Select e.* from Event e where e.owner_id = ?1", Event.class)
                .setParameter(1, idUser)
                .getResultList();
        
        if (l.isEmpty())
        {
            return null;
        }
        
        return l;
    }
    
    public List<Event> getOthersEvents(long idUser)
    {
        EntityManager em = PersistenseUtil.getEntityManager();
        
        List l = em.createNativeQuery("Select e.* from Event e where e.owner_id <> ?1", Event.class)
                .setParameter(1, idUser)
                .getResultList();
        
        if (l.isEmpty())
        {
            return null;
        }
        
        return l;
    }
    
    public List<Event> getAllEvents() {
        EntityManager em = PersistenseUtil.getEntityManager();
        EntityTransaction et = em.getTransaction();
        List<Event> events = null;
        try {
            et.begin();
            TypedQuery<Event> query = (TypedQuery<Event>) em.createQuery("select e from Event e");

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
