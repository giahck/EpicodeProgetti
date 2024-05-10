package entities.UtenteETessera;

import entities.TitoliViaggi.Abbonamento;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tessere")

public class Tessera {
    @Id
    @GeneratedValue
    @Column(name = "id_tessera")
    private UUID idTessera;
    @OneToOne(mappedBy = "tessera")
    private Abbonamento abbonamento;


    private LocalDate emissione;

    private LocalDate scadenza;
    @PrePersist
    public void prePersist() {
        if (this.scadenza == null) {
            this.scadenza = this.emissione.plusYears(1);
        }
    }

    @OneToOne
    @JoinColumn(name = "id_utente",unique = true)//fk
    // collegamento con utente con prioritario utente
    private Utente utente;

    public Tessera( LocalDate emissione, Utente utente) {
        this.idTessera = UUID.randomUUID();
        this.emissione = emissione;
        this.utente = utente;
    }
    public Tessera() {
    }

    public UUID getIdTessera() {
        return idTessera;
    }

    public Abbonamento getAbbonamento() {
        return abbonamento;
    }

    public void setAbbonamento(Abbonamento abbonamento) {
        this.abbonamento = abbonamento;
    }

    public LocalDate getEmissione() {
        return emissione;
    }

    public void setEmissione(LocalDate emissione) {
        this.emissione = emissione;
    }

    public LocalDate getScadenza() {
        return scadenza;
    }

    public void setScadenza(LocalDate scadenza) {
        this.scadenza = scadenza;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }
    @Override
    public String toString() {
        return "Tessera{" +
                "idTessera=" + idTessera +
                /*", abbonamento=" + abbonamento +*/
                ", emissione=" + emissione +
                ", scadenza=" + scadenza +
                /*", utente=" + utente +*/
                '}';
    }
}
