package entities.TitoliViaggi;

import entities.Rivenditori.Rivenditore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "titoli_viaggio")
public class TitoloDiViaggio {
    @Id
    @GeneratedValue
    @Column(name = "id_titolo_viaggio")
    private UUID idTitoloViaggio;


    @ManyToOne
    @JoinColumn(name = "id_rivenditori")
    private Rivenditore rivenditore;
    @Column(name = "data_di_emissione")
    private LocalDate emissioneTitoloViaggio;
    public TitoloDiViaggio() {    }
    public TitoloDiViaggio(UUID idTitoloViaggio, Rivenditore rivenditore, LocalDate emissioneTitoloViaggio) {
        this.idTitoloViaggio = idTitoloViaggio;
        this.rivenditore = rivenditore;
        this.emissioneTitoloViaggio = emissioneTitoloViaggio;
    }

    public UUID getIdTitoloViaggio() {
        return idTitoloViaggio;
    }

    public void setIdTitoloViaggio(UUID idTitoloViaggio) {
        this.idTitoloViaggio = idTitoloViaggio;
    }

    public Rivenditore getRivenditore() {
        return rivenditore;
    }

    public void setRivenditore(Rivenditore rivenditore) {
        this.rivenditore = rivenditore;
    }

    public LocalDate getEmissioneTitoloViaggio() {
        return emissioneTitoloViaggio;
    }

    public void setEmissioneTitoloViaggio(LocalDate emissioneTitoloViaggio) {
        this.emissioneTitoloViaggio = emissioneTitoloViaggio;
    }
    @Override
    public String toString() {
        return  "TitoloDiViaggio{" +
                "idTitoloViaggio=" + idTitoloViaggio +
                ", rivenditore=" + rivenditore +
                ", emissioneTitoloViaggio=" + emissioneTitoloViaggio +
                '}';
    }

}
