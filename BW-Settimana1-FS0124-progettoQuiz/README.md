
# BW GRUPPO 7 - SETTIMANA 4 - FS0124

## Welcome Page

Nella welcome page troviamo le istruzioni per poter sostenere l'esame. All'interno troviamo una lista non ordinata in cui si evincono le condizioni ed è stato impostato un form con una checkbox che ha come requisito essenziale la spunta per poter procedere alla pagina successiva (ovvero la Benchmark Page), usando questa funzione:

```
function btnStart() {
  btnAdd.disabled = true;
  checkbox.addEventListener('click', function () {
    if (checkbox.checked) {
      btnAdd.disabled = false;
      btnAdd.classList.add('btnEnabled');
    } else {
      btnAdd.disabled = true;
      btnAdd.classList.remove('btnEnabled');
    }

  })
}
```

## Benchmark Page
Benvenuto nella Benchmark Page! Questa pagina ti permette di rispondere a una serie di domande su argomenti legati alla scienza e alla tecnologia. Di seguito troverai una breve guida su come utilizzare la pagina.

### Funzionalità
- Rispondi alle domande: Verranno visualizzate una serie di domande su argomenti come computer, programmazione e tecnologia. Seleziona la risposta che ritieni corretta tra quelle fornite.

- Timer: Ogni domanda deve essere risposta entro un limite di tempo di 30 secondi. Se non rispondi entro questo limite, la domanda successiva verrà automaticamente visualizzata.

```
function updateChart(chart, counter) {
  if (counter <= 0) {
    // Modifica la condizione per controllare se il contatore è <= 0
    clearInterval(timerInterval); // Ferma l'intervallo quando il timer raggiunge 0 secondi
    counter = 30; // Reimposta il contatore a 0
    timerInterval = setInterval(() => {
      // Ricomincia l'intervallo
      counter = counter - 1;
      updateChart(myChart, counter);
    }, intervalDuration);
  }
  chart.data.datasets[0].data = [maxTime - counter, counter];
  chart.update();
  const counterHtml = document.getElementById("counterHtml");
  counterHtml.innerHTML = counter;
}
```

- Risultati: Una volta risposte tutte le domande, verrai reindirizzato a una pagina dei risultati dove potrai vedere quanti quesiti hai risposto correttamente e quanti non hai risposto correttamente.


```
 localStorage.setItem("risposte", arr);
  window.location.href = "../../results.html";
  ```

### Utilizzo
1. Avvio della Pagina: Carica la pagina nel tuo browser preferito.

2. Rispondere alle Domande: Leggi attentamente ogni domanda e seleziona la risposta che ritieni corretta tra quelle fornite.

3. Timer: Assicurati di rispondere entro il limite di tempo di 30 secondi per ogni domanda.

4. Visualizzazione dei Risultati: Una volta risposte tutte le domande o scaduto il tempo, verrai automaticamente reindirizzato alla pagina dei risultati.

## Results Page
La Results Page è progettato per visualizzare i risultati di un test a risposta multipla in un grafico a ciambella. Utilizza la libreria Chart.js per creare il grafico e localStorage per memorizzare i risultati del test.

### Funzionalità principali
- Calcola le percentuali di risposte corrette e sbagliate.

```
function calcolaPercentuali(risultati) {
    if (Array.isArray(risultati)) {
        const conteggioTrue = risultati.filter(val => val === true).length;
        const conteggioFalse = risultati.length - conteggioTrue;
       ...
        percentualeTrue = (conteggioTrue / risultati.length) * 100;
        const percentualeFalse = (conteggioFalse / risultati.length) * 100;
       ...
        percentualeTrueHTML.innerHTML = percentualeTrue.toFixed(1) + '%';
        percentualeFalseHTML.innerHTML = percentualeFalse.toFixed(1) + '%';

        data.datasets[0].data = [percentualeFalse, percentualeTrue];
    }
}

```

- Visualizza i risultati in un grafico a ciambella.
- Fornisce un feedback personalizzato a seconda del punteggio ottenuto.

```
if (percentualeTrue >= 60) {
            ctx.fillText(testoCorretto, xCoor, yCoor);
        } else {
            ctx.fillText(testoSbagliato, xCoor, yCoor);
        }
```

## Feedback Page

- Nella feedback Page troviamo un interfaccia che fa scegliere una valutazione del test selezionando un numero di stelle con due funzionalità ciascuna facente riferimento ad un evento specifico, il passaggio sopra le stelle ed il click su di esse. 


```
 star.addEventListener('mouseover', () => {
        highlightStars(index);
    });

    star.addEventListener('click', () => {
        selectStar(index);
    });
```   


Inoltre c'è un input di testo con requisito essenziale l'inserimento di almeno un carattere


```
<input type="text" id="comment" placeholder="Write your comment here" required>
``` 


 per poter attivare il pulsante gestito esattamente come nella pagina di Welcome solo con il cambio della condizione del **IF**:


```
if (text.value.trim() !== '')
```


Questo button porta alla pagina di ringraziamento è verificato invio


## Tecnologie Utilizzate

La Pagina di Quiz è stata sviluppata utilizzando le seguenti tecnologie:

- HTML: Per la struttura e il contenuto della pagina.
- CSS: Per lo stile e il layout della pagina.
- JavaScript: Per la logica di funzionamento del quiz, inclusi il timer e la gestione delle risposte.
- Chart.js: Per la visualizzazione del timer e del grafico a torta. Il codice è stato testato utilizzando Chart.js versione (https://cdn.jsdelivr.net/npm/chart.js)

## Autori
- Clarissa Piovesan
- Gianluca Giacchetta
- Vito Dagnello
- Stefano Fortezza
- Mishel Cakalli
- Lucian Andrei Oprica