package entities.mezzi;

import entities.StatusMezzo.StatusMezzo;
import entities.TitoliViaggi.Biglietto;
import entities.ViaggioTratta.Viaggio;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "mezzi")
public class Mezzo {
    @Id
    @GeneratedValue
    @Column(name = "id_mezzo")
    private UUID idMezzo;

   @OneToMany(mappedBy = "mezzo")
    private List<Biglietto> biglietto;
   @OneToMany(mappedBy= "mezzo")
   private List<StatusMezzo> statusMezzo;
  @ManyToMany(mappedBy = "mezzi")
  private List<Viaggio> viaggi;

    public Mezzo(UUID idMezzo, List<Biglietto> biglietto) {
        this.idMezzo = idMezzo;
        this.biglietto = biglietto;
    }
    public Mezzo() {    }
    public UUID getIdMezzo() {
        return idMezzo;
    }

    public void setIdMezzo(UUID idMezzo) {
        this.idMezzo = idMezzo;
    }

    public List<Biglietto> getBiglietto() {
        return biglietto;
    }

    public void setBiglietto(List<Biglietto> biglietto) {
        this.biglietto = biglietto;
    }
    @Override
    public String toString() {
        return "Mezzo{" +
                "idMezzo=" + idMezzo +
                ", biglietto=" + biglietto +
                '}';
    }
}
