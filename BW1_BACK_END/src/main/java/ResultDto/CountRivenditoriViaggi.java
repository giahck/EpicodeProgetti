package ResultDto;

import entities.Rivenditori.Rivenditore;
import entities.TitoliViaggi.TitoloDiViaggio;

public class CountRivenditoriViaggi {
    private Rivenditore rivenditore;
    private Long numTitoli;
    private TitoloDiViaggio titoloDiViaggio;

    public CountRivenditoriViaggi(Rivenditore rivenditore, Long numTitoli, TitoloDiViaggio titoloDiViaggio) {
        this.rivenditore = rivenditore;
        this.numTitoli = numTitoli;
        this.titoloDiViaggio = titoloDiViaggio;
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

    public TitoloDiViaggio getTitoloDiViaggio() {
        return titoloDiViaggio;
    }

    public void setTitoloDiViaggio(TitoloDiViaggio titoloDiViaggio) {
        this.titoloDiViaggio = titoloDiViaggio;
    }

    @Override
    public String toString() {
        return "CountRivenditoriViaggi{" +
                "rivenditore=" + rivenditore +
                ", numTitoli=" + numTitoli +
                ", titoloDiViaggio=" + titoloDiViaggio +
                '}';
    }
}
