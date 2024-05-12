package DAO;

import ResultDto.StatoAbbonamento;
import entities.Rivenditori.Rivenditore;
import entities.UtenteETessera.Tessera;
import entities.UtenteETessera.Utente;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class TesseraDao {
    private EntityManager em;

    public TesseraDao(EntityManager em) {
        this.em = em;
    }

    public void save(Tessera tessera) {
        try {
            em.getTransaction().begin();
            em.persist(tessera);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public Tessera getById(Integer id) {
        return em.find(Tessera.class, id);
    }

    public void delete(Tessera tessera) {
        try {
            em.getTransaction().begin();
            em.remove(tessera);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public void update(Tessera tessera) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.merge(tessera);
        et.commit();
    }

    public void saveAll(List<Tessera> tessere) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            for (Tessera tessera : tessere) {
                //  System.out.println(tessere);
                em.persist(tessera);
            }
            et.commit();
            System.out.println("Tessere salvate con successo");
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            System.out.println("Errore durante il salvataggio delle tessere: " + e.getMessage());
        }
    }

    // Soluzione 2 da implementare con if nel main
    /*public void getValiditaTessera2(String idNumeroTessera) {
        UUID idNumeroTesString = UUID.fromString(idNumeroTessera);

        Query query = em.createQuery("SELECT t.scadenza FROM Tessera t " +
                "WHERE t.idTessera = :idNumeroTessera"
        );
        query.setParameter("idNumeroTessera", idNumeroTesString);
        LocalDate DataScadenza = (LocalDate) query.getSingleResult();

        if (DataScadenza.isAfter(LocalDate.now())) {
            System.out.println("Tessera scaduta");
            //System.out.println("Nome proprietario tessera: " + statoAbbonamento.getNomeTessera()+" "+statoAbbonamento.getCognomeTessera());
            //System.out.println("Scadenza abbonamento: " + statoAbbonamento.getScadenzaAbbonamento());
        } else {
            System.out.println("Tessera valida, scadenza prevista in data: " + DataScadenza);
        }*/

    public String getValiditaTessera(String idNumeroTessera) {
        UUID idNumeroTesString = UUID.fromString(idNumeroTessera);
        Query query = em.createQuery("SELECT t FROM Tessera t " +
                "WHERE t.idTessera = :idNumeroTessera"
        );
        query.setParameter("idNumeroTessera", idNumeroTesString);

        try {
            Tessera tessera = (Tessera) query.getSingleResult();
            LocalDate scadenza = tessera.getScadenza();

            if (LocalDate.now().isBefore(scadenza)) {
                String output = "La tessera è valida.\n";
                output += "Nome e cognome proprietario/a tessera: " + tessera.getUtente().getNome()+ " "+tessera.getUtente().getCognome()+ " nato/a il: "+tessera.getUtente().getDataNascita() + "\n";
                output += "Scadenza abbonamento prevista in data: " + tessera.getScadenza();
                return output;
            } else {
                return "La tessera non è valida. È necessario rinnovare anche l'abbonamento.";
            }
        } catch (NoResultException e) {
            return "Nessuna tessera trovata per l'id specificato.";
        }
    }

    }

