
let x = document.getElementById("modeSwitch");
// let extraOptions = document.getElementById("extraOptions");
// let y = document.getElementsByClassName("fa-solid fa-lightbulb")[0];
let dropdownMenuBtn = document.getElementById("dropdownMenuButton");

console.log('testing')

window.onload = function(){
    if(localStorage.getItem('theme') == 'dark'){
        document.body.setAttribute('data-bs-theme', 'dark')
        dropdownMenuBtn.setAttribute('class', 'btn btn-light btn-sm position-absolute bottom-0 end-0')
        // y.setAttribute('class', 'fa-regular fa-lightbulb')
        // x.setAttribute('class', 'btn btn-light btn-lg position-absolute bottom-0 end-0')
        // extraOptions.setAttribute('class', 'btn btn-dark btn-sm position-absolute bottom-0 start-0')
        console.log('Theme switched to dark')
    }
    else{
        document.body.setAttribute('data-bs-theme', 'light')
        dropdownMenuBtn.setAttribute('class', 'btn btn-dark btn-sm position-absolute bottom-0 end-0')
        // y.setAttribute('class', 'fa-solid fa-lightbulb')
        // x.setAttribute('class', 'btn btn-dark btn-lg position-absolute bottom-0 end-0')
        // extraOptions.setAttribute('class', 'btn btn-light btn-sm position-absolute bottom-0 start-0')
        console.log('Theme switched to light')
    }
}

x.addEventListener('click', function() {
    if(document.body.getAttribute('data-bs-theme') === 'light'){
    // if(localStorage.getItem('theme') == 'light'){
        document.body.setAttribute('data-bs-theme', 'dark')
        dropdownMenuBtn.setAttribute('class', 'btn btn-light btn-sm position-absolute bottom-0 end-0')
        // y.setAttribute('class', 'fa-regular fa-lightbulb')
        // x.setAttribute('class', 'btn btn-light btn-lg position-absolute bottom-0 end-0')
        // extraOptions.setAttribute('class', 'btn btn-dark btn-sm position-absolute bottom-0 start-0')
        localStorage.setItem('theme', 'dark')
        console.log('Theme switched to dark')
    }
    else{
        document.body.setAttribute('data-bs-theme', 'light')
        dropdownMenuBtn.setAttribute('class', 'btn btn-dark btn-sm position-absolute bottom-0 end-0')
        // y.setAttribute('class', 'fa-solid fa-lightbulb')
        // x.setAttribute('class', 'btn btn-dark btn-lg position-absolute bottom-0 end-0')
        // extraOptions.setAttribute('class', 'btn btn-light btn-sm position-absolute bottom-0 start-0')
        console.log('Theme switched to light')
        localStorage.setItem('theme', 'light')
    }
})
