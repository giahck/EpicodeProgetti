package entities.ViaggioTratta;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "tratte")
public class Tratta {
    @Id
    @GeneratedValue
    @Column(name = "id_tratta")
    private int idTratta;
    @Column(name = "nome_partenza")
    private String nomePartenza;//via di partenza
    @Column(name = "nome_arrivo")
    private String nomeArrivo;//via di arrivo
    @OneToMany(mappedBy = "tratta")
    private List<Viaggio> viaggi;
    @Column(name = "tempo_medio_tratta")
    private LocalTime tempoMedioTratta;

    public Tratta(int idTratta, String nomePartenza, String nomeArrivo, List<Viaggio> viaggi, LocalTime tempoMedioTratta) {
        this.idTratta = idTratta;
        this.nomePartenza = nomePartenza;
        this.nomeArrivo = nomeArrivo;
        this.viaggi = viaggi;
        this.tempoMedioTratta = tempoMedioTratta;
    }
    public Tratta() {    }

    public int getIdTratta() {
        return idTratta;
    }

    public void setIdTratta(int idTratta) {
        this.idTratta = idTratta;
    }

    public String getNomePartenza() {
        return nomePartenza;
    }

    public void setNomePartenza(String nomePartenza) {
        this.nomePartenza = nomePartenza;
    }

    public String getNomeArrivo() {
        return nomeArrivo;
    }

    public void setNomeArrivo(String nomeArrivo) {
        this.nomeArrivo = nomeArrivo;
    }

    public List<Viaggio> getViaggi() {
        return viaggi;
    }

    public void setViaggi(List<Viaggio> viaggi) {
        this.viaggi = viaggi;
    }

    public LocalTime getTempoMedioTratta() {
        return tempoMedioTratta;
    }

    public void setTempoMedioTratta(LocalTime tempoMedioTratta) {
        this.tempoMedioTratta = tempoMedioTratta;
    }
    @Override
    public String toString() {
        return "Tratta{" +
                "idTratta=" + idTratta +
                ", nomePartenza='" + nomePartenza + '\'' +
                ", nomeArrivo='" + nomeArrivo + '\'' +
                ", viaggi=" + viaggi +
                ", tempoMedioTratta=" + tempoMedioTratta +
                '}';
    }
}
