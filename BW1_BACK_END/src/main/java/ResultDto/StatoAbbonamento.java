package ResultDto;

import java.time.LocalDate;

public class StatoAbbonamento {

    private String nomeTessera;
    private String cognomeTessera;
    private LocalDate scadenzaAbbonamento;

    public StatoAbbonamento(String nomeTessera,String cognomeTessera, LocalDate scadenzaAbbonamento) {
        this.nomeTessera = nomeTessera;
        this.scadenzaAbbonamento = scadenzaAbbonamento;
        this.cognomeTessera = cognomeTessera;
    }

    // Getters and setters

    public String getNomeTessera() {
        return nomeTessera;
    }

    public void setNomeTessera(String nomeTessera) {
        this.nomeTessera = nomeTessera;
    }

    public String getCognomeTessera() {
        return cognomeTessera;
    }

    public void setCognomeTessera(String cognomeTessera) {
        this.cognomeTessera = cognomeTessera;
    }

    public LocalDate getScadenzaAbbonamento() {
        return scadenzaAbbonamento;
    }

    public void setScadenzaAbbonamento(LocalDate scadenzaAbbonamento) {
        this.scadenzaAbbonamento = scadenzaAbbonamento;
    }
}
