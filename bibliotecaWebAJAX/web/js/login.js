/*
 * login.js
 * Hugo Rivera Vazquez | 205540
 * Luis Gonzalo Cervantes Rivera | 228549
 * Jesus Ricardo Ortega Sanchez 216703
 * 
 * Contiene todo lo necesario para el login
 */

var xhttp;
var username;
var password;

function signIn() {
    username = document.getElementById("username").value;
    password = document.getElementById("password").value;

    user = createJWT(username, password);
    console.log(user);

    xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let usuario = this.responseText
            console.log(usuario);

            if (usuario.startsWith("{")) {
                window.location.replace("biblioteca.html");
            }
        }
    }

    if (xhttp) {
        xhttp.open("GET", "SignIn?user=" + user, true)
        xhttp.send(null)
    }
}

function createJWT(username, password) {
    const header = {
        alg: "HS256",
        typ: "JWT"
    };

    const payload = {
        username: username,
        password: password,
    };

    const encodedHeader = btoa(JSON.stringify(header)).replace(/\+/g, '-').replace(/\//g, '_').replace(/=+$/, '');
    const encodedPayload = btoa(JSON.stringify(payload)).replace(/\+/g, '-').replace(/\//g, '_').replace(/=+$/, '');

    const encodedToken = `${encodedHeader}.${encodedPayload}`;

    const secretKey = "biblioteca_web_secret_master_key";
    
    const signature = btoa(unescape(encodeURIComponent(encodedToken + secretKey))).replace(/\+/g, '-').replace(/\//g, '_').replace(/=+$/, '');

    return `${encodedToken}.${signature}`;
}