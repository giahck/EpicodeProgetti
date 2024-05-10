# Progetto build-week Java: gestione di un’azienda di trasporto pubblico

L'obiettivo consiste nel realizzare un progetto Java che includa il modello, le classi DAO e tutte le infrastrutture necessarie alla gestione della persistenza tramite JPA. Occorre inoltre realizzare il database PostgreSQL a supporto del modello applicativo.

In questo progetto la traccia fornita era la seguente:

_"Il sistema deve permettere l'emissione dei biglietti, sia da distributori automatici che da rivenditori autorizzati, oltre che l'emissione di abbonamenti settimanali e mensili di tipo nominativo ad utenti dotati di tessera. La tessera ha validità annuale e deve essere rinnovata alla scadenza. Ogni biglietto ed abbonamento è identificato da un codice univoco. Deve essere possibile tenere traccia del numero di biglietti e/o abbonamenti emessi in un dato periodo di tempo in totale e per punto di emissione. I distributori automatici possono essere attivi o fuori servizio.
Occorre inoltre permettere la verifica rapida della validità di un abbonamento in base al numero di tessera dell'utente controllato. Il sistema deve inoltre prevedere la gestione del parco mezzi. I mezzi possono essere tram o autobus ed avere una certa capienza in base al tipo di mezzo. Ogni mezzo può essere in servizio o in manutenzione ed occorre tenere traccia dei periodi di servizio o manutenzione di ogni mezzo. Quando un biglietto viene vidimato su un mezzo, esso deve essere annullato e deve essere possibile acquisire il numero di biglietti vidimati su un particolare mezzo o in totale in un periodo di tempo.
Ogni mezzo in servizio può essere assegnato ad una tratta, che è caratterizzata da una zona di partenza, un capolinea ed un tempo medio di percorrenza. 
Occorre tenere traccia del numero di volte che un mezzo percorre una tratta e del tempo effettivo di percorrenza di esse."_

## Tecnologie utilizzate
- Java
- PostgreSQL

 ## Struttura
Di seguito le classi create con le relative relazioni:

### Classe Autobus:

- Estende la classe Mezzo.
- Ha un campo per la targa.
- Ha un riferimento a una lista di oggetti Biglietto tramite l'associazione @OneToMany.
  
### Classe Autobus:
- Estende la classe Mezzo.
- Ha un campo per la targa.
- Ha un riferimento a una lista di oggetti Biglietto tramite l'associazione @OneToMany.

### Classe Tram:
- Estende la classe Mezzo.
- Ha un campo per la targa.
- Ha un riferimento a una lista di oggetti Biglietto tramite l'associazione @OneToMany.

### Classe Mezzo:
- Rappresenta un mezzo di trasporto generico.
- Contiene un ID (idMezzo) generato automaticamente.
- Ha un riferimento a una lista di oggetti Biglietto tramite l'associazione @OneToMany.

### Classe Biglietto:
- Estende la classe TitoloDiViaggio.
- Contiene un campo booleano (statusValidita) che indica lo stato di validità del biglietto.
- Ha un campo per la timbratura, che rappresenta la data di utilizzo del biglietto.
- Contiene un riferimento a un oggetto Utente tramite l'associazione @ManyToOne.
- Contiene un riferimento a un oggetto Mezzo tramite l'associazione @ManyToOne.

### Classe Abbonamento:
- Estende la classe TitoloDiViaggio.
- Contiene un campo enumerato (emissione) che indica il tipo di emissione dell'abbonamento.
- Ha un campo per la scadenza.
- Contiene un riferimento a un oggetto Tessera tramite l'associazione @OneToOne.

### Classe Rivenditore:
- Rappresenta un rivenditore generico.
- Contiene un ID (idRivenditore) generato automaticamente.
- Ha un riferimento a una lista di oggetti TitoloDiViaggio tramite l'associazione @OneToMany.

