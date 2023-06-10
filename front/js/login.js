function verificar_login() {
    const formulario = document.getElementById("form_login");
    const url = 'https://localhost:8080/cadastros/pessoa-juridica';
    const dados_form = new FormData(formulario);
    var obj_form = {};
    const xhr = new XMLHttpRequest();
    const nome = document.getElementById('nome');
    xhr.open('POST', url, true);
    
    for (const [k, v] of dados_form.entries()) {
        obj_form[k] = v;
    }
    obj_form['papel'] = "CLIENTE";
    console.log(obj_form);
    xhr.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            console.log("ok");
        }
    }

    obj_form = JSON.stringify(obj_form);
    xhr.send(obj_form);
}