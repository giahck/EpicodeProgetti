window.addEventListener('load', function() {
  history.pushState(null, document.title, location.href);
});

window.addEventListener('popstate', function(event) {
  history.pushState(null, document.title, location.href);
});

const form = document.getElementsByTagName('form');
const btnAdd = document.getElementById('btnAdd');
const checkbox = document.getElementById('checkbox');

window.addEventListener("load", init());

function init() {
  btnStart();
}


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



window.addEventListener('load', function() {
  history.pushState(null, document.title, location.href);
});

window.addEventListener('popstate', function(event) {
  history.pushState(null, document.title, location.href);
});


