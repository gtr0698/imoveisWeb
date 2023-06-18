$(document).ready(function () {
    localStorage.removeItem('id_pessoa');
});

function verificar_login() {
    const formulario = document.getElementById("form_login");
    const url = 'http://localhost:8080/pessoas/login';
    const dados_form = new FormData(formulario);
    var obj_form = {};
    const idUser = document.getElementById('id_pessoa');
    const xhr = new XMLHttpRequest();

    xhr.open('POST', url, true);
    xhr.setRequestHeader("Content-Type", "application/json");

    for (const [k, v] of dados_form.entries()) {
        obj_form[k] = v;
    }
   
    xhr.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var res = this.responseText;
            var data = JSON.parse(res);
            localStorage.setItem('id_pessoa', data['idPessoa']);
            window.location.href = 'index.html';
        }
        else if(this.readyState == 4){
            alert("Credenciais incorretas!")
        }
    }
    xhr.send(JSON.stringify(obj_form));
}

