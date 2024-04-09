
let lightMode = document.getElementById("modeSwitchLight");
let darkMode = document.getElementById("modeSwitchDark");
let blueMode = document.getElementById("modeSwitchBlue");
let redMode = document.getElementById("modeSwitchRed");
let redOrangeMode = document.getElementById("modeSwitchRedOrange");
let purpleYellowMode = document.getElementById("modeSwitchPurpleYellow");
let aboutProject = document.getElementById("aboutProject");
let navBar = document.getElementsByTagName('nav')[0];
// let extraOptions = document.getElementById("extraOptions");
// let y = document.getElementsByClassName("fa-solid fa-lightbulb")[0];
let dropdownMenuBtn = document.getElementById("dropdownMenuButton");

console.log('testing')

window.onload = function(){
    if(localStorage.getItem('theme') == 'dark'){
        document.body.setAttribute('data-bs-theme', 'dark')
        dropdownMenuBtn.setAttribute('class', 'btn btn-light btn-sm position-fixed bottom-0 end-0')
        document.body.removeAttribute('style');
        dropdownMenuBtn.style.backgroundColor = '';

        if (navBar){
            navBar.removeAttribute('style');
        }
        // y.setAttribute('class', 'fa-regular fa-lightbulb')
        // x.setAttribute('class', 'btn btn-light btn-lg position-fixed bottom-0 end-0')
        // extraOptions.setAttribute('class', 'btn btn-dark btn-sm position-fixed bottom-0 start-0')
        console.log('Theme switched to dark')
    }
    else if(localStorage.getItem('theme') == 'blue'){
        if (navBar){
            navBar.removeAttribute('style');
        }
        dropdownMenuBtn.style.backgroundColor = '';
        document.body.setAttribute('data-bs-theme', 'light')
        dropdownMenuBtn.setAttribute('class', 'btn btn-danger btn-sm position-fixed bottom-0 end-0')
        document.body.setAttribute('style', 'background-color: #0000EE; color: #DC3545;');
        console.log('Theme switched to blue')
    }
    else if (localStorage.getItem('theme') == 'red') {
        if (navBar){
            navBar.removeAttribute('style');
        }
        dropdownMenuBtn.style.backgroundColor = '';
        document.body.setAttribute('data-bs-theme', 'light')
        dropdownMenuBtn.setAttribute('class', 'btn btn-info btn-sm position-fixed bottom-0 end-0')
        document.body.setAttribute('style', 'background-color: #DC3545; color: #0000EE;');
        console.log('Theme switched to red')
    }
    else if (localStorage.getItem('theme') == 'purpleYellow') {
        if (navBar){
            navBar.removeAttribute('style');
        }
        dropdownMenuBtn.style.backgroundColor = '';
        document.body.setAttribute('data-bs-theme', 'light')
        dropdownMenuBtn.setAttribute('class', 'btn btn-dark btn-sm position-fixed bottom-0 end-0')
        dropdownMenuBtn.style.backgroundColor = '#800080';
        document.body.setAttribute('style', 'background-image: linear-gradient(to bottom right, #800080, yellow); background-repeat: no-repeat; background-size: cover; background-position: center; color: black; font-weight: bold; min-height: 100vh;');
        if (navBar){
            navBar.setAttribute('style', 'background-color: #800080 !important; font-weight: bold;');
        }
        console.log('Theme switched to purpleYellow')
    }
    else if (localStorage.getItem('theme') == 'redOrange') {
        if (navBar){
            navBar.removeAttribute('style');
        }
        dropdownMenuBtn.style.backgroundColor = '';
        document.body.setAttribute('data-bs-theme', 'light')
        dropdownMenuBtn.setAttribute('class', 'btn btn-warning btn-sm position-fixed bottom-0 end-0')
        document.body.setAttribute('style', 'background-image: linear-gradient(to bottom right, orange, red); background-repeat: no-repeat; background-size: cover; background-position: center; color: black; font-weight: bold; color: #0000EE; min-height: 100vh;');
        console.log('Theme switched to redOrange')
        if (navBar){
            navBar.setAttribute('style', 'background-color: orange !important; font-weight: bold;');
        }
    }
    else{
        if (navBar){
            navBar.removeAttribute('style');
        }
        dropdownMenuBtn.style.backgroundColor = '';
        document.body.setAttribute('data-bs-theme', 'light')
        document.body.removeAttribute('style');
        dropdownMenuBtn.setAttribute('class', 'btn btn-dark btn-sm position-fixed bottom-0 end-0')
        // y.setAttribute('class', 'fa-solid fa-lightbulb')
        // x.setAttribute('class', 'btn btn-dark btn-lg position-fixed bottom-0 end-0')
        // extraOptions.setAttribute('class', 'btn btn-light btn-sm position-fixed bottom-0 start-0')
        console.log('Theme switched to light')
    }
}

