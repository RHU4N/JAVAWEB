const base_url = 'http://localhost:8080'

const authenticate = () => {
    const email = $("#e").val();
    const password = $("#p").val();
    const body = `{"email": "${email}", "password": "${password}"}`;

    $.ajax({
        type: 'POST',
        url: `${base_url}/auth/login`,
        contentType: 'application/json',
        dataType: 'json',
        data: body,
        success: (res) => { 
            console.log(res); 
            localStorage.setItem("token", res.token);
            window.location.href = "main.html";
        }
    });
}

const check = () => {
    $.ajax({
        type: 'POST',
        url: `${base_url}/auth/check`,
        headers: {"token": localStorage.getItem("token")},
        success: (res) => { console.log(res) },
        error: (res) => { 
            console.log(res);
            window.location.href = "index.html";
        }
    });
}

const signout = () => {
    $.ajax({
        type: 'POST',
        url: `${base_url}/auth/signout`,
        headers: {"token": localStorage.getItem("token")},
        success: (res) => { console.log(res); check(); },
        error: (res) => { console.log(res); check(); }
    });
}

const signup = (fn) => {
    const email = $("#sup-e").val();
    const password = $("#sup-p").val();
    const body = `{"email": "${email}", "password": "${password}"}`;

    $.ajax({
        type: 'POST',
        url: `${base_url}/auth/signup`,
        contentType: 'application/json',
        dataType: 'json',
        data: body,
        success: (res) => { 
            console.log(res);
        }
    });
}

export {authenticate, check, signup, signout}