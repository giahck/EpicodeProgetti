import DAO.*;
  

import ResultDto.CountRivenditoriViaggi;
import entities.Rivenditori.DistributoreAutomatico;
import entities.Rivenditori.Rivenditore;
import entities.Rivenditori.RivenditoreAutorizzato;
import entities.StatusMezzo.StatusMezzo;
import entities.TitoliViaggi.Abbonamento;
import entities.TitoliViaggi.Biglietto;
import entities.UtenteETessera.Tessera;
import entities.UtenteETessera.Utente;
import entities.ViaggioTratta.Tratta;
import entities.ViaggioTratta.Viaggio;
import entities.mezzi.Autobus;
import entities.mezzi.Mezzo;
import entities.mezzi.Tram;
import enums.EmissioneAbbonamento;
import enums.EnumStatus;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import static javafx.application.Application.launch;




public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fileFxml.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 900, 600);
        stage.setTitle("BW1");
        stage.setScene(scene);
        stage.show();
        /*EntityManagerFactory emf = Persistence.createEntityManagerFactory("bw1");
        EntityManager em = emf.createEntityManager();

        UtenteDao utenteDao = new UtenteDao(em);
        TesseraDao tesseraDao = new TesseraDao(em);
        RivenditoriDao rivenditoriDao = new RivenditoriDao(em);
        TitoloDiViaggioDao titoloDiViaggioDao = new TitoloDiViaggioDao(em);
        TrattaDao trattaDao = new TrattaDao(em);
        ViaggioDao viaggioDao = new ViaggioDao(em);
        MezziDao mezzoDao = new MezziDao(em);
        StatusMezziDao statusMezzoDao = new StatusMezziDao(em);*/
        //-----------------------------------------------------------------


  /*      //DISTRIBUTORI AUTOMATICI-------------------------------------------------------

        DistributoreAutomatico distributoreAutomatico1 = new DistributoreAutomatico();
        DistributoreAutomatico distributoreAutomatico2 = new DistributoreAutomatico();
        DistributoreAutomatico distributoreAutomatico3 = new DistributoreAutomatico();

        distributoreAutomatico1.setStatoServizio(true);
        distributoreAutomatico1.setNomeDistributore("DA1");
        distributoreAutomatico2.setStatoServizio(false);
        distributoreAutomatico2.setNomeDistributore("DA2");
        distributoreAutomatico3.setStatoServizio(true);
        distributoreAutomatico3.setNomeDistributore("DA3");

        rivenditoriDao.save(distributoreAutomatico1);
        rivenditoriDao.save(distributoreAutomatico2);
        rivenditoriDao.save(distributoreAutomatico3);

        //RIVENDITORI AUTORIZZATI--------------------------------------------------------
        RivenditoreAutorizzato rivenditoreAutorizzato1 = new RivenditoreAutorizzato();
        RivenditoreAutorizzato rivenditoreAutorizzato2 = new RivenditoreAutorizzato();
        RivenditoreAutorizzato rivenditoreAutorizzato3 = new RivenditoreAutorizzato();

        rivenditoreAutorizzato1.setNomeRivenditore("Bar da Mario");
        rivenditoreAutorizzato1.setIndirizzo("Via Giuseppe Meazza");

        rivenditoreAutorizzato2.setNomeRivenditore("Giornalaio");
        rivenditoreAutorizzato2.setIndirizzo("Piazza di Spagna");

        rivenditoreAutorizzato3.setNomeRivenditore("Pasticceria Luna");
        rivenditoreAutorizzato3.setIndirizzo("Via dei Baracchini 50");

        rivenditoriDao.save(rivenditoreAutorizzato1);
        rivenditoriDao.save(rivenditoreAutorizzato2);
        rivenditoriDao.save(rivenditoreAutorizzato3);






        //UTENTE 1---------------- CON TESSERA------------
        //Tessera
        Tessera t1 = new Tessera();
        t1.setEmissione(LocalDate.of(2022, 10, 10));
        tesseraDao.save(t1);

        //utente
        Utente u1 = new Utente();
        u1.setNome("Mario");
        u1.setCognome("Rossi");
        u1.setDataNascita(LocalDate.of(1990, 10, 10));
        // assegno la tessera uno all'utente
        t1.setUtente(u1);
        utenteDao.save(u1);

        Abbonamento abbonamento1 = new Abbonamento();
        abbonamento1.setRivenditore(rivenditoreAutorizzato2);
        abbonamento1.setTessera(t1);
        abbonamento1.setEmissione(EmissioneAbbonamento.MENSILE);
        abbonamento1.setEmissioneTitoloViaggio(LocalDate.of(2024, 5, 8));
        titoloDiViaggioDao.save(abbonamento1);



        //UTENTE 2---------------- CON TESSERA------------

        Tessera t2 = new Tessera();
        t2.setEmissione(LocalDate.of(2023, 5, 12));
        tesseraDao.save(t2);
        // salviamo la tessera 2
        Utente u2 = new Utente();
        u2.setNome("Roberta");
        u2.setCognome("Verdi");
        u2.setDataNascita(LocalDate.of(2006, 1, 10));
        // assegno la tessera due all'utente
        t2.setUtente(u2);
        utenteDao.save(u2);


        Abbonamento abbonamento2 = new Abbonamento();
        abbonamento2.setRivenditore(rivenditoreAutorizzato3);
        abbonamento2.setTessera(t2);
        abbonamento2.setEmissione(EmissioneAbbonamento.SETTIMANALE);
        abbonamento2.setEmissioneTitoloViaggio(LocalDate.of(2024, 5, 8));
        titoloDiViaggioDao.save(abbonamento2);



        //UTENTE 3---------------- SENZA TESSERA----------------

        Utente u3 = new Utente();
        u3.setNome("Bruno");
        u3.setCognome("Conti");
        u3.setDataNascita(LocalDate.of(1996, 1, 10));
        utenteDao.save(u3);

        //BIGLIETTI UTENTE1---------------------------------------------------------
        //b1
        Biglietto biglietto1a = new Biglietto(); // BIGLIETTO DELL'UTENTE SENZA TESSERA U3

        biglietto1a.setRivenditore(rivenditoreAutorizzato1);//SETTO IL RIVENDITORE DEL BIGLIETTO
        biglietto1a.setUtente(u3); // SETTO L'UTENTE CHE USA IL BIGLIETTO
        biglietto1a.setEmissioneTitoloViaggio(LocalDate.of(2024, 5, 8)); //SETTO GIORNI DI EMISSIONE
        //b2
        Biglietto biglietto1b = new Biglietto(); // BIGLIETTO DELL'UTENTE SENZA TESSERA U3

        biglietto1b.setRivenditore(rivenditoreAutorizzato1);//SETTO IL RIVENDITORE DEL BIGLIETTO
        biglietto1b.setUtente(u3); // SETTO L'UTENTE CHE USA IL BIGLIETTO
        biglietto1b.setEmissioneTitoloViaggio(LocalDate.of(2024, 5, 8)); //SETTO GIORNI DI EMISSIONE


        //UTENTE4------------------SENZA TESSERA-------------------------------------
        Utente u4 = new Utente();
        u4.setNome("Brandi");
        u4.setCognome("Love");
        u4.setDataNascita(LocalDate.of(2003, 1, 11));
        utenteDao.save(u4);

        //BIGLIETTI UTENTE2-----------------------------------------------------
        Biglietto biglietto2 = new Biglietto(); // BIGLIETTO DELL'UTENTE SENZA TESSERA U4

        biglietto2.setRivenditore(rivenditoreAutorizzato2); //SETTO IL RIVENDITORE DEL BIGLIETTO
        biglietto2.setUtente(u4);// SETTO L'UTENTE CHE USA IL BIGLIETTO
        biglietto2.setEmissioneTitoloViaggio(LocalDate.of(2024, 5, 7)); //SETTO GIORNI DI EMISSIONE



        //TRATTE-------------------------------------------


        //tratta andata
        Tratta tratta1 = new Tratta();
        tratta1.setNomePartenza("Piazza di Spagna");
        tratta1.setNomeArrivo(("Stadio Olimpico"));

        //Tratta ritorno
        Tratta tratta2 = new Tratta();
        tratta2.setNomePartenza("Roma Termini");
        tratta2.setNomeArrivo(("Sulmona"));
        //Tratta ritorno
        Tratta tratta3 = new Tratta();
        tratta3.setNomePartenza("Bologna");
        tratta3.setNomeArrivo(("Bari"));
        //Tratta ritorno
        Tratta tratta4 = new Tratta();
        tratta4.setNomePartenza("Roma Tiburtina");
        tratta4.setNomeArrivo(("Orbetello"));
        trattaDao.saveAll(List.of(tratta1, tratta2, tratta3, tratta4));

        //MEZZI------------------------------------------------------------

        Autobus autobus1 = new Autobus();
        autobus1.setTarga("BY230JA");

        Tram tram1 = new Tram();
        tram1.setTarga("CG540KA");

        Autobus autobus2 = new Autobus();
        autobus2.setTarga("AY230JA");

        Tram tram2 = new Tram();
        tram2.setTarga("ZG540KA");

        Autobus autobus3 = new Autobus();
        autobus3.setTarga("KY230JA");

        Tram tram3 = new Tram();
        tram3.setTarga("XG540KA");

        Autobus autobus4 = new Autobus();
        autobus4.setTarga("LY230JA");

        Tram tram4 = new Tram();
        tram4.setTarga("VG540KA");


        Autobus autobus5 = new Autobus();
        autobus5.setTarga("QY230JA");

        Tram tram5 = new Tram();
        tram5.setTarga("MG540KA");
        mezzoDao.saveAll(List.of(autobus1, autobus2, autobus3, autobus4, autobus5, tram1, tram2, tram3, tram4, tram5));

        //STATUS MEZZI che cambiano nel tempo
        StatusMezzo statusMezzo1 = new StatusMezzo();// autobus1 in servizio dal 10/01/2024 al 30/02/2024
        statusMezzo1.setMezzo(autobus1);
        statusMezzo1.setStatus(EnumStatus.IN_SERVIZIO);
        statusMezzo1.setDataInizio(LocalDate.of(2023, 1, 10));
        statusMezzo1.setDataFine(LocalDate.of(2023, 2, 28));
        statusMezzoDao.save(statusMezzo1);

        StatusMezzo statusMezzo1a = new StatusMezzo(); // autobus1 in manutenzione
        statusMezzo1a.setMezzo(autobus1);
        statusMezzo1a.setStatus(EnumStatus.IN_MANUTENZIONE);
        statusMezzo1a.setDataInizio(LocalDate.of(2023, 3, 1));
        statusMezzo1a.setDataFine(LocalDate.of(2023, 12, 30));
        statusMezzoDao.save(statusMezzo1a);

        StatusMezzo statusMezzo1b = new StatusMezzo();// autobus1 in servizio
        statusMezzo1b.setMezzo(autobus1);
        statusMezzo1b.setStatus(EnumStatus.IN_SERVIZIO);
        statusMezzo1b.setDataInizio(LocalDate.of(2024, 1, 15));
        statusMezzoDao.save(statusMezzo1b);

        StatusMezzo statusMezzo2 = new StatusMezzo();
        statusMezzo2.setMezzo(autobus2);
        statusMezzo2.setStatus(EnumStatus.IN_MANUTENZIONE);
        statusMezzo2.setDataInizio(LocalDate.of(2023, 12, 10));
        statusMezzoDao.save(statusMezzo2);

        StatusMezzo statusMezzo3 = new StatusMezzo();
        statusMezzo3.setMezzo(autobus3);
        statusMezzo3.setStatus(EnumStatus.IN_SERVIZIO);
        statusMezzo3.setDataInizio(LocalDate.of(2023, 12, 10));
        statusMezzoDao.save(statusMezzo3);

        StatusMezzo statusMezzo4 = new StatusMezzo();
        statusMezzo4.setMezzo(autobus4);
        statusMezzo4.setStatus(EnumStatus.IN_MANUTENZIONE);
        statusMezzo4.setDataInizio(LocalDate.of(2023, 12, 10));
        statusMezzoDao.save(statusMezzo4);

        StatusMezzo statusMezzo5 = new StatusMezzo();
        statusMezzo5.setMezzo(autobus5);
        statusMezzo5.setStatus(EnumStatus.IN_SERVIZIO);
        statusMezzo5.setDataInizio(LocalDate.of(2023, 12, 10));
        statusMezzoDao.save(statusMezzo5);

        StatusMezzo statusMezzo6 = new StatusMezzo();
        statusMezzo6.setMezzo(tram1);
        statusMezzo6.setStatus(EnumStatus.IN_MANUTENZIONE);
        statusMezzo6.setDataInizio(LocalDate.of(2023, 12, 10));
        statusMezzoDao.save(statusMezzo6);


        StatusMezzo statusMezzo7 = new StatusMezzo();
        statusMezzo7.setMezzo(tram2);
        statusMezzo7.setStatus(EnumStatus.IN_MANUTENZIONE);
        statusMezzo7.setDataInizio(LocalDate.of(1995, 5, 10));
        statusMezzo7.setDataFine(LocalDate.of(2005, 6, 10));
        statusMezzoDao.save(statusMezzo7);
        StatusMezzo statusMezzo7a = new StatusMezzo();
        statusMezzo7a.setMezzo(tram2);
        statusMezzo7a.setStatus(EnumStatus.IN_SERVIZIO);
        statusMezzo7a.setDataInizio(LocalDate.of(2005, 6, 11));
        statusMezzoDao.save(statusMezzo7a);


        StatusMezzo statusMezzo8 = new StatusMezzo();
        statusMezzo8.setMezzo(tram3);
        statusMezzo8.setStatus(EnumStatus.IN_MANUTENZIONE);
        statusMezzo8.setDataInizio(LocalDate.of(2019, 12, 10));
        statusMezzo7.setDataFine(LocalDate.of(2020, 1, 20));
        statusMezzoDao.save(statusMezzo8);


        StatusMezzo statusMezzo9 = new StatusMezzo();
        statusMezzo9.setMezzo(tram4);
        statusMezzo9.setStatus(EnumStatus.IN_SERVIZIO);
        statusMezzo9.setDataInizio(LocalDate.of(2020, 10, 10));
        statusMezzoDao.save(statusMezzo9);


        StatusMezzo statusMezzo10 = new StatusMezzo();
        statusMezzo10.setMezzo(tram5);
        statusMezzo10.setStatus(EnumStatus.IN_MANUTENZIONE);
        statusMezzo10.setDataInizio(LocalDate.of(2023, 12, 10));
        statusMezzoDao.save(statusMezzo10);





        //Utilizzo biglietti
        biglietto1a.setMezzo(autobus1);
        biglietto1a.setstatusValidita(true);
        biglietto1a.setTimbratura(LocalDate.of(2024, 5, 8));

        biglietto1b.setMezzo(autobus1);
        biglietto1b.setstatusValidita(true);
        biglietto1b.setTimbratura(LocalDate.of(2024, 5, 9));

        biglietto2.setMezzo(tram3);
        biglietto2.setstatusValidita(true);
        biglietto2.setTimbratura(LocalDate.of(2024, 5, 7));

        titoloDiViaggioDao.saveAll(List.of(biglietto1a,biglietto1b,biglietto2));

        //RELAZIONE MEZZI VIAGGIO
//ANDATA
        Viaggio viaggio1 = new Viaggio();
        viaggio1.setTempoEffettivo(LocalTime.of(0, 25,3));
        viaggio1.setNomeTratta("Andata");
        viaggio1.setMezzi(List.of(autobus1));

        //RITORNO
        Viaggio viaggio2 = new Viaggio();
        viaggio2.setTempoEffettivo(LocalTime.of(0, 43,14));
        viaggio2.setNomeTratta("Ritorno");
        viaggio2.setMezzi(List.of(autobus1));

        Viaggio viaggio3 = new Viaggio();
        viaggio3.setTempoEffettivo(LocalTime.of(0, 28,45));
        viaggio3.setNomeTratta("Notturno");
        viaggio3.setMezzi(List.of(autobus1,autobus5,autobus4));


        Viaggio viaggio4 = new Viaggio();
        viaggio4.setTempoEffettivo(LocalTime.of(1, 30,24));
        viaggio4.setNomeTratta("Notturno");
        viaggio4.setMezzi(List.of(tram4,tram5));


        Viaggio viaggio5 = new Viaggio();
        viaggio5.setTempoEffettivo(LocalTime.of(4, 30,54));
        viaggio5.setNomeTratta("Andata");
        viaggio5.setMezzi(List.of(tram1,tram3));

        Viaggio viaggio6 = new Viaggio();
        viaggio6.setTempoEffettivo(LocalTime.of(0, 37,27));
        viaggio6.setNomeTratta("Notturno");
        viaggio6.setMezzi(List.of(autobus2,tram3));

        Viaggio viaggio7 = new Viaggio();
        viaggio7.setTempoEffettivo(LocalTime.of(0, 42,12));
        viaggio7.setNomeTratta("Notturno");
        viaggio7.setMezzi(List.of(autobus5,autobus3));

        Viaggio viaggio8 = new Viaggio();
        viaggio8.setTempoEffettivo(LocalTime.of(3, 26,40));
        viaggio8.setNomeTratta("Notturno");
        viaggio8.setMezzi(List.of(tram2));


        viaggioDao.saveAll(List.of(viaggio1,viaggio2,viaggio3,viaggio4,viaggio5,viaggio6,viaggio7,viaggio8));

    //TRATTE TO VIAGGIO
        viaggio1.setTratta(tratta1);
        viaggio2.setTratta(tratta1);
        viaggio3.setTratta(tratta4);
        viaggio4.setTratta(tratta2);
        viaggio5.setTratta(tratta3);
        viaggio6.setTratta(tratta4);
        viaggio7.setTratta(tratta1);
        viaggio8.setTratta(tratta3);
        viaggioDao.saveAll(List.of(viaggio1,viaggio2,viaggio3,viaggio4,viaggio5,viaggio6,viaggio7,viaggio8));
        */







