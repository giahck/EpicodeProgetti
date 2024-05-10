package DAO;

import entities.UtenteETessera.Utente;
import entities.mezzi.Mezzo;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class UtenteDao {
    private EntityManager em;

    public UtenteDao(EntityManager em) {
        this.em = em;
    }

    public void save(Utente utente) {
        try {
            em.getTransaction().begin();
            em.persist(utente);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public Utente getById(Integer id) {
        return em.find(Utente.class, id);
    }

    public void delete(Utente utente) {
        try {
            em.getTransaction().begin();
            em.remove(utente);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public void update(Utente utente) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.merge(utente);
        et.commit();
    }
    public void saveAll(List<Utente> utenti) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            for (Utente utente : utenti) {
                //System.out.println(utente);
                em.persist(utente);
            }
            et.commit();
            System.out.println("Prestiti salvati con successo");
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            System.out.println("Errore durante il salvataggio dei utenti: " + e.getMessage());
        }
}
}
