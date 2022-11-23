/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controldao;

import controldao.exceptions.IllegalOrphanException;
import controldao.exceptions.NonexistentEntityException;
import controldao.exceptions.PreexistingEntityException;
import dao.CatalogoRevistas;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import dao.InventarioRevistas;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author luisg
 */
public class CatalogoRevistasJpaController implements Serializable {

    public CatalogoRevistasJpaController() {
        this.emf = Persistence.createEntityManagerFactory("videocentroWebAJAX_00000228549PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CatalogoRevistas catalogoRevistas) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InventarioRevistas inventarioRevistas = catalogoRevistas.getInventarioRevistas();
            if (inventarioRevistas != null) {
                inventarioRevistas = em.getReference(inventarioRevistas.getClass(), inventarioRevistas.getId());
                catalogoRevistas.setInventarioRevistas(inventarioRevistas);
            }
            em.persist(catalogoRevistas);
            if (inventarioRevistas != null) {
                CatalogoRevistas oldIsbnRevistaOfInventarioRevistas = inventarioRevistas.getIsbnRevista();
                if (oldIsbnRevistaOfInventarioRevistas != null) {
                    oldIsbnRevistaOfInventarioRevistas.setInventarioRevistas(null);
                    oldIsbnRevistaOfInventarioRevistas = em.merge(oldIsbnRevistaOfInventarioRevistas);
                }
                inventarioRevistas.setIsbnRevista(catalogoRevistas);
                inventarioRevistas = em.merge(inventarioRevistas);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCatalogoRevistas(catalogoRevistas.getIsbn()) != null) {
                throw new PreexistingEntityException("CatalogoRevistas " + catalogoRevistas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CatalogoRevistas catalogoRevistas) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CatalogoRevistas persistentCatalogoRevistas = em.find(CatalogoRevistas.class, catalogoRevistas.getIsbn());
            InventarioRevistas inventarioRevistasOld = persistentCatalogoRevistas.getInventarioRevistas();
            InventarioRevistas inventarioRevistasNew = catalogoRevistas.getInventarioRevistas();
            List<String> illegalOrphanMessages = null;
            if (inventarioRevistasOld != null && !inventarioRevistasOld.equals(inventarioRevistasNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain InventarioRevistas " + inventarioRevistasOld + " since its isbnRevista field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (inventarioRevistasNew != null) {
                inventarioRevistasNew = em.getReference(inventarioRevistasNew.getClass(), inventarioRevistasNew.getId());
                catalogoRevistas.setInventarioRevistas(inventarioRevistasNew);
            }
            catalogoRevistas = em.merge(catalogoRevistas);
            if (inventarioRevistasNew != null && !inventarioRevistasNew.equals(inventarioRevistasOld)) {
                CatalogoRevistas oldIsbnRevistaOfInventarioRevistas = inventarioRevistasNew.getIsbnRevista();
                if (oldIsbnRevistaOfInventarioRevistas != null) {
                    oldIsbnRevistaOfInventarioRevistas.setInventarioRevistas(null);
                    oldIsbnRevistaOfInventarioRevistas = em.merge(oldIsbnRevistaOfInventarioRevistas);
                }
                inventarioRevistasNew.setIsbnRevista(catalogoRevistas);
                inventarioRevistasNew = em.merge(inventarioRevistasNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = catalogoRevistas.getIsbn();
                if (findCatalogoRevistas(id) == null) {
                    throw new NonexistentEntityException("The catalogoRevistas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CatalogoRevistas catalogoRevistas;
            try {
                catalogoRevistas = em.getReference(CatalogoRevistas.class, id);
                catalogoRevistas.getIsbn();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The catalogoRevistas with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            InventarioRevistas inventarioRevistasOrphanCheck = catalogoRevistas.getInventarioRevistas();
            if (inventarioRevistasOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CatalogoRevistas (" + catalogoRevistas + ") cannot be destroyed since the InventarioRevistas " + inventarioRevistasOrphanCheck + " in its inventarioRevistas field has a non-nullable isbnRevista field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(catalogoRevistas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CatalogoRevistas> findCatalogoRevistasEntities() {
        return findCatalogoRevistasEntities(true, -1, -1);
    }

    public List<CatalogoRevistas> findCatalogoRevistasEntities(int maxResults, int firstResult) {
        return findCatalogoRevistasEntities(false, maxResults, firstResult);
    }

    private List<CatalogoRevistas> findCatalogoRevistasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CatalogoRevistas.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public CatalogoRevistas findCatalogoRevistas(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CatalogoRevistas.class, id);
        } finally {
            em.close();
        }
    }

    public int getCatalogoRevistasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CatalogoRevistas> rt = cq.from(CatalogoRevistas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
