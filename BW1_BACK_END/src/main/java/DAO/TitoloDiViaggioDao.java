package DAO;

import ResultDto.CountRivenditoriViaggi;
import ResultDto.StatoAbbonamento;
import entities.Rivenditori.Rivenditore;
import entities.TitoliViaggi.Abbonamento;
import entities.TitoliViaggi.TitoloDiViaggio;
import entities.UtenteETessera.Tessera;
import entities.UtenteETessera.Utente;
import entities.mezzi.Mezzo;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TitoloDiViaggioDao {
    private EntityManager em;

    public TitoloDiViaggioDao(EntityManager em) {
        this.em = em;
    }

    public void save(TitoloDiViaggio titoloDiViaggio) {
        try {
            em.getTransaction().begin();
            em.persist(titoloDiViaggio);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public TitoloDiViaggio getById(Integer id) {
        return em.find(TitoloDiViaggio.class, id);
    }

    public void delete(TitoloDiViaggio titoloDiViaggio) {
        try {
            em.getTransaction().begin();
            em.remove(titoloDiViaggio);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public void update(TitoloDiViaggio titoloDiViaggio) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.merge(titoloDiViaggio);
        et.commit();
    }

    public void saveAll(List<TitoloDiViaggio> titolodiviaggi) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            for (TitoloDiViaggio titolidiviaggio : titolodiviaggi) {
                //  System.out.println(titolidiviaggio);
                em.persist(titolidiviaggio);
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

    //parte delle query

//     -Deve essere possibile tenere traccia del numero di biglietti e/o abbonamenti emessi
//    in un dato periodo di tempo in totale e per punto di emissione

 public List<CountRivenditoriViaggi> getTotaleBiglietti(LocalDate inizio, LocalDate fine) {
     Query query = em.createQuery("SELECT new ResultDto.CountRivenditoriViaggi(t.rivenditore, COUNT(t) AS numTitoli) " +
             "FROM TitoloDiViaggio t " +
             "WHERE t.emissioneTitoloViaggio BETWEEN :startDate AND :endDate " +
             "GROUP BY t.rivenditore"
     );
       query.setParameter("startDate", inizio);
       query.setParameter("endDate", fine);
       return query.getResultList();
   }


   /* public void getStatoAbbonamento(String idNumeroTessera) {
        LocalDate dataOggi = LocalDate.now();
        UUID idNumeroTesString = UUID.fromString(idNumeroTessera);

        Query query = em.createQuery("SELECT a.scadenza FROM Abbonamento a WHERE a.tessera.idTessera = :idNumeroTessera");
        query.setParameter("idNumeroTessera", idNumeroTesString);

        LocalDate scadenzaAbbonamento = (LocalDate) query.getSingleResult();
        if (dataOggi.isAfter(scadenzaAbbonamento)) {
            System.out.println("Abbonamento scaduto in data: "+scadenzaAbbonamento);
        } else {
            System.out.println("Abbonamento valido fino al: " + scadenzaAbbonamento);
        }
    }*/


    /* Soluzione 2 da implementare con if nel main
    public LocalDate getStatoAbbonamentiById(String idNumeroTessera) {
        UUID idNumeroTesString = UUID.fromString(idNumeroTessera);
        Query query = em.createQuery("SELECT a.scadenza FROM Abbonamento a " +
                "WHERE a.tessera.idTessera = :idNumeroTessera"
        );
        query.setParameter("idNumeroTessera", idNumeroTesString);
        return (LocalDate) query.getSingleResult();
    }
*/

    //soluzione 3 che stampa anche il nome della tessera
public void getStatoAbbonamento3(String idNumeroTessera) {
    LocalDate dataOggi = LocalDate.now();
    UUID idNumeroTesString = UUID.fromString(idNumeroTessera);

    Query query = em.createQuery("SELECT NEW ResultDto.StatoAbbonamento(u.nome,u.cognome, a.scadenza) FROM Abbonamento a JOIN a.tessera t JOIN t.utente u WHERE t.idTessera = :idNumeroTessera");
    query.setParameter("idNumeroTessera", idNumeroTesString);

    StatoAbbonamento statoAbbonamento;
    try {
        statoAbbonamento = (StatoAbbonamento) query.getSingleResult();
        if (statoAbbonamento != null) {
            System.out.println("Nome proprietario tessera: " + statoAbbonamento.getNomeTessera() + " " + statoAbbonamento.getCognomeTessera());
            System.out.println("Scadenza abbonamento: " + statoAbbonamento.getScadenzaAbbonamento());
        } else {
            System.out.println("Nessun abbonamento trovato per il numero di tessera specificato.");
        }
    } catch (NoResultException e) {
        System.out.println("Nessun abbonamento trovato per il numero di tessera specificato.");
    }
}


    public long calcoloBigliettiVidimati(String idMezzo) {
        UUID uuidMezzo = UUID.fromString(idMezzo);
        Query query = em.createQuery("SELECT COUNT(b) FROM Biglietto b WHERE b.statusValidita = true AND b.mezzo.idMezzo = :idMezzo");
        query.setParameter("idMezzo", uuidMezzo);
        return (long) query.getSingleResult();
    }


    public long getTotaleBigliettiVidimatiPerData(LocalDate inizio, LocalDate fine) {

            Query query = em.createQuery("SELECT COUNT(b) FROM Biglietto b WHERE b.timbratura BETWEEN :startDate AND :endDate");
        query.setParameter("startDate", inizio);
        query.setParameter("endDate", fine);
        return (long) query.getSingleResult();
    }
}




//    select r.idrivenditore, count(t.*) as numTitoli from public.titoli_viaggio t
//    inner join public.rivenditori r
//    on t.id_rivenditori = r.idrivenditore where t.emissionetitoloviaggio
//    between '2024-01-01' and '2024-12-31' group by r.idrivenditore;



