package DAO;


import entities.ViaggioTratta.Viaggio;
import entities.mezzi.Mezzo;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public class ViaggioDao {
    private EntityManager em;

    public ViaggioDao(EntityManager em) {
        this.em = em;
    }

    public void save(Viaggio viaggio) {
        try {
            em.getTransaction().begin();
            em.persist(viaggio);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public Viaggio getById(Integer id) {
        return em.find(Viaggio.class, id);
    }

    public void delete(Viaggio viaggio) {
        try {
            em.getTransaction().begin();
            em.remove(viaggio);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public void update(Viaggio viaggio) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.merge(viaggio);
        et.commit();
    }
    public void saveAll(List<Viaggio> viaggi) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            for (Viaggio viaggio : viaggi) {
                //  System.out.println(viaggi);
                em.persist(viaggio);
            }
            et.commit();
            System.out.println("Viaggi salvati con successo");
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            System.out.println("Errore durante il salvataggio dei viaggi: " + e.getMessage());
        }
    }

    //query: numero viaggi per tratta dato un mezzo

    public long contaViaggiByMezzoAndTratta(String idMezzo, int idTratta) {
        UUID uuidMezzo = UUID.fromString(idMezzo);
        Mezzo mezzo = em.find(Mezzo.class, uuidMezzo);
        Query query = em.createQuery("SELECT COUNT(v) FROM Viaggio v WHERE v.tratta.idTratta = :idTratta AND :mezzo MEMBER OF v.mezzi");
        query.setParameter("idTratta", idTratta);
        query.setParameter("mezzo", mezzo);
        return (long) query.getSingleResult();

    }

    public List<LocalTime> tempoEffettivoTrattaBYMezzo(String idMezzo, int idTratta) {
        UUID uuidMezzo = UUID.fromString(idMezzo);
        Mezzo mezzo = em.find(Mezzo.class, uuidMezzo);
        // Query per il tempo effettivo di ogni viaggio
        Query tempoQuery = em.createQuery("SELECT v.tempoEffettivo FROM Viaggio v WHERE v.tratta.idTratta = :idTratta AND :mezzo MEMBER OF v.mezzi");
        tempoQuery.setParameter("idTratta", idTratta);
        tempoQuery.setParameter("mezzo", mezzo);
        return tempoQuery.getResultList();

    }






    //query tempo effettivo tratte
}
