package entities.Rivenditori;

import entities.TitoliViaggi.TitoloDiViaggio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "distributori_automatici")
public class DistributoreAutomatico extends Rivenditore {
    private boolean statoServizio;
    @Column(length = 4)
    private String nomeDistributore;

    public DistributoreAutomatico(int idRivenditore, List<TitoloDiViaggio> titoliDiViaggio, boolean statoServizio, String nomeDistributore) {
        super(idRivenditore, titoliDiViaggio);
        this.statoServizio = statoServizio;
        this.nomeDistributore=nomeDistributore;
    }
    public DistributoreAutomatico() {    }

    public String getNomeDistributore() {
        return nomeDistributore;
    }

    public void setNomeDistributore(String nomeDistributore) {
        this.nomeDistributore = nomeDistributore;
    }

    public boolean isStatoServizio() {
        return statoServizio;
    }

    public void setStatoServizio(boolean statoServizio) {
        this.statoServizio = statoServizio;
    }
    @Override
    public String toString() {
        return super.toString()+"DistributoreAutomatico{" +
                "statoServizio=" + statoServizio +
                "nomeDistributore=" + nomeDistributore +
                '}';
    }
}
