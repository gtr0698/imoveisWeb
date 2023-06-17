var id_imovel = '';

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
    obj_form = JSON.stringify(obj_form);
    // Caso seja um cadastro novo
    if (id_imovel == "") {
        criar_imovel(obj_form);
    }
    else {
        editar_imovel(obj_form);
    }
}

function limpar_imovel() {
    const formulario = document.getElementById("form_cadastro_imovel");
    const dados_form = new FormData(formulario);

    for (const [k, v] of dados_form.entries()) {
        document.getElementById(k).value = "";
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

    xhr.send(obj_form);
}

function modal_remover() {
    $('#mdl_deletar_imovel').modal('show');
}

function modal_editar() {
    $('#mdl_editar_imovel').modal('show');
}

function buscar_imovel() {
    var id_imovel = document.getElementById("editar_id");

    const url = 'http://localhost:8080/imoveis/buscar/' + id_imovel.value;
    const xhr = new XMLHttpRequest();
    xhr.open('GET', url, true);
    xhr.setRequestHeader("Content-Type", "application/json");

    if (id_imovel.value.length < 1) {
        alert("O campo deve ter pelo menos 1 caractere!");
    } else {
        xhr.open('DELETE', url, true);
        xhr.setRequestHeader("Content-Type", "application/json");

        xhr.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                var response = JSON.parse(xhr.responseText)["content"];
                
                document.getElementById('id').value = response['id'];
                document.getElementById('matriculaImovel').value = response['matriculaImovel'];
                document.getElementById('proprietario').value = response['proprietario']['id'];
                document.getElementById('tipoImovel').value = response['tipoImovel'];
                document.getElementById('largura').value = response['largura'];
                document.getElementById('comprimento').value = response['comprimento'];
                document.getElementById('preco').value = response['comprimento'];
            }
        }

        xhr.send();
    }
}

function deletar_imovel() {
    var id_imovel = document.getElementById("deletar_id");

    const url = 'http://localhost:8080/imoveis/remover/' + id_imovel.value;
    const xhr = new XMLHttpRequest();
    xhr.open('DELETE', url, true);
    xhr.setRequestHeader("Content-Type", "application/json");

    if (id_imovel.value.length < 1) {
        alert("O campo deve ter pelo menos 1 caractere!");
    } else {
        xhr.open('DELETE', url, true);
        xhr.setRequestHeader("Content-Type", "application/json");

        xhr.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                alert("Imóvel Deletado");
            }
        }

        xhr.send();
    }
}


