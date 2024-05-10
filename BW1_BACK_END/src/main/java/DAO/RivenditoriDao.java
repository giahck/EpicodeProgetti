package DAO;

import entities.Rivenditori.Rivenditore;
import entities.UtenteETessera.Tessera;
import entities.mezzi.Mezzo;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class RivenditoriDao {
    private EntityManager em;

    public RivenditoriDao(EntityManager em) {
        this.em = em;
    }

    public void save(Rivenditore rivenditore) {
        try {
            em.getTransaction().begin();
            em.persist(rivenditore);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public Rivenditore getById(Integer id) {
        return em.find(Rivenditore.class, id);
    }

    public void delete(Rivenditore rivenditore) {
        try {
            em.getTransaction().begin();
            em.remove(rivenditore);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public void update(Rivenditore rivenditore) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.merge(rivenditore);
        et.commit();
    }

    public void saveAll(List<Rivenditore> rivenditori) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            for (Rivenditore rivenditore : rivenditori) {
                //  System.out.println(rivenditore);
                em.persist(rivenditore);
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