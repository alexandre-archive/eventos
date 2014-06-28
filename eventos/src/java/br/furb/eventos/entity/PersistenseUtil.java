
package br.furb.eventos.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenseUtil {
    private static EntityManagerFactory emf = null;
    
    private PersistenseUtil() {}
    
    public static EntityManager getEntityManager() {
        if (emf == null)
            emf = Persistence.createEntityManagerFactory("eventosPU");
        return emf.createEntityManager();
    }
    
    public static void close(EntityManager em) {
        if (em != null)
            em.close();
    }
}
