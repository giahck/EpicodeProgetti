import DAO.*;
import ResultDto.CountRivenditoriViaggi;
import entities.Rivenditori.DistributoreAutomatico;
import entities.Rivenditori.RivenditoreAutorizzato;
import entities.TitoliViaggi.Abbonamento;
import entities.TitoliViaggi.Biglietto;
import enums.EnumStatus;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

import enums.EnumStatus;
public class Controller implements Initializable {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("bw1");
    @FXML
    private GridPane gridPane1;

    @FXML
    private Text idText;
    @FXML
    private TextArea idTextArea;

    @FXML
    private ComboBox<EnumStatus> idComboBox;
    @FXML
    void shutdown() {
        emf.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idComboBox.setItems(FXCollections.observableArrayList(EnumStatus.values()));
       //il listener serve  per rilevare quando viene selezionato un pulsante in modo che cosi si possa aggiornare l'ho stato della visibilta in questo caso del ComboBox
    }


    @FXML
    void faiVedere() {
        gridPane1.setVisible(!gridPane1.isVisible());
    }

    @FXML
    void Q1() {
        idText.setText("Query.1 numero di biglietti e/o abbonamenti emessi");
        TitoloDiViaggioDao titoloDiViaggioDao = new TitoloDiViaggioDao(emf.createEntityManager());
        List<CountRivenditoriViaggi> results = titoloDiViaggioDao.getTotaleBiglietti(LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 12, 31));
        StringBuilder risultatiText = new StringBuilder();
        results.forEach(result -> {
            if (result.getRivenditore() instanceof RivenditoreAutorizzato) {
                RivenditoreAutorizzato autorizzato = (RivenditoreAutorizzato) result.getRivenditore();
                risultatiText.append("Il rivenditore autorizzato '").append(autorizzato.getNomeRivenditore());
            } else if (result.getRivenditore() instanceof DistributoreAutomatico) {
                DistributoreAutomatico distributore = (DistributoreAutomatico) result.getRivenditore();
                risultatiText.append("Il distributore automatico '").append(distributore.getNomeDistributore());
            } else {
                risultatiText.append("Nessun risultato trovato.\n");
                return;
            }
            // Aggiungi informazioni sul tipo di titolo (biglietto o abbonamento)
            risultatiText.append("' ha emesso ");
            if (result.getTitoloDiViaggio() instanceof Biglietto) {
                risultatiText.append("il biglietto");
            } else if (result.getTitoloDiViaggio() instanceof Abbonamento) {
                risultatiText.append("l'abbonamento");
            }
            risultatiText.append(" numero ").append(result.getNumTitoli()).append(" di viaggio.\n");
        });
        idTextArea.setVisible(true);
        idTextArea.setText(risultatiText.toString());
      //  emf.close();
    }

    @FXML
    void Q2() {
        idText.setText("Query.2 stato di un abbonamento dato il numero di tessera");
        TitoloDiViaggioDao titoloDiViaggioDao = new TitoloDiViaggioDao(emf.createEntityManager());
        titoloDiViaggioDao.getStatoAbbonamento3("25ef5ece-046e-4399-bf96-59231de10b1b",idTextArea);

     //   emf.close();
    }

    @FXML
    void Q3() {//modifica il testo
        idText.setText("Query.3 validità tessera");
        TesseraDao tesseraDao = new TesseraDao(emf.createEntityManager());
        idTextArea.setVisible(true);
        idTextArea.setText( tesseraDao.getValiditaTessera("25ef5ece-046e-4399-bf96-59231de10b1b" ));
     //   emf.close();
    }

    @FXML
    void Q4() {
        idText.setText("Query.4 status mezzi in manutenzione/inServizio attualmente");
        StatusMezziDao statusMezzoDao = new StatusMezziDao(emf.createEntityManager());
        idTextArea.setVisible(true);
        // Aggiungi un listener per rilevare quando il valore della ComboBox cambia
        idComboBox.setVisible(!idComboBox.isVisible());
        idComboBox.valueProperty().addListener((observable, oldValue, stato) -> {
            // Aggiorna l'output quando il valore cambia
            idTextArea.setText(stato != null ? statusMezzoDao.getStatusMezzi(stato) : "");
        });
    }



    @FXML
    void Q4variant2() {
        idText.setText("Query.4 tutti i status dei mezzi dato uno stato");
        StatusMezziDao statusMezzoDao = new StatusMezziDao(emf.createEntityManager());
        idTextArea.setVisible(true);
        idComboBox.setVisible(!idComboBox.isVisible());
        idComboBox.valueProperty().addListener((observable, oldValue, stato) -> {
            // Aggiorna l'output quando il valore cambia
            idTextArea.setText(stato != null ? statusMezzoDao.getStatusMezzi2(stato) : "");
        });
    }

    @FXML
    void Q5() {
        idText.setText("Query.5 aggiornamento stato mezzo in manutenzione/inServizio");
        StatusMezziDao statusMezzoDao = new StatusMezziDao(emf.createEntityManager());
        idTextArea.setVisible(true);
        idComboBox.setVisible(!idComboBox.isVisible());
        //serve a poco sta cosa
        idComboBox.valueProperty().addListener((observable, oldValue, stato) -> {
            // Aggiorna l'output quando il valore cambia
            idTextArea.setText(stato != null ? statusMezzoDao.getStatusMezzo("2fabefbe-867e-473c-8559-2db52b79f5e6",stato) : "");
        });
    }

    @FXML
    void Q6() {
        idText.setText("Query.6 il numero di viaggi effettuati per una tratta da un mezzo");
        ViaggioDao viaggioDao = new ViaggioDao(emf.createEntityManager());
        idTextArea.setVisible(true);
        idTextArea.setText("Viaggi effettuati per questa tratta: " + viaggioDao.contaViaggiByMezzoAndTratta("2fabefbe-867e-473c-8559-2db52b79f5e6", 7));

    }

    @FXML
    void Q7() {
        idText.setText("Query.7 tempo effettivo di una tratta da un mezzo");
        ViaggioDao viaggioDao = new ViaggioDao(emf.createEntityManager());
        idTextArea.setVisible(true);
        List<LocalTime> tempiEffettivi = viaggioDao.tempoEffettivoTrattaBYMezzo("2fabefbe-867e-473c-8559-2db52b79f5e6", 7);
        StringBuilder stringBuilder = new StringBuilder();
        tempiEffettivi.forEach(tempo -> stringBuilder.append("Tempo impiegato: ").append(tempo).append("\n"));
        String tempiEffettiviString = stringBuilder.toString();
        idTextArea.setText(tempiEffettiviString);
         }

    @FXML
    void Q8() {
        TrattaDao trattaDao = new TrattaDao(emf.createEntityManager());
        System.out.println("Query.8");
        trattaDao.tempoEffettivoTratta();
     //   emf.close();
    }

    @FXML
    void Q9() {
        idText.setText("Query.9 il numero di biglietti vidimati su un particolare mezzo");
        TitoloDiViaggioDao titoloDiViaggioDao = new TitoloDiViaggioDao(emf.createEntityManager());
        long risultato = titoloDiViaggioDao.calcoloBigliettiVidimati("df79d1a2-832a-4767-8722-0e78fc356bfe");
        idTextArea.setVisible(true);
        idTextArea.setText(risultato == 0 ? "Nessun risultato trovato" : String.valueOf(risultato));
        //  emf.close();
    }

    @FXML
    void Q10() {
        idText.setText("Query.10");
        TitoloDiViaggioDao titoloDiViaggioDao = new TitoloDiViaggioDao(emf.createEntityManager());
        idTextArea.setVisible(true);
        idTextArea.setText(String.valueOf(titoloDiViaggioDao.getTotaleBigliettiVidimatiPerData(LocalDate.of(2024, 5, 7), LocalDate.of(2024, 5, 9))));
        //emf.close();
    }


}
