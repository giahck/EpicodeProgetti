package entities.StatusMezzo;

import entities.mezzi.Mezzo;
import enums.EnumStatus;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "status_mezzi")
public class StatusMezzo {
    @Id @GeneratedValue
    @Column(name = "id_status_mezzo")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_mezzo")
    private Mezzo mezzo;
    @Enumerated(EnumType.STRING)
    private EnumStatus status;
    @Column(name = "data_inizio")
    private LocalDate dataInizio;
    @Column(name = "data_fine")
    private LocalDate dataFine;

    public StatusMezzo(Mezzo mezzo, EnumStatus status, LocalDate dataInizio, LocalDate dataFine) {
        this.mezzo = mezzo;
        this.status = status;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
    }
    public StatusMezzo() {    }

    public Mezzo getMezzo() {
        return mezzo;
    }

    public void setMezzo(Mezzo mezzo) {
        this.mezzo = mezzo;
    }

    public EnumStatus getStatus() {
        return status;
    }

    public void setStatus(EnumStatus status) {
        this.status = status;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }
    @Override
    public String toString() {
        return "StatusMezzo{" +
                "mezzo=" + mezzo +
                ", status=" + status +
                ", dataInizio=" + dataInizio +
                ", dataFine=" + dataFine +
                '}';
    }
}
