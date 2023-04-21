/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controldao;

import controldao.exceptions.IllegalOrphanException;
import controldao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import dao.CatalogoRevistas;
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
public class InventarioRevistasJpaController implements Serializable {

    public InventarioRevistasJpaController() {
        this.emf = Persistence.createEntityManagerFactory("videocentroWebAJAX_00000228549PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InventarioRevistas inventarioRevistas) throws IllegalOrphanException {
        List<String> illegalOrphanMessages = null;
        CatalogoRevistas isbnRevistaOrphanCheck = inventarioRevistas.getIsbnRevista();
        if (isbnRevistaOrphanCheck != null) {
            InventarioRevistas oldInventarioRevistasOfIsbnRevista = isbnRevistaOrphanCheck.getInventarioRevistas();
            if (oldInventarioRevistasOfIsbnRevista != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The CatalogoRevistas " + isbnRevistaOrphanCheck + " already has an item of type InventarioRevistas whose isbnRevista column cannot be null. Please make another selection for the isbnRevista field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CatalogoRevistas isbnRevista = inventarioRevistas.getIsbnRevista();
            if (isbnRevista != null) {
                isbnRevista = em.getReference(isbnRevista.getClass(), isbnRevista.getIsbn());
                inventarioRevistas.setIsbnRevista(isbnRevista);
            }
            em.persist(inventarioRevistas);
            if (isbnRevista != null) {
                isbnRevista.setInventarioRevistas(inventarioRevistas);
                isbnRevista = em.merge(isbnRevista);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InventarioRevistas inventarioRevistas) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InventarioRevistas persistentInventarioRevistas = em.find(InventarioRevistas.class, inventarioRevistas.getId());
            CatalogoRevistas isbnRevistaOld = persistentInventarioRevistas.getIsbnRevista();
            CatalogoRevistas isbnRevistaNew = inventarioRevistas.getIsbnRevista();
            List<String> illegalOrphanMessages = null;
            if (isbnRevistaNew != null && !isbnRevistaNew.equals(isbnRevistaOld)) {
                InventarioRevistas oldInventarioRevistasOfIsbnRevista = isbnRevistaNew.getInventarioRevistas();
                if (oldInventarioRevistasOfIsbnRevista != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The CatalogoRevistas " + isbnRevistaNew + " already has an item of type InventarioRevistas whose isbnRevista column cannot be null. Please make another selection for the isbnRevista field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (isbnRevistaNew != null) {
                isbnRevistaNew = em.getReference(isbnRevistaNew.getClass(), isbnRevistaNew.getIsbn());
                inventarioRevistas.setIsbnRevista(isbnRevistaNew);
            }
            inventarioRevistas = em.merge(inventarioRevistas);
            if (isbnRevistaOld != null && !isbnRevistaOld.equals(isbnRevistaNew)) {
                isbnRevistaOld.setInventarioRevistas(null);
                isbnRevistaOld = em.merge(isbnRevistaOld);
            }
            if (isbnRevistaNew != null && !isbnRevistaNew.equals(isbnRevistaOld)) {
                isbnRevistaNew.setInventarioRevistas(inventarioRevistas);
                isbnRevistaNew = em.merge(isbnRevistaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = inventarioRevistas.getId();
                if (findInventarioRevistas(id) == null) {
                    throw new NonexistentEntityException("The inventarioRevistas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InventarioRevistas inventarioRevistas;
            try {
                inventarioRevistas = em.getReference(InventarioRevistas.class, id);
                inventarioRevistas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The inventarioRevistas with id " + id + " no longer exists.", enfe);
            }
            CatalogoRevistas isbnRevista = inventarioRevistas.getIsbnRevista();
            if (isbnRevista != null) {
                isbnRevista.setInventarioRevistas(null);
                isbnRevista = em.merge(isbnRevista);
            }
            em.remove(inventarioRevistas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InventarioRevistas> findInventarioRevistasEntities() {
        return findInventarioRevistasEntities(true, -1, -1);
    }

    public List<InventarioRevistas> findInventarioRevistasEntities(int maxResults, int firstResult) {
        return findInventarioRevistasEntities(false, maxResults, firstResult);
    }

    private List<InventarioRevistas> findInventarioRevistasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InventarioRevistas.class));
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

    public InventarioRevistas findInventarioRevistas(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InventarioRevistas.class, id);
        } finally {
            em.close();
        }
    }

    public int getInventarioRevistasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InventarioRevistas> rt = cq.from(InventarioRevistas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
