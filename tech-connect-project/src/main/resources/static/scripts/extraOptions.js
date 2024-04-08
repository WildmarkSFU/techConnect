
let lightMode = document.getElementById("modeSwitchLight");
let darkMode = document.getElementById("modeSwitchDark");
let aboutProject = document.getElementById("aboutProject");
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

lightMode.addEventListener('click', function() {
    if (localStorage.getItem('theme') == 'light') {
        return;
    }
    document.body.setAttribute('data-bs-theme', 'light')
    dropdownMenuBtn.setAttribute('class', 'btn btn-dark btn-sm position-absolute bottom-0 end-0')
    console.log('Theme switched to light')
    localStorage.setItem('theme', 'light')
});

darkMode.addEventListener('click', function() {
    if (localStorage.getItem('theme') == 'dark') {
        return;
    }
    document.body.setAttribute('data-bs-theme', 'dark')
    dropdownMenuBtn.setAttribute('class', 'btn btn-light btn-sm position-absolute bottom-0 end-0')
    localStorage.setItem('theme', 'dark')
    console.log('Theme switched to dark')
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
