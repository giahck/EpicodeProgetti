package entities.TitoliViaggi;

import entities.Rivenditori.Rivenditore;
import entities.UtenteETessera.Utente;
import entities.mezzi.Mezzo;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name="biglietti")
public class Biglietto extends TitoloDiViaggio {

private boolean statusValidita;

private LocalDate timbratura;

    @ManyToOne
    @JoinColumn(name ="id_utente")
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "id_mezzo")
    private Mezzo mezzo;


      public Biglietto() {
        }

    public Biglietto(UUID idTitoloViaggio, Rivenditore rivenditore, LocalDate emissioneTitoloViaggio, boolean statusValidita, LocalDate timbratura, Utente utente, Mezzo mezzo) {
        super(idTitoloViaggio, rivenditore, emissioneTitoloViaggio);
        this.statusValidita = statusValidita;
        this.timbratura = timbratura;
        this.utente = utente;
        this.mezzo = mezzo;
    }

    public boolean isValidita() {
        return statusValidita;
    }

    public void setstatusValidita(boolean statusValidita) {
        this.statusValidita = statusValidita;
    }

    public LocalDate getTimbratura() {
        return timbratura;
    }

    public void setTimbratura(LocalDate timbratura) {
        this.timbratura = timbratura;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Mezzo getMezzo() {
        return mezzo;
    }

    public void setMezzo(Mezzo mezzo) {
        this.mezzo = mezzo;
    }
    @Override
    public String toString() {
        return super.toString() + "Biglietto{" +
                "validita=" + statusValidita +
                ", timbratura=" + timbratura +
                ", utente=" + utente +
                ", mezzo=" + mezzo +
                '}';
    }
}
