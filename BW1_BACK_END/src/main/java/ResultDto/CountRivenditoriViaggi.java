package ResultDto;

import entities.Rivenditori.Rivenditore;

public class CountRivenditoriViaggi {
    private Rivenditore rivenditore;
    private Long numTitoli;

    public CountRivenditoriViaggi(Rivenditore rivenditore, Long numTitoli) {
        this.rivenditore = rivenditore;
        this.numTitoli = numTitoli;
    }

    public CountRivenditoriViaggi() {}

    public Rivenditore getRivenditore() {
        return rivenditore;
    }

    public void setRivenditore(Rivenditore rivenditore) {
        this.rivenditore = rivenditore;
    }

    public Long getNumTitoli() {
        return numTitoli;
    }

    public void setNumTitoli(Long numTitoli) {
        this.numTitoli = numTitoli;
    }
    @Override
    public String toString() {
        return "CountRivenditoriViaggi{" +
                "rivenditore=" + rivenditore +
                ", numTitoli=" + numTitoli +
                '}';
    }
}
