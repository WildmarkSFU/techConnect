
let x = document.getElementById("modeSwitch");
let y = document.getElementsByClassName("fa-solid fa-lightbulb")[0];

console.log('testing')

window.onload = function(){
    if(localStorage.getItem('theme') == 'dark'){
        document.body.setAttribute('data-bs-theme', 'dark')
        y.setAttribute('class', 'fa-regular fa-lightbulb')
        x.setAttribute('class', 'btn btn-light btn-lg position-absolute bottom-0 end-0')
        console.log('Theme switched to dark')
    }
    else{
        document.body.setAttribute('data-bs-theme', 'light')
        y.setAttribute('class', 'fa-solid fa-lightbulb')
        x.setAttribute('class', 'btn btn-dark btn-lg position-absolute bottom-0 end-0')
        console.log('Theme switched to light')
    }
}

x.addEventListener('click', function() {
    if(document.body.getAttribute('data-bs-theme') === 'light'){
    // if(localStorage.getItem('theme') == 'light'){
        document.body.setAttribute('data-bs-theme', 'dark')
        y.setAttribute('class', 'fa-regular fa-lightbulb')
        x.setAttribute('class', 'btn btn-light btn-lg position-absolute bottom-0 end-0')
        localStorage.setItem('theme', 'dark')
        console.log('Theme switched to dark')
    }
    else{
        document.body.setAttribute('data-bs-theme', 'light')
        y.setAttribute('class', 'fa-solid fa-lightbulb')
        x.setAttribute('class', 'btn btn-dark btn-lg position-absolute bottom-0 end-0')
        console.log('Theme switched to light')
        localStorage.setItem('theme', 'light')
    }
})
