package DAO;

import entities.Rivenditori.Rivenditore;
import entities.UtenteETessera.Tessera;
import entities.ViaggioTratta.Tratta;
import entities.ViaggioTratta.Viaggio;
import entities.mezzi.Mezzo;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public class TrattaDao {
    private EntityManager em;

    public TrattaDao(EntityManager em) {
        this.em = em;
    }

    public void save(Tratta tratta) {
        try {
            em.getTransaction().begin();
            em.persist(tratta);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public Tratta getById(Integer id) {
        return em.find(Tratta.class, id);
    }

    public void delete(Tratta tratta) {
        try {
            em.getTransaction().begin();
            em.remove(tratta);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public void update(Tratta tratta) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.merge(tratta);
        et.commit();
    }

    public void saveAll(List<Tratta> tratte) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            for (Tratta tratta : tratte) {
                //  System.out.println(tratta);
                em.persist(tratta);
            }
            et.commit();
            System.out.println("Tratte salvati con successo");
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            System.out.println("Errore durante il salvataggio delle tratte: " + e.getMessage());
        }
    }

    public void tempoEffettivoTratta() {
        Query trattaQuery = em.createQuery("SELECT t FROM Tratta t");
        trattaQuery.getResultList().forEach(System.out::println);
        setMediaEffettivaTrattaBYTratta(trattaQuery.getResultList());
    }

    public void setMediaEffettivaTrattaBYTratta(List<Tratta> tratte){
        try {
            em.getTransaction().begin();

            Map<Tratta, LocalTime> tempoMedioPerTratta = tratte.stream()
                    .collect(Collectors.toMap(
                            tratta -> tratta,
                            tratta -> {
                                List<LocalTime> tempiEffettivi = tratta.getViaggi().stream()
                                        .map(Viaggio::getTempoEffettivo)
                                        .filter(Objects::nonNull)
                                        .collect(Collectors.toList());
                                if (tempiEffettivi.isEmpty()) {
                                    return LocalTime.MIN;
                                }
                                long tempoTotaleInSeconds = tempiEffettivi.stream()
                                        .mapToLong(tempo -> tempo.toSecondOfDay())
                                        .sum();

                                return LocalTime.ofSecondOfDay(tempoTotaleInSeconds / tempiEffettivi.size());
                            }
                    ));

            tempoMedioPerTratta.forEach((tratta, tempoMedio) -> {
                tratta.setTempoMedioTratta(tempoMedio);
                em.merge(tratta);
            });

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace(); // Gestisci l'eccezione come preferisci
        }
    }




}
