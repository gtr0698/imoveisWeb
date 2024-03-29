$(document).ready(function () {
    create_cards();
    modal_agendar();
    carregar_funcionarios();
});

function create_cards() {
    var xhr = new XMLHttpRequest();
    const url = 'http://localhost:8080/imoveis/listar'
    xhr.open('GET', url, true);
    var card_content = document.getElementById('cards_imoveis');

    xhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var res = this.responseText;
            var data = JSON.parse(res);

            for (const [k, v] of Object.entries(data)) {

                var div = document.createElement('div');
                var div_content = '';
                div_content += '<div class="content">';
                div_content += '<div class="header" id="' + v["id"] + '"style="color: white; font-size:20px">';
                div_content += '<img class="ui fluid rounded image" style="min-height: 200px; max-height: 200px;" src="../front/img/imovel-' + v["id"] + '.png"></div>';

                div_content += '<div class="meta-content" style="display: inline-block; color: white;">';
                div_content += '<div style="font-size: 1.5rem; margin-bottom: 4px; margin-top: 4px;">R$ ' + v["preco"] + '</div></div>';

                div_content += '<table class="ui inverted table"  id="dados_imovel' + v["id"] + '">';

                div_content += '<tr>';
                div_content += '<td>Zona ' + v["tipoImovel"] + '</td>';
                div_content += '</tr>';
                div_content += '<tr>';
                div_content += '<td>' + v["largura"] + 'm x ' + v["comprimento"] + 'm</td>';
                div_content += '</tr></table>';
                div_content += '</div>';
                div_content += '<div class="extra content">';
                div_content += '<div class="ui green button" onclick="abrir_modal()">Agendar</div>';
                div_content += '</div>';

                div.innerHTML += div_content;
                div.setAttribute('class', 'ui inverted card');
                card_content.appendChild(div);


            }
        }
    }

    xhr.send();
};

function carregar_funcionarios() {
    var xhr = new XMLHttpRequest();
    const url = 'http://localhost:8080/funcionarios/listar';
    xhr.open('GET', url, true);
    var funcionarios = document.getElementById('funcionario_dropdown');

    xhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var res = this.responseText;
            var data = JSON.parse(res);

            for (let item in data) {
                var div = document.createElement('option');
                div.innerHTML = data[item]['nome'];
                div.setAttribute('id', data[item]['id']);
                funcionarios.appendChild(div);
            }
        }
    }

    xhr.send();

}

function modal_agendar() {
    const agendamento = document.getElementById('form_agendamento');
    agendamento.innerHTML = '';

    var div = document.createElement('div');
    var div_content = '';
    // DEIXAR ESTES DISPLAY AQUI                                                vvvvv
    div_content += '<input type="number" value="" name="id" id="id" style="display:none;">'
    div_content += '<div class="six wide field">';
    div_content += '<label for="dataAgendamento">Data</label>';
    div_content += '<input type="date" id="dataAgendamento" name="dataAgendamento">';
    div_content += '</div>';
    div_content += '<div class="six wide field">';
    div_content += '<label for="funcionario">Corretor</label>';
    div_content += '<select name="funcionario" id="funcionario_dropdown">';
    div_content += '<option value=""></option>';
   div_content += '</select></div></div>';


    div.setAttribute('class', 'ui inverted segment');
    div.innerHTML = div_content;
    agendamento.append(div);

}

function abrir_modal() {
    $('#mdl_agendamento').modal('show');
}

function agendar() {
    const dataAgendamento = document.getElementById("dataAgendamento");

    var obj_form = {};
    var url = "http://localhost:8080/agendamentos/criar"
    const xhr = new XMLHttpRequest();
    xhr.open('POST', url, true);
    xhr.setRequestHeader("Content-Type", "application/json");

    const selectElement = document.getElementById('funcionario_dropdown');
    const selectedIndex = selectElement.selectedIndex;
    const selectedOption = selectElement.options[selectedIndex].id;

    obj_form['pessoa'] = [{'idPessoa': localStorage.getItem('id_pessoa')}]
    obj_form['funcionario'] = [{'idFuncionario': selectedOption}]
    obj_form['dataAgendamento'] = dataAgendamento.value;

    obj_form = JSON.stringify(obj_form);
    console.log(obj_form)
    xhr.send(obj_form);

}