### Classe RivenditoreAutorizzato:
- Estende la classe Rivenditore.
- Contiene informazioni specifiche come il nomeRivenditore e l'indirizzo.

### Classe DistributoreAutomatico:
- Estende la classe Rivenditore.
- Contiene informazioni specifiche come lo statoServizio e il nomeDistributore.

### Classe Utente:
- Rappresenta un utente del sistema.
- Contiene campi per il nome, il cognome e la dataNascita.
- Contiene un riferimento a un oggetto Tessera tramite l'associazione @OneToOne.
- Contiene un riferimento a una lista di oggetti Biglietto tramite l'associazione @OneToMany.

### Classe Tessera:
- Rappresenta una tessera associata a un utente.
- Contiene un campo per la emissione della tessera.
- Ha un campo per la scadenza della tessera, che viene calcolata automaticamente.
- Contiene un riferimento a un oggetto Utente tramite l'associazione @OneToOne.
- Contiene un riferimento a un oggetto Abbonamento tramite l'associazione @OneToOne.

### Classe StatusMezzo:
- Contiene un ID (id_status_mezzo) generato automaticamente.
- Ha un riferimento a un oggetto Mezzo tramite l'associazione @ManyToOne.
- Contiene un campo enumerato (status) che rappresenta lo stato del mezzo.
- Contiene campi per la data di inizio (dataInizio) e fine (dataFine) dello stato.

### Classe Tratta:
- Contiene un ID (id_tratta) generato automaticamente.
- Ha campi per il nome di partenza (nomePartenza) e arrivo (nomeArrivo) della tratta.
- Ha un riferimento a una lista di oggetti Viaggio tramite l'associazione @OneToMany.
- Ha un campo per il tempo medio della tratta (tempoMedioTratta) di tipo LocalTime.

### Classe Viaggio:
- Contiene un ID (id_viaggio) generato automaticamente.
- Ha un campo per il tempo effettivo (tempoEffettivo) del viaggio di tipo LocalTime.
- Ha un campo per il nome della tratta (nomeTratta).
- Ha un riferimento a una lista di oggetti Mezzo tramite l'associazione @ManyToMany.
- Ha un riferimento a un oggetto Tratta tramite l'associazione @ManyToOne.

### Classe CountRivenditoriViaggi:
- Ha un campo per un oggetto Rivenditore (rivenditore).
- Ha un campo per il numero di titoli di viaggio (numTitoli) di tipo Long.

### Classe StatoAbbonamento:
- Ha campi per il nome (nomeTessera) e il cognome (cognomeTessera) della tessera.
- Ha un campo per la data di scadenza dell'abbonamento (scadenzaAbbonamento) di tipo LocalDate.


Di seguito una lista delle varie query utilizzate e testate nel Main:

### QUERY.1:
Questa query restituisce il numero totale di biglietti emessi da ciascun rivenditore autorizzato e distributore automatico in un determinato periodo di tempo.
### QUERY.2:
Questa query recupera lo stato di validità degli abbonamenti associati a una determinata tessera utente.
### Query.3:
Questa query restituisce la data di scadenza della tessera di un utente, che rappresenta la scadenza degli abbonamenti associati a quella tessera.
### Query.4:
Questa query restituisce tutti i periodi di servizio o manutenzione di ciascun mezzo con uno stato specifico.
### Query.5:
Questa query restituisce i periodi di servizio o manutenzione di uno specifico mezzo con uno stato specifico.
### Query.6:
Questa query calcola il numero di viaggi effettuati per una determinata tratta da un particolare mezzo.
### Query.7:
Questa query restituisce il tempo effettivo impiegato per ciascuna tratta da un particolare mezzo.
### Query.8:
Questa query calcola la media dei tempi effettivi di tutte le tratte.
### Query.9:
Questa query calcola il numero di biglietti vidimati su un particolare mezzo.
### Query.10:
Questa query calcola il numero totale di biglietti vidimati su tutti i mezzi in un determinato intervallo di tempo.






