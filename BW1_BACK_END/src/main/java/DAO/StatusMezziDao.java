package DAO;

import entities.StatusMezzo.StatusMezzo;
import entities.UtenteETessera.Tessera;
import entities.mezzi.Mezzo;
import enums.EnumStatus;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class StatusMezziDao {
    private EntityManager em;

    public StatusMezziDao(EntityManager em) {
        this.em = em;
    }

    public void save(StatusMezzo statusMezzi) {
        try {
            em.getTransaction().begin();
            em.persist(statusMezzi);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public StatusMezzo getById(Integer id) {
        return em.find(StatusMezzo.class, id);
    }

    public void delete(StatusMezzo statusMezzi) {
        try {
            em.getTransaction().begin();
            em.remove(statusMezzi);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public void update(StatusMezzo statusMezzi) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.merge(statusMezzi);
        et.commit();
    }

    public void saveAll(List<StatusMezzo> statusMezzi) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            for (StatusMezzo stusMezzo : statusMezzi) {
               // System.out.println(stusMezzo);
                em.persist(stusMezzo);
            }
            et.commit();
            System.out.println("Prestiti salvati con successo");
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            System.out.println("Errore durante il salvataggio dei statusMezzi: " + e.getMessage());
        }
    }
    //stampa tutti i mezzi di uno stato
    public void getStatusMezzi(EnumStatus status){
        Query query = em.createQuery("Select s from StatusMezzo s where status like :status");
        query.setParameter("status", status);
        List<StatusMezzo> statusMezzi = query.getResultList();
        for (StatusMezzo statusMezzo : statusMezzi) {
            System.out.println(statusMezzo);
        }
    }
    //stampa solo i stati di un mezzo ricercato
    public  void getStatusMezzo(String id,EnumStatus status){
        UUID uuid=UUID.fromString(id);
        Query query= em.createQuery("select s from StatusMezzo s where status like :status AND s.mezzo.idMezzo= :uuid ");
        query.setParameter("status", status);
        query.setParameter("uuid", uuid);
        List<StatusMezzo> statusMezzi = query.getResultList();
        statusMezzi.forEach(System.out::println);
    }
}