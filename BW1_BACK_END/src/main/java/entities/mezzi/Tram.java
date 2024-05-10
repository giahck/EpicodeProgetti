package entities.mezzi;

import entities.TitoliViaggi.Biglietto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "mezzi_tram")
public class Tram extends Mezzo{
    @Column(name = "capienza_max")
    protected static final int CAPIENZA_MAX = 300;
    @Column(length = 7)
    public String targa;

    public Tram(UUID idMezzo, List<Biglietto> biglietto, String targa) {
        super(idMezzo, biglietto);
        this.targa = targa;
    }
    public Tram() {
    }


    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }
    @Override
    public String toString() {
        return "Tram{" +
                "targa='" + targa + '\'' +
                '}';
    }
}
