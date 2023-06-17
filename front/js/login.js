<<<<<<< HEAD
// function verificar_login() {
//     const formulario = document.getElementById("form_login");
//     const url = 'http://localhost:8080/pessoas/login';
//     const dados_form = new FormData(formulario);
//     var obj_form = {};

//     const xhr = new XMLHttpRequest();

//     xhr.open('GET', url, true);
//     xhr.setRequestHeader("Content-Type", "application/json");

//     for (const [k, v] of dados_form.entries()) {
//         console.log(k + " >>> " + v)
//         obj_form[k] = v;
//     }

//     xhr.onreadystatechange = function() {
//         if (this.readyState == 4 && this.status == 200) {
//             console.log("ok");
//         }
//     }

//     obj_form = JSON.stringify(obj_form);
//     console.log(obj_form)
//     xhr.send(obj_form);
// }
=======
$(document).ready(function () {
    localStorage.removeItem('id_pessoa');
});
>>>>>>> master

function verificar_login() {
    const formulario = document.getElementById("form_login");
    const dados_form = new FormData(formulario);
    var obj_form = {};
<<<<<<< HEAD
=======
    const idUser = document.getElementById('id_pessoa');
    const xhr = new XMLHttpRequest();

    xhr.open('POST', url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
>>>>>>> master

    for (const [k, v] of dados_form.entries()) {
        obj_form[k] = v;
    }

    obj_form = JSON.stringify(obj_form);
    console.log(obj_form);

    const url = 'http://localhost:8080/pessoas/login';
    const xhr = new XMLHttpRequest();

    xhr.open('GET', url, true);
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
<<<<<<< HEAD
            alert("Imóvel Editado");
        }
    }

    xhr.send(obj_form);
}
=======
            var res = this.responseText;
            var data = JSON.parse(res);
            localStorage.setItem('id_pessoa', data['idPessoa']);
            window.location.href = 'index.html';
        }
        else if(this.readyState == 4){
            alert("Credenciais incorretas!")
        }
    }
    xhr.send(JSON.stringify(obj_form));
}
>>>>>>> master
