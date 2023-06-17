$(document).ready(function () {
	const input_search = document.getElementById('pesquisar_usuario');
	const users = document.getElementById('tabela_usuarios');
	carregar_usuarios();
	input_search.addEventListener('keyup', () => {
		let search = input_search.value.toLowerCase();

		let rows = users.getElementsByTagName('tr');

		for (let pos in rows) {
			if (isNaN(pos)) {
				continue;
			}
			let row_content = rows[pos].innerHTML.toLowerCase();

			if (row_content.includes(search)) {
				rows[pos].style.display = '';
			}
			else {
				rows[pos].style.display = 'none';
			}
		}

	});
});

function carregar_usuarios() {
	const url = 'http://localhost:8080/pessoas/listar';
	const xhr = new XMLHttpRequest();
	xhr.open('GET', url, true);
	xhr.setRequestHeader("Content-Type", "application/json");

	xhr.onreadystatechange = function () {

		if (this.readyState == 4 && this.status == 200) {

			//converter a resposta em JSON
			var response = JSON.parse(xhr.responseText);
			const lista_usuarios = document.getElementById("lista_usuarios")
			lista_usuarios.innerHTML = ''

			for (var user in response) {

				var tr = document.createElement('tr');
				var tr_content = '';
				tr_content += '<td style="display: none;">' + response[user]["idPessoa"] + '</td>';
				tr_content += '<td>' + response[user]["nome"] + '</td>';
				tr_content += '<td>' + response[user]["email"] + '</td>';
				tr_content += '<td>' + response[user]["telefone"] + '</td>';
				tr_content += '<td>' + response[user]["numeroDocumento"] + '</td>';
				tr_content += '<td>' + response[user]["tipoPessoa"] + '</td>';
				tr_content += "<td><a class='ui primary basic button' onclick='preencher_usuario(" + JSON.stringify(response[user]) + ")'>Editar</a>";
				tr_content += "<a class='ui red basic button' onclick='excluir_usuario(" + JSON.stringify(response[user]) + ")'>Remover</a></td>";
				tr.innerHTML += tr_content;
				lista_usuarios.appendChild(tr);
			}

		}
		if (this.readyState == 4 && this.status == 415) {
			console.log("XHR" + xhr.response);
		}

	}
	xhr.send();
}

function preencher_usuario(usuario) {
	$('#mdl_cadastro_usuario').modal({closable: false}).modal('show');

	const formulario = document.getElementById("form_usuario");
	const dados_form = new FormData(formulario);
	var url = "http://localhost:8080/pessoas/buscar/" + usuario["idPessoa"];
	const xhr = new XMLHttpRequest();
	xhr.open('GET', url, true);
	xhr.setRequestHeader("Content-Type", "application/json");

	xhr.onreadystatechange = function () {
		if (this.readyState == 4 && this.status == 200) {
			var res = this.responseText;
			var data = JSON.parse(res);

			for (const [k, v] of Object.entries(data)) {
				if (k != 'role') {
					document.getElementById(k).value = v;
				}
			}
		}
	}

	xhr.send();
}

function excluir_usuario(usuario) {
	$('#delete_confirmation').modal({
		closable: false,
		onApprove: function () {
			var url = 'http://localhost:8080/pessoas/remover/' + usuario['idPessoa'];
			const xhr = new XMLHttpRequest();
			xhr.open('DELETE', url, true);

			xhr.onreadystatechange = function () {
				if (this.readyState == 4 && this.status == 200) {
					alert("Usuário " + usuario['nome'] + " Excluído!");
					carregar_usuarios();
				}
			}
			xhr.send();
		}
	}).modal('show');
}

function editar_usuario(){
	const formulario = document.getElementById("form_usuario");
    const dados_form = new FormData(formulario);
	var obj_form = {};

	const url = 'http://localhost:8080/pessoas/atualizar/' + document.getElementById('idPessoa').value;
    const xhr = new XMLHttpRequest();

    xhr.open('PUT', url, true);
    xhr.setRequestHeader("Content-Type", "application/json");

	for (const [k, v] of dados_form.entries()) {
        obj_form[k] = v;
    }

    xhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            alert("Usuário Editado");
			carregar_usuarios();
        }
    }
	obj_form = JSON.stringify(obj_form);
    xhr.send(obj_form);
	$('#mdl_cadastro_usuario').modal('hide');
}