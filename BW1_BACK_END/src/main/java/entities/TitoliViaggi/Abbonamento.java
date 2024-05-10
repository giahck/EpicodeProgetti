package entities.TitoliViaggi;

import entities.Rivenditori.Rivenditore;
import entities.UtenteETessera.Tessera;
import enums.EmissioneAbbonamento;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "abbonamenti")
public class Abbonamento extends TitoloDiViaggio {

    @Enumerated(EnumType.STRING)
    @Column(name = "emissione_abbonamento")
    private EmissioneAbbonamento emissione;

    private LocalDate scadenza;


    @OneToOne
    @JoinColumn(name = "id_tessera")
    private Tessera tessera;

    public Abbonamento() {
    }

    public Abbonamento(UUID idTitoloViaggio, Rivenditore rivenditore, LocalDate emissioneTitoloViaggio, EmissioneAbbonamento emissione, Tessera tessera) {
        super(idTitoloViaggio, rivenditore, emissioneTitoloViaggio);
        this.emissione = emissione;
        this.tessera = tessera;
    }

    @PrePersist
    public void prePersist() {
        if (emissione == EmissioneAbbonamento.MENSILE) {
            this.scadenza = getEmissioneTitoloViaggio().plusMonths(1);
        } else {
            this.scadenza = getEmissioneTitoloViaggio().plusDays(7);
        }
    }

    public EmissioneAbbonamento getEmissione() {
        return emissione;
    }

    public void setEmissione(EmissioneAbbonamento emissione) {
        this.emissione = emissione;
    }

    public Tessera getTessera() {
        return tessera;
    }

    public void setTessera(Tessera tessera) {
        this.tessera = tessera;
    }

    @Override
    public String toString() {
        return super.toString() + "Abbonamento{" +
                "emissione=" + emissione +
                /*", tessera=" + tessera +*/
                '}';
    }
}
