function onClick() {
    if (localStorage.getItem("name") == null) {
        login(document.getElementById("login-btn"));
    } else {
        logout(document.getElementById("login-btn"));
    }
}

function onLoad() {

    var loginBtn = document.getElementById("login-btn");

    if (localStorage.getItem("name") != null) {
        var diff = new Date().getTime() - localStorage.getItem("time");
        if (diff > 2 * 60 * 1000) {
            logout(document.getElementById("login-btn"));
        } else {
            loginBtn.value = "Logout";
        }
    }

}

function login(btn) {
    var person = prompt("What's ur name?");

    localStorage.setItem("name", person);
    localStorage.setItem("time", new Date().getTime());

    btn.value = "Logout";
}

function logout(btn) {
    alert("Bye bye " + localStorage.getItem("name"));

    localStorage.removeItem("name");
    localStorage.removeItem("time");

    btn.value = "Login";
}
