$(document).ready(function () {
    create_cards();
});
function create_cards() {
    var xhr = new XMLHttpRequest();
    const url = 'http://localhost:8080/cadastros/imoveis'
    xhr.open('GET', url, true);
    var card_content = document.getElementById('cards_imoveis');

    xhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var res = this.responseText;
            var data = JSON.parse(res);

            for (const [k, v] of Object.entries(data)) {
                // Para cada chave (k), valor (v) do JSON coletado na resposta da API
                if (k == 'content') {
                    // V == dados dos imóveis
                    for (const [atrib, val] of Object.entries(v)) {
                        var div = document.createElement('div');
                        var div_content = '';
                        div_content += '<div class="content">';
                        div_content += '<div class="header" id="' + val["id"] + '"style="color: white; font-size:20px">';
                        div_content += '<img class="ui fluid rounded image" style="min-height: 200px;" src="../front/img/imovel-' + val["id"] + '.png"></div>'
                        
                        div_content += '<div class="meta-content" style="display: inline-block; margin-top: 15px; margin-bottom: 15px; color: white;">';
                        div_content += '<div style="font-size: 30px; ">R$ ' + val["preco"] + '</div></div>';

                        div_content += '<table class="ui inverted table"  id="dados_imovel' + v["id"] + '">';
                        div_content += '<tr>';
                        div_content += '<td>Proprietário</td>';
                        div_content += '<td>' + val["proprietario"]["nome"] + '</td>';
                        div_content += '</tr>';
                        div_content += '<tr>';
                        div_content += '<td>tipoImovel</td>';
                        div_content += '<td>' + val["tipoImovel"] + '</td>';
                        div_content += '</tr>';
                        div_content += '<tr>';
                        div_content += '<td>largura</td>';
                        div_content += '<td>' + val["largura"] + '</td>';
                        div_content += '</tr>';
                        div_content += '<tr>';
                        div_content += '<td>comprimento</td>';
                        div_content += '<td>' + val["comprimento"] + '</td>';
                        div_content += '</tr>';
                        div_content += '</div>';
                        div.innerHTML += div_content;
                        div.setAttribute('class', 'ui inverted card');
                        card_content.appendChild(div);
                    }

                }
            }
        }
    }

    xhr.send();
};