//       ---QUERY 1----
        // Deve essere possibile tenere traccia del numero di biglietti e/o abbonamenti emessi in un dato periodo di tempo in totale e per punto di emissione
     /*   System.out.println("QUERY.1");//restituscie nella classe il nome del venditore e quanti ne ha venduti nel tempo che gli abbiamo passato
        List<CountRivenditoriViaggi> results = titoloDiViaggioDao.getTotaleBiglietti(LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 12, 31));

        results.forEach(result -> {
            if (result.getRivenditore() instanceof RivenditoreAutorizzato) {
                RivenditoreAutorizzato autorizzato = (RivenditoreAutorizzato) result.getRivenditore();
                System.out.println("Il rivenditore autorizzato '" + autorizzato.getNomeRivenditore() + "'" + " ha emesso " + result.getNumTitoli() + " titolo/i di viaggio.");
            } else if (result.getRivenditore() instanceof DistributoreAutomatico) {
                DistributoreAutomatico distributore = (DistributoreAutomatico) result.getRivenditore();
                System.out.println("Il distributore '" + distributore.getNomeDistributore() + "'" + " ha emesso " + result.getNumTitoli() + " titolo/i di viaggio.");
            } else {
                System.out.println("Nessun risultato trovato.");
            }
        });*/



//        Deve essere possibile tenere traccia della scadenza degli abbonamenti dato il numero della tessera di un utente

