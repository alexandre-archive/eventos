package br.furb.eventos.dao;

import br.furb.eventos.entity.Permission;
import br.furb.eventos.entity.PersistenseUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class PermissionDAO {

    private static PermissionDAO instance = new PermissionDAO();

    private PermissionDAO() {

    }

    public static PermissionDAO getInstance() {
        return instance;
    }

    public void salvar(Permission perm) {
        EntityManager em = PersistenseUtil.getEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            if (perm.getId() != 0) {
                em.persist(perm);
            } else {
                em.merge(perm);
            }
            et.commit();
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            PersistenseUtil.close(em);
        }
    }

    public List<Permission> getAllProfiles() {
        EntityManager em = PersistenseUtil.getEntityManager();
        EntityTransaction et = em.getTransaction();
        List<Permission> permissions = null;
        try {
            et.begin();
            TypedQuery<Permission> query = (TypedQuery<Permission>) em.createQuery("select p from Profile p");

            permissions = query.getResultList();

            for (Permission p : permissions) {
                System.out.println("Nome: " + p.getName());
            }

            et.commit();
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            PersistenseUtil.close(em);
        }
        return permissions;
    }
}
