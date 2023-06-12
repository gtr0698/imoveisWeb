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
	const url = 'http://localhost:8080/cadastros/pessoas';
	const xhr = new XMLHttpRequest();
	xhr.open('GET', url, true);
	xhr.setRequestHeader("Content-Type", "application/json");

	xhr.onreadystatechange = function () {

		if (this.readyState == 4 && this.status == 200) {

			//converter a resposta em JSON
			var response = JSON.parse(xhr.responseText);
			console.log(response);
			const lista_usuarios = document.getElementById("lista_usuarios")
			lista_usuarios.innerHTML = ''
			console.log(response['content']);
			for (var user in response['content']) {
				var tr = document.createElement('tr');
				var tr_content = '';
				tr_content += '<td>' + response['content'][user]["nome"] + '</td>';
				tr_content += '<td>' + response['content'][user]["email"] + '</td>';
				tr_content += '<td>' + response['content'][user]["telefone"] + '</td>';
				tr_content += '<td>' + response['content'][user]["numeroDocumento"] + '</td>';
				tr_content += '<td>' + response['content'][user]["papel"] + '</td>';
				tr_content += '<td>' + response['content'][user]["tipoPessoa"] + '</td>';
				tr_content += '<td><a class="ui primary basic button" href=#>Editar</a>';
				tr_content += "<a class='ui red basic button' onclick='excluir_usuario(" + JSON.stringify(response['content'][user]) + ")'>Remover</a></td>";
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

function excluir_usuario(usuario) {
	$('#delete_confirmation').modal({
		closable: false,
		onApprove: function () {
			var url = 'http://localhost:8080/cadastros/pessoas/' + usuario['idPessoa'];
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