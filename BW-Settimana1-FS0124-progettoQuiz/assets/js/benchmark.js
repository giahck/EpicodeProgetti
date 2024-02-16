const questions = [
  {
    category: "Science: Computers",
    type: "multiple",
    difficulty: "easy",
    question: "What does CPU stand for?",
    correct_answer: "Central Processing Unit",
    incorrect_answers: [
      "Central Process Unit",
      "Computer Personal Unit",
      "Central Processor Unit",
    ],
  },
  {
    category: "Science: Computers",
    type: "multiple",
    difficulty: "easy",
    question:
      "In the programming language Java, which of these keywords would you put on a variable to make sure it doesn&#039;t get modified?",
    correct_answer: "Final",
    incorrect_answers: ["Static", "Private", "Public"],
  },
  {
    category: "Science: Computers",
    type: "boolean",
    difficulty: "easy",
    question: "The logo for Snapchat is a Bell.",
    correct_answer: "False",
    incorrect_answers: ["True"],
  },
  {
    category: "Science: Computers",
    type: "boolean",
    difficulty: "easy",
    question:
      "Pointers were not used in the original C programming language; they were added later on in C++.",
    correct_answer: "False",
    incorrect_answers: ["True"],
  },
  {
    category: "Science: Computers",
    type: "multiple",
    difficulty: "easy",
    question:
      "What is the most preferred image format used for logos in the Wikimedia database?",
    correct_answer: ".svg",
    incorrect_answers: [".png", ".jpeg", ".gif"],
  },
  {
    category: "Science: Computers",
    type: "multiple",
    difficulty: "easy",
    question: "In web design, what does CSS stand for?",
    correct_answer: "Cascading Style Sheet",
    incorrect_answers: [
      "Counter Strike: Source",
      "Corrective Style Sheet",
      "Computer Style Sheet",
    ],
  },
  {
    category: "Science: Computers",
    type: "multiple",
    difficulty: "easy",
    question:
      "What is the code name for the mobile operating system Android 7.0?",
    correct_answer: "Nougat",
    incorrect_answers: ["Ice Cream Sandwich", "Jelly Bean", "Marshmallow"],
  },
  {
    category: "Science: Computers",
    type: "multiple",
    difficulty: "easy",
    question: "On Twitter, what is the character limit for a Tweet?",
    correct_answer: "140",
    incorrect_answers: ["120", "160", "100"],
  },
  {
    category: "Science: Computers",
    type: "boolean",
    difficulty: "easy",
    question: "Linux was first created as an alternative to Windows XP.",
    correct_answer: "False",
    incorrect_answers: ["True"],
  },
  {
    category: "Science: Computers",
    type: "multiple",
    difficulty: "easy",
    question:
      "Which programming language shares its name with an island in Indonesia?",
    correct_answer: "Java",
    incorrect_answers: ["Python", "C", "Jakarta"],
  },
];

let timer;
const risposteDate = [];


window.addEventListener('popstate', function(event) {
  history.pushState(null, document.title, location.href);
});



window.addEventListener("load", function inizio() {
  history.pushState(null, document.title, location.href);
  domandaEstratta();
  tempo();
});




function casuale() {
  const ran = Math.floor( Math.random() * (questions.length));
  let j=true;
  for (const key of risposteDate ) {
    if (key.id===ran) {
      j=false;
    }
  }
if (j===false) {
  casuale();
}else if (j===true) {
  risposteDate.push({ id: ran }); 
}
}

function domandaEstratta() {
  casuale();
  let casual=risposteDate[risposteDate.length-1].id;

  const h2Question = document.getElementById("idTextQuestions");
  h2Question.innerHTML = `<h2>${questions[casual].question}</h2>`;

  textBenchmark.innerHTML = "";
  let answer = [];
  answer = questions[casual].incorrect_answers;
  const ran = Math.floor(
    Math.random() *
      (questions[casual].incorrect_answers.length + 1)
  );
  answer.splice(ran, 0, questions[casual].correct_answer);
  answer.forEach((element) => {
    textBenchmark.innerHTML += `<div class='domanda'>
      <input type='radio' value='${element}' name='family' id='${element}'></input>
        <label for='${element}'>${element}</label></div>`;
        document.getElementById('questionIndex').innerText=risposteDate.length;
  });
 
}

function invio(radioButton) {
  risposteDate[risposteDate.length - 1].bool = radioButton;
}

function eventoRadio() {
  let casual=risposteDate[risposteDate.length-1].id;
  const radioButtons = document.querySelectorAll('input[name="family"]');
  for (const radioButton of radioButtons) {
    radioButton.addEventListener("change", function () {
      clearInterval(timer);
      timer = null;

      clearInterval(timerInterval);
      counter = maxTime;
      timerInterval = setInterval(() => {
        counter = counter - 1;
        updateChart(myChart, counter);
      }, intervalDuration);

      if (radioButton.value === questions[casual].correct_answer) {
        invio(true);
      } else {
        invio(false);
      }
      if (questions.length === risposteDate.length) {
        fine();
      } else {
        domandaEstratta();
        
        tempo();
      }
    });
  }
}
function fine() {
  const arr = risposteDate.map((element) => {
    return element.bool;
  });
  localStorage.setItem("risposte", arr);
  window.location.href = "../../results.html";
}
function tempo() {
  eventoRadio();
  timer = setInterval(function () {
  
    if (risposteDate.length < questions.length ) {
      invio(false);
      domandaEstratta();  
    } else {
      invio(false);
      clearInterval(timer);
      timer = null;
      fine();
    }

    eventoRadio();
  }, 30000);
}

// FUNZIONE PER IL TIMER CHE GIRA IN SENSO ANTI ORARIO
let myChart;
let intervalDuration = 1000; // Intervallo di aggiornamento del timer in millisecondi
let maxTime = 30; // Tempo massimo del timer in secondi

function drawPieChart(value, maxValue) {
  const ctx = document.getElementById("countdown").getContext("2d");
  myChart = new Chart(ctx, {
    type: "doughnut",
    data: {
      datasets: [
        {
          data: [value, maxValue],
          backgroundColor: ["#C2128D", "#00ffff"],
        },
      ],
    },
    options: {
      cutout: 40,
      tooltips: {
        enabled: false,
      },
      plugins: {
        datalabels: {
          backgroundColor: function (context) {
            return context.dataset.backgroundColor;
          },
          display: function (context) {
            let dataset = context.dataset;
            let value = dataset.data[context.dataIndex];
            return value > 0;
          },
          color: "white",
        },
      },
    },
  });
}

function updateChart(chart, counter) {
  if (counter <= 0) {
    // Modifica la condizione per controllare se il contatore Ã¨ <= 0
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

let timerInterval;

const init = () => {
  drawPieChart(0, maxTime);

  let counter = maxTime; // Inizializza il contatore a maxTime invece di 0
  timerInterval = setInterval(() => {
    counter = counter - 1; // Decrementa il contatore invece di incrementarlo

    updateChart(myChart, counter);
  }, intervalDuration);
};

init();