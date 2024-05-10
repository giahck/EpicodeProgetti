package DAO;

import entities.UtenteETessera.Tessera;
import entities.mezzi.Mezzo;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class MezziDao {
    private EntityManager em; 

    public MezziDao(EntityManager em) {
        this.em = em;
    }

    public void save(Mezzo mezzi) {
        try {
            em.getTransaction().begin();
            em.persist(mezzi);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public Mezzo getById(Integer id) {
        return em.find(Mezzo.class, id);
    }

    public void delete(Mezzo mezzi) {
        try {
            em.getTransaction().begin();
            em.remove(mezzi);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public void update(Mezzo mezzi) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.merge(mezzi);
        et.commit();
    }
    public void saveAll(List<Mezzo> mezzi) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            for (Mezzo mezzo : mezzi) {
                //System.out.println(mezzo);
                em.persist(mezzo);
            }
            et.commit();
            System.out.println("Prestiti salvati con successo");
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            System.out.println("Errore durante il salvataggio dei mezzi: " + e.getMessage());
        }
    }

}
