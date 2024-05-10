package entities.ViaggioTratta;

import entities.mezzi.Mezzo;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "viaggi")
public class Viaggio {
    @Id
    @GeneratedValue
    @Column(name = "id_viaggio")
    private int idViaggio;
    @Column(name = "tempo_effettivo")
    private LocalTime tempoEffettivo;
    @Column(name = "nome_tratta")
    private String nomeTratta;
    @ManyToMany
    @JoinTable(
            name="viaggi_mezzo",
            joinColumns = @JoinColumn(name="id_viaggio"),
            inverseJoinColumns = @JoinColumn(name="id_mezzo")
    )
    private List<Mezzo> mezzi;

    @ManyToOne
    @JoinColumn(name = "id_tratta")
    private Tratta tratta;

    public Viaggio(int idViaggio, LocalTime tempoEffettivo, String nomeTratta, List<Mezzo> mezzi) {
        this.idViaggio = idViaggio;
        this.tempoEffettivo = tempoEffettivo;
        this.nomeTratta = nomeTratta;
        this.mezzi = mezzi;
    }
    public Viaggio() {    }

    public int getIdViaggio() {
        return idViaggio;
    }

    public void setIdViaggio(int idViaggio) {
        this.idViaggio = idViaggio;
    }

    public LocalTime getTempoEffettivo() {
        return tempoEffettivo;
    }

    public void setTempoEffettivo(LocalTime tempoEffettivo) {
        this.tempoEffettivo = tempoEffettivo;
    }

    public String getNomeTratta() {
        return nomeTratta;
    }

    public void setNomeTratta(String nomeTratta) {
        this.nomeTratta = nomeTratta;
    }

    public List<Mezzo> getMezzi() {
        return mezzi;
    }

    public void setTratta(Tratta tratta) {
        this.tratta = tratta;
    }

    public void setMezzi(List<Mezzo> mezzi) {
        this.mezzi = mezzi;
    }
    @Override
    public String toString() {
        return "Viaggio{" +
                "idViaggio=" + idViaggio +
                ", tempoEffettivo=" + tempoEffettivo +
                ", nomeTratta='" + nomeTratta + '\'' +
                ", mezzi=" + mezzi +
                '}';
    }
}