/*        System.out.println("QUERY.2");
        titoloDiViaggioDao.getStatoAbbonamento3("755cdf66-343b-4c09-8950-01264e3d65d0");
        System.out.println();
 */

      /*  System.out.println();
        System.out.println("QUERY.3");

        // ---QUERY 3----
        // Deve essere possibile tenere traccia della scadenza degli abbonamenti dato il numero della tessera di un utente
        tesseraDao.getValiditaTessera("61916754-b5b9-4df8-b888-39d2d8fa0ec3");*/


      /*  System.out.println();
        System.out.println("Query.4");

        //--query 4  Occorre tenere traccia dei periodi di servizio o manutenzione di ogni mezzo dato uno status.
        statusMezzoDao.getStatusMezzi(EnumStatus.IN_MANUTENZIONE);*/


        /*System.out.println();
        System.out.println("Query.5");
        //--query 5 Occorre tenere traccia dei periodi di servizio o manutenzione di uno specifico mezzo dato uno status.
        statusMezzoDao.getStatusMezzo("b5d41d08-ba35-4052-9a7a-05e03ff88591",EnumStatus.IN_MANUTENZIONE);*/


        /*System.out.println("Query.6");
        //query6: numero viaggi per tratta dato un mezzo
        System.out.println("Viaggi effettuati per questa tratta: "+viaggioDao.contaViaggiByMezzoAndTratta("b5d41d08-ba35-4052-9a7a-05e03ff88591", 7));
*/

       /* System.out.println();
        //query: tempo effettivo di ogni tratta dato un mezzo e il numero della tratta
        System.out.println("Query.7");
        viaggioDao.tempoEffettivoTrattaBYMezzo("b5d41d08-ba35-4052-9a7a-05e03ff88591", 7).forEach(e -> System.out.println("Tempo impiegato: "+e));
*/


        //calcola la media dei tempi effettivi di ogni tratta,non passiamo nessun parametro perchè la query lavora direttamnte con la tabella tratta
        /*System.out.println();
        System.out.println("Query.8");
        trattaDao.tempoEffettivoTratta();*/

        // query: deve essere possibile acquisire il numero di biglietti vidimati su un particolare mezzo
     /*   System.out.println();
        System.out.println("Query.9");
        System.out.println(titoloDiViaggioDao.calcoloBigliettiVidimati("df79d1a2-832a-4767-8722-0e78fc356bfe"));
*/

        /*System.out.println();
        System.out.println("Query.10");
        System.out.println(titoloDiViaggioDao.getTotaleBigliettiVidimatiPerData(LocalDate.of(2024,5,7),LocalDate.of(2024,5,9)));
*/
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