lightMode.addEventListener('click', function() {
    if (navBar){
        navBar.removeAttribute('style');
    }
    dropdownMenuBtn.style.backgroundColor = '';
    if (localStorage.getItem('theme') == 'light') {
        return;
    }
    document.body.setAttribute('data-bs-theme', 'light')
    document.body.removeAttribute('style');
    dropdownMenuBtn.setAttribute('class', 'btn btn-dark btn-sm position-fixed bottom-0 end-0')
    console.log('Theme switched to light')
    localStorage.setItem('theme', 'light')
});

darkMode.addEventListener('click', function() {
    if (navBar){
        navBar.removeAttribute('style');
    }
    dropdownMenuBtn.style.backgroundColor = '';
    if (localStorage.getItem('theme') == 'dark') {
        return;
    }
    document.body.setAttribute('data-bs-theme', 'dark')
    document.body.removeAttribute('style');
    dropdownMenuBtn.setAttribute('class', 'btn btn-light btn-sm position-fixed bottom-0 end-0')
    localStorage.setItem('theme', 'dark')
    console.log('Theme switched to dark')
});

blueMode.addEventListener('click', function() {
    if (navBar){
        navBar.removeAttribute('style');
    }
    dropdownMenuBtn.style.backgroundColor = '';
    if (localStorage.getItem('theme') == 'blue') {
        return;
    }
    document.body.setAttribute('data-bs-theme', 'light')
    document.body.setAttribute('style', 'background-color: #0000EE; color: #DC3545;');
    dropdownMenuBtn.setAttribute('class', 'btn btn-danger btn-sm position-fixed bottom-0 end-0')
    localStorage.setItem('theme', 'blue')
    console.log('Theme switched to blue')
});

redMode.addEventListener('click', function() {
    if (navBar){
        navBar.removeAttribute('style');
    }
    dropdownMenuBtn.style.backgroundColor = '';
    if (localStorage.getItem('theme') == 'red') {
        return;
    }
    document.body.setAttribute('data-bs-theme', 'light')
    dropdownMenuBtn.setAttribute('class', 'btn btn-info btn-sm position-fixed bottom-0 end-0')
    document.body.setAttribute('style', 'background-color: #DC3545; color: #0000EE;');
    localStorage.setItem('theme', 'red')
    console.log('Theme switched to red')
});

purpleYellowMode.addEventListener('click', function() {
    if (localStorage.getItem('theme') == 'purpleYellow') {
        return;
    }
    if (navBar){
        navBar.removeAttribute('style');
    }
    dropdownMenuBtn.style.backgroundColor = '';
    document.body.setAttribute('data-bs-theme', 'light')
    dropdownMenuBtn.setAttribute('class', 'btn btn-dark btn-sm position-fixed bottom-0 end-0')
    dropdownMenuBtn.style.backgroundColor = '#800080';
    document.body.setAttribute('style', 'background-image: linear-gradient(to bottom right, #800080, yellow); background-repeat: no-repeat; background-size: cover; background-position: center; color: black; font-weight: bold; min-height: 100vh;');
    if (navBar){
        navBar.setAttribute('style', 'background-color: #800080 !important; font-weight: bold;');
    }
    localStorage.setItem('theme', 'purpleYellow')
    console.log('Theme switched to purpleYellow')
});

redOrangeMode.addEventListener('click', function() {
    if (navBar){
        navBar.removeAttribute('style');
    }
    dropdownMenuBtn.style.backgroundColor = '';
    if (localStorage.getItem('theme') == 'redOrange') {
        return;
    }
    document.body.setAttribute('data-bs-theme', 'light')
    dropdownMenuBtn.setAttribute('class', 'btn btn-warning btn-sm position-fixed bottom-0 end-0')
    document.body.setAttribute('style', 'background-image: linear-gradient(to bottom right, orange, red); background-repeat: no-repeat; background-size: cover; background-position: center; color: black; font-weight: bold; color: #0000EE; min-height: 100vh;');
    localStorage.setItem('theme', 'redOrange')
        console.log('Theme switched to redOrange')
        if (navBar){
            navBar.setAttribute('style', 'background-color: orange !important; font-weight: bold;');
        }
    console.log('Theme switched to redOrange')
});

aboutProject.addEventListener('click', function() {
    console.log("Working...");
    let aboutText = document.createElement('button');
    aboutText.setAttribute('style', 'text-align: center; position: absolute; top: 10px; left: 0; right: 0; margin-left: auto; margin-right: auto; border-radius: 20px; width: 300px;');
    // aboutText.setAttribute('class', 'reveal');
    if (localStorage.getItem('theme') == 'dark') {
        aboutText.setAttribute('class', 'btn btn-light btn-sm reveal');
    }
    else {
        aboutText.setAttribute('class', 'btn btn-dark btn-sm reveal');
    }
    aboutText.innerHTML = `<i class = "fa-solid fa-circle-info"></i> Created by Team WLDMK Spring 2024`;
    let appendToSite = document.getElementById('siteStats');
    appendToSite.appendChild(aboutText);

    setTimeout(function(){
        appendToSite.removeChild(aboutText);
    }, 3000);
});
