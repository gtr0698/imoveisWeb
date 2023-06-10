function create_cards(data, card_content) {
    var xhr = new XMLHttpRequest();
    const url = 'http://localhost:8080/cadastros/imoveis'
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
                div_content += '<div class="header" id="' + v["number"] + '"style="color: white; font-size:20px">' + v["number"];
                div_content += '<img class="ui tiny image right floated" src="/static/img/onts/' + v["equipment_id"] + '.png"></div>'
                /*
                div_content += '<div class="meta content" style="color: white;">';
                div_content += '<br>';
                div_content += '<p>' + v["sn"] + '<\p>';
                div_content += '<p>Model: ' + v["equipment_id"] + '<\p>';
                div_content += '<p">Board: ' + v["board"] + '<\p>';
                div_content += '<p">PON: ' + v["pon"] + '<\p>';
                div_content += '<p">Vendor: ' + v["vendor_name"] + '<\p>';
                div_content += '<\div>'; 
                */
                div.innerHTML += div_content;
                div_content = '';

                div_content += '<table class="ui inverted table" id="dados_imovel' + v["number"] + '">';
                div_content += '<tr>';
                div_content += '<td>Time Discovered</td>';
                div_content += '<td>' + v["time"] + '</td>';
                div_content += '</tr>';
                div_content += '<tr>';
                div_content += '<td>Software Version</td>';
                div_content += '<td>' + v["software_version"] + '</td>';
                div_content += '</tr>';
                div_content += '<tr>';
                div_content += '<td>Hardware Version</td>';
                div_content += '<td>' + v["version"] + '</td>';
                div_content += '</tr>';
                div_content += '<tr>';
                div_content += '<td>Info</td>';
                div_content += '<td>' + v["info"] + '</td>';
                div_content += '</tr>';
                div_content += '<\div>';
                div.innerHTML += div_content;
                div.setAttribute('class', 'ui inverted card');
                card_content.appendChild(div);

                count += 1;
            }
        }
    }

    xhr.send();
};