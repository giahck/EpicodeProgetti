package entities.Rivenditori;

import entities.TitoliViaggi.TitoloDiViaggio;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "rivenditori_autorizzati")
public class RivenditoreAutorizzato extends Rivenditore {
    private String nomeRivenditore;
    private String indirizzo;

    public RivenditoreAutorizzato(int idRivenditore , List<TitoloDiViaggio> titoliDiViaggio, String nomeRivenditore, String indirizzo) {
        super(idRivenditore, titoliDiViaggio);
        this.nomeRivenditore = nomeRivenditore;
        this.indirizzo = indirizzo;
    }
    public RivenditoreAutorizzato() {    }

    public String getNomeRivenditore() {
        return nomeRivenditore;
    }

    public void setNomeRivenditore(String nomeRivenditore) {
        this.nomeRivenditore = nomeRivenditore;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }
    @Override
    public String toString() {
        return super.toString()+"RivenditoreAutorizzato{" +
                "nomeRivenditore='" + nomeRivenditore + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                '}';
    }
}
