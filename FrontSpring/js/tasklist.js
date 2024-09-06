const base_url = 'http://localhost:8080'

const  authenticate = () => { // função anonima
    const email = $("#e").val(); //$ para não precisar escrever jquery.
    const password = $("#p").val();
    const body = `{"email": "${email}", "password":"${password}"}`;

    $.ajax({
        type: 'POST',
        url: `${base_url}/auth/login`,
        contentType: 'application/json',
        dataType:'json',
        data:body,
        success: (res) => {
            console.log(res)
            localStorage.setItem("token", res.token);
            window.location.href = "main.html";
        }
            
    }); //Criando obj com json
}

const check = () =>{
    $.ajax({
        type: 'POST',
        url: `${base_url}/auth/check`,
        headers: {"token": localStorage.getItem("token")},
        success: (res) => { console.log(res)},
        error: (res) => {
            console.log(res);
            window.location.href = "index.html";
        }
    });
}

const signout = () =>{
    $.ajax({
        type: 'POST',
        url: `${base_url}/auth/signout`,
        headers: {"token": localStorage.getItem("token")},
        success: (res) => { console.log(res)},
        error: (res) => {console.log(res)}
    });
}

document.onreadystatechange = () => {
    $("#b").click(authenticate);
    setInterval(check, 1000);
}