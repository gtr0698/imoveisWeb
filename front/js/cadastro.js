function get_url(){
    var url = window.location.href;
    var parts = url.split('/');
    var directoryPath = "";

    for (var i = 0; i < parts.length; i++) {
        if (parts[i] === "imoveis") {
            directoryPath = parts.slice(0, i+1).join('/');
        }
    }
    return directoryPath;

}
$(document).ready(function(){
    
});

function abrir_cadastro() {
    $('.coupled.modal')
        .modal({
            allowMultiple: false
        })
        ;
    $('#mdl_cadastro_juridica')
        .modal('attach events', '#mdl_abrir_cadastro #btn_juridica')
        ;
    // open second modal on first modal buttons
    $('#mdl_cadastro_fisica')
        .modal('attach events', '#mdl_abrir_cadastro #btn_fisica')
        ;
    // show first immediately
    $('#mdl_abrir_cadastro')
        .modal('show')
        ;
}

function gerar_cadastro_fisica(){
    const cadastro_fisica = document.getElementById('form_pessoa_fisica');
    const cadastro_juridica = document.getElementById('form_pessoa_juridica');
    cadastro_fisica.innerHTML = '';
    cadastro_juridica.innerHTML = '';
    var div = document.createElement('div');
    var div_content = '';
    div_content += '<div class="ui inverted form">';
    div_content += '<div class="two fields">';
    div_content += '<div class="ten wide field">';
    div_content += '<label for="nome">Nome</label>';
    div_content += '<input type="text" placeholder="Nome Completo" name="nome" id="nome">'
    div_content += '</div>';    
    div_content += '<div class="five wild field">';
    div_content += '<label for="cpf">CPF</label>';
    div_content += '<input type="number" placeholder="CPF" name="cpf" id="cpf">'
    div_content += '</div></div>';
    div_content += '<div class="two fields">';
    div_content += '<div class="ten wide field">';
    div_content += '<label for="email">E-mail</label>';
    div_content += '<input type="email" inputmode="email" placeholder="exemplo@mail.com" id="email" name="email">'
    div_content += '</div>';
    div_content += '<div class="six wide field">';
    div_content += '<label for="senha">Senha</label>';
    div_content += '<input type="password" placeholder="Senha" id="senha" name="senha">'
    div_content += '</div></div>';
    div_content += '<div class="two fields">';
    div_content += '<div class="field">';
    div_content += '<label for="genero">Gênero</label>';
    div_content += '<select id="genero" name="genero">';
    div_content += '<option value=""></option>';
    div_content += '<option value="MASCULINO">Masculino</option>';
    div_content += '<option value="FEMININO">Feminino</option>';
    div_content += '<option value="OUTRO">Outro</option>';
    div_content += '</select></div>';
    div_content += '<div class="three wild field">';
    div_content += '<label for="dataNascimento">Data de Nascimento</label>';
    div_content += '<input type="date" id="dataNascimento" name="dataNascimento">';
    div_content += '</div></div></div>';
    div.setAttribute('class', 'ui inverted segment');
    div.innerHTML = div_content;
    cadastro_fisica.append(div);
}

function gerar_cadastro_juridica(){
    const cadastro_fisica = document.getElementById('form_pessoa_fisica');
    const cadastro_juridica = document.getElementById('form_pessoa_juridica');
    cadastro_fisica.innerHTML = '';
    cadastro_juridica.innerHTML = '';
    var div = document.createElement('div');
    var div_content = '';
    div_content += '<div class="ui inverted form">';
    div_content += '<div class="three fields">';
    div_content += '<div class="five wide field">';
    div_content += '<label for="razaoSocial">Razão Social</label>';
    div_content += '<input type="text" placeholder="Razão Social" name="razaoSocial" id="razaoSocial">'
    div_content += '</div>';    
    div_content += '<div class="five wild field">';
    div_content += '<label for="nomeFantasia">Nome Fantasia</label>';
    div_content += '<input type="text" placeholder="Nome Fantasia" name="nomeFantasia" id="nomeFantasia">'
    div_content += '</div>';
    div_content += '<div class="five wild field">';
    div_content += '<label for="cnpj">CNPJ</label>';
    div_content += '<input type="number" placeholder="CNPJ" name="cnpj" id="cnpj">'
    div_content += '</div></div>';
    div_content += '<div class="two fields">';
    div_content += '<div class="ten wide field">';
    div_content += '<label for="email">E-mail</label>';
    div_content += '<input type="email" inputmode="email" placeholder="exemplo@mail.com" id="email" name="email">'
    div_content += '</div>';
    div_content += '<div class="seven wide field">';
    div_content += '<label for="senha">Senha</label>';
    div_content += '<input type="password" placeholder="Senha" id="senha" name="senha">'
    div_content += '</div></div>';
    div.setAttribute('class', 'ui inverted segment');
    div.innerHTML = div_content;
    cadastro_juridica.append(div);
}

function cadastro_fisica() {
    const formulario = document.getElementById("form_pessoa_fisica");
    const url = 'http://localhost:8080/cadastros/pessoa-fisica';
    const dados_form = new FormData(formulario);
    var obj_form = {};
    const xhr = new XMLHttpRequest();
    const nome = document.getElementById('nome');
    xhr.open('POST', url, true);
    xhr.setRequestHeader("Content-Type", "application/json");

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


function cadastro_juridica() {
    const formulario = document.getElementById("form_pessoa_juridica");
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
