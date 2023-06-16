function salvar_imovel() {
    const formulario = document.getElementById("form_cadastro_imovel");
    const dados_form = new FormData(formulario);
    var obj_form = {};

    for (const [k, v] of dados_form.entries()) {
        if (k == "proprietario") {
            obj_form[k] = { 'id': v };
            continue;
        }
        if (k == 'id') {
            id_imovel = v;
        }
        obj_form[k] = v;
    }
    console.log(obj_form)
    // Caso seja um cadastro novo
    if (id_imovel == "") {
        criar_imovel(obj_form);
    }
    else {
        editar_imovel(obj_form);
    }
}

function criar_imovel(obj_form) {
    const url = 'http://localhost:8080/imoveis/criar';
    const xhr = new XMLHttpRequest();

    xhr.open('POST', url, true);
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            alert("Imóvel Criado");
        }
    }

    obj_form = JSON.stringify(obj_form);
    xhr.send(obj_form);
}

function editar_imovel(obj_form) {
    const url = 'http://localhost:8080/imoveis/atualizar/' + id_imovel;
    const xhr = new XMLHttpRequest();

    xhr.open('PUT', url, true);
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            alert("Imóvel Editado");
        }
    }

    obj_form = JSON.stringify(obj_form);
    xhr.send(obj_form);
}

function modal_remover(){
    $('#mdl_deletar_imovel').modal('show');
}

function limitarTamanho() {
    var campo = document.getElementById("deletar_id");
    var valor = campo.value;
    
    if (valor.length > 4) {
      campo.value = valor.slice(1, 4); // Limita o valor a 4 caracteres
    } else if (valor.length === 0) {
      campo.value = ""; // Limpa o campo se estiver vazio
    }
  }

function deletar_imovel() {
    var id_imovel = document.getElementById("deletar_id").value;
    

    console.log(id_imovel);
    const url = 'http://localhost:8080/imoveis/remover/' + id_imovel;
    const xhr = new XMLHttpRequest();

    xhr.open('DELETE', url, true);
    xhr.setRequestHeader("Content-Type", "application/json");

    // xhr.onreadystatechange = function () {
    //     if (this.readyState == 4 && this.status == 200) {
    //         alert("Imóvel Deletado");
    //     }
    // }

    // obj_form = JSON.stringify(obj_form);
    // xhr.send(obj_form);
}
