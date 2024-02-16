const stars = document.querySelectorAll(".star");
const btnFeedback = document.getElementById("btnFeedback");
const text = document.getElementById("comment");

window.addEventListener("load", function() {
    history.pushState(null, document.title, location.href);
  });
  
  window.addEventListener("popstate", function(event) {
    history.pushState(null, document.title, location.href);
  });
  
stars.forEach((star, index) => {
    star.addEventListener("mouseover", () => {
        highlightStars(index);
    });

    star.addEventListener("click", () => {
        selectStar(index);
        console.log(index +1 );
    });
});

function highlightStars(index) {
    stars.forEach((star, i) => {
        if (i <= index) {
            star.classList.add("active");
        } else {
            star.classList.remove("active");
        }
    });
}

function selectStar(index) {
   stars.forEach((star, i) => {
       if (i <= index) {
           star.classList.add("active");
       } else {
           star.classList.remove("active");
       }
   });
}

window.addEventListener("load", init());

function init() {
    btnStart();
}
function btnStart() {
    btnFeedback.disabled = true;

    text.addEventListener("input", () => {
        if (text.value.trim() !== "") {
            btnFeedback.disabled = false;
            btnFeedback.classList.add("btnEnabled");
        } else {
            btnFeedback.disabled = true;
            btnFeedback.classList.remove("btnEnabled");
        }
    });
}

const regexCommento = /^[a-zA-Z\s]+$/;

document.getElementById("containerComment").addEventListener("submit", function(event) {
    event.preventDefault(); // Impedisce l"invio del modulo se il testo non è valido, qundi se non rispetta i reqsuiti!
    let comment = document.getElementById("comment").value;
    // Verifica se il testo contiene solo lettere maiuscole e minuscole, non accettando nessun carattere speciale!
    if (!regexCommento.test(comment)) {
      alert("Inserisci solo caratteri alfabetici (senza spazi o caratteri speciali)!");
      return 
    }
location.href = "final.html";
});