package DAO;

import entities.StatusMezzo.StatusMezzo;
import entities.UtenteETessera.Tessera;
import entities.mezzi.Autobus;
import entities.mezzi.Mezzo;
import entities.mezzi.Tram;
import enums.EnumStatus;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    //stampa tutti i stati dei mezzi anchhe quelli risolti
    public String getStatusMezzi2(EnumStatus status){
        Query query = em.createQuery("Select s from StatusMezzo s where status like :status ");
        query.setParameter("status", status);
        List<StatusMezzo> statusMezzi = query.getResultList();
        StringBuilder output = new StringBuilder();
        return statusMezzi.stream()
                .map(StatusMezzo::toString)
                .collect(Collectors.joining("\n"));
    }
    //stampa tutti i mezzi nello stato atuale
    public String getStatusMezzi(EnumStatus status) {
        Query query = em.createQuery("SELECT s FROM StatusMezzo s WHERE s.status = :status AND s.dataInizio IS NOT NULL AND s.dataFine IS NULL");
        query.setParameter("status", status);
        List<StatusMezzo> statusMezzi = query.getResultList();
        return statusMezzi.stream()
                .map(statusMezzo -> {
                    String tipoMezzo = "";
                    String targa = "";
                    if (statusMezzo.getMezzo() instanceof Autobus) {
                        tipoMezzo = "Autobus";
                        targa = ((Autobus) statusMezzo.getMezzo()).getTarga();
                    } else if (statusMezzo.getMezzo() instanceof Tram) {
                        tipoMezzo = "Tram";
                        targa = ((Tram) statusMezzo.getMezzo()).getTarga();
                    }
                    return tipoMezzo + " - Targa: " + targa +
                            " - In manutenzione dal " + statusMezzo.getDataInizio();
                })
                .collect(Collectors.joining("\n"));
    }
    //stampa solo i stati di un mezzo ricercato
    public String getStatusMezzo(String id, EnumStatus status) {
        UUID uuid = UUID.fromString(id);
        Query query = em.createQuery("select s from StatusMezzo s where status like :status AND s.mezzo.idMezzo= :uuid ");
        query.setParameter("status", status);
        query.setParameter("uuid", uuid);
        List<StatusMezzo> statusMezzi = query.getResultList();
        StringBuilder result = new StringBuilder();
        statusMezzi.forEach(statusMezzo -> result.append(statusMezzo.toString()).append("\n"));
        return result.toString();
    }
}