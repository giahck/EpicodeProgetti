package entities.Rivenditori;

import entities.TitoliViaggi.TitoloDiViaggio;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "rivenditori")
public class Rivenditore {
    @Id
    @GeneratedValue
    private int idRivenditore;

    @OneToMany(mappedBy = "rivenditore")
    private List<TitoloDiViaggio> titoliDiViaggio;

    public Rivenditore(int idRivenditore, List<TitoloDiViaggio> titoliDiViaggio) {
        this.idRivenditore = idRivenditore;
        this.titoliDiViaggio = titoliDiViaggio;
    }
    public Rivenditore() {    }

    public int getIdRivenditore() {
        return idRivenditore;
    }

    public void setIdRivenditore(int idRivenditore) {
        this.idRivenditore = idRivenditore;
    }

    public List<TitoloDiViaggio> getTitoliDiViaggio() {
        return titoliDiViaggio;
    }

    public void setTitoliDiViaggio(List<TitoloDiViaggio> titoliDiViaggio) {
        this.titoliDiViaggio = titoliDiViaggio;
    }
    @Override
    public String toString() {
        return "Rivenditore{" +
                "idRivenditore=" + idRivenditore +
//                ", titoliDiViaggio=" + titoliDiViaggio +
                '}';
    }
}
