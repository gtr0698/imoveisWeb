function abrir_cadastro() {
  
    // show first immediately
    $('#mdl_cadastro_usuario')
        .modal('show')
        ;
}

function gerar_cadastro(){
    const cadastro_usuario = document.getElementById('form_usuario');
    cadastro_usuario.innerHTML = '';
   
    var div = document.createElement('div');
    var div_content = '';
    // DEIXAR ESTES DISPLAY AQUI                                                vvvvv
    div_content += '<input type="number" value="" name="idPessoa" id="idPessoa" style="display:none;">'
    div_content += '<div class="ui inverted form">';
    div_content += '<div class="two fields">';
    div_content += '<div class="two field">';
    div_content += '<label for="nome">Nome</label>';
    div_content += '<input type="text" placeholder="Nome Completo" name="nome" id="nome">'
    div_content += '</div>';    
    div_content += '<div class="five wild field">';
    div_content += '<label for="numeroDocumento">Documento</label>';
    div_content += '<input type="number" placeholder="CPF ou CNJP" name="numeroDocumento" id="numeroDocumento">'
    div_content += '</div></div>';
    div_content += '<div class="two fields">';
    div_content += '<div class="two field">';
    div_content += '<label for="email">E-mail</label>';
    div_content += '<input type="email" inputmode="email" placeholder="exemplo@mail.com" id="email" name="email">'
    div_content += '</div>';
    // div_content += '<div class="ten wide field">';
    // div_content += '<label for="dataNascimento">Data de Nascimento</label>';
    // div_content += '<input type="date" id="dataNascimento" name="dataNascimento">';
    // div_content += '</div>';
    div_content += '<div class="two field">';
    div_content += '<label for="senha">Senha</label>';
    div_content += '<input type="password" placeholder="Senha" id="senha" name="senha">'
    div_content += '</div></div>';
    div_content += '<div class="two fields">';
    div_content += '<div class="two field right">';
    div_content += '<label for="telefone">Telefone</label>';
    div_content += '<input type="number" id="telefone" name="telefone">';
    div_content += '</div>';
    div_content += '<div class="two field">';
    div_content += '<label for="tipoPessoa">Tipo</label>';
    div_content += '<select id="tipoPessoa" name="tipoPessoa">';
    div_content += '<option value=""></option>';
    div_content += '<option value="PESSOA_FISICA">Pessoa Física</option>';
    div_content += '<option value="PESSOA_JURIDICA">Pessoa Jurídica</option></select>';
    div_content += '</div>';
   
    div.setAttribute('class', 'ui inverted segment');
    div.innerHTML = div_content;
    cadastro_usuario.append(div);
}

function cadastrar_usuario() {
    const formulario = document.getElementById("form_usuario");
    const url = 'http://localhost:8080/pessoas/criar';
    const dados_form = new FormData(formulario);
    var obj_form = {};
    const xhr = new XMLHttpRequest();
    xhr.open('POST', url, true);
    xhr.setRequestHeader("Content-Type", "application/json");

    for (const [k, v] of dados_form.entries()) {
        obj_form[k] = v;
    }

    xhr.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            alert("Usuário Cadastrado!");
        }
    }

    obj_form = JSON.stringify(obj_form);
    xhr.send(obj_form);
}
