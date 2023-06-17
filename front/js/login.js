function verificar_login() {
    const formulario = document.getElementById("form_login");
    const url = 'http://localhost:8080/pessoas/login';
    const dados_form = new FormData(formulario);
    var obj_form = {};
   
    const xhr = new XMLHttpRequest();

    xhr.open('POST', url, true);
    xhr.setRequestHeader("Content-Type", "application/json");

    for (const [k, v] of dados_form.entries()) {
        console.log(k + " >>> " + v)
        obj_form[k] = v;
    }
   
    xhr.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            console.log("ok");
        }
    }

    const jsonBody = JSON.stringify(obj_form);
    console.log(jsonBody)
    xhr.send(jsonBody);
}