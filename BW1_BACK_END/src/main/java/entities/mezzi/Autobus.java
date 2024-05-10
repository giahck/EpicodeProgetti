package entities.mezzi;

import entities.TitoliViaggi.Biglietto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;
@Entity
@Table(name = "mezzi_autobus")
public class Autobus extends Mezzo{
    @Column(name = "capienza_max")
    protected static final int CAPIENZA_MAX = 40;
    @Column(length = 7)
    public String targa;

    public Autobus(UUID idMezzo, List<Biglietto> biglietto, String targa) {
        super(idMezzo, biglietto);
        this.targa = targa;
    }
    public Autobus() {}

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }
    @Override
    public String toString() {
        return "Autobus{" +
                "targa='" + targa + '\'' +
                '}';
    }
}
