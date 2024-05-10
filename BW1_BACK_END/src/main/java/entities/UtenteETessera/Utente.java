package entities.UtenteETessera;

import entities.TitoliViaggi.Biglietto;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "utenti")

public class Utente {
@Id
@GeneratedValue
@Column(name = "id_utente")
    private UUID idUtente;
    private String nome;
    private String cognome;
    @Column(name = "data_nascita")
    private LocalDate dataNascita;
    @OneToOne(mappedBy = "utente") //Collegamento con tessera
    private Tessera tessera;

    @OneToMany(mappedBy = "utente")
    private List<Biglietto> biglietti;



    public Utente(UUID idUtente, String nome, String cognome, LocalDate dataNascita, Tessera tessera) {
        this.idUtente = idUtente;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.tessera = tessera;
    }

    public Utente() {
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public UUID getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(UUID idUtente) {
        this.idUtente = idUtente;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public Tessera getTessera() {
        return tessera;
    }

    public void setTessera(Tessera tessera) {
        this.tessera = tessera;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "idUtente=" + idUtente +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", dataNascita=" + dataNascita +
                ", tessera=" + tessera +
                '}';
    }
}
