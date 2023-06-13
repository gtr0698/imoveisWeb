function trocarImagem() {
    var imagens = document.querySelectorAll('.slideshow img');
    var imagemAtual = document.querySelector('.slideshow img:not([style*="display: none"])');

    imagemAtual.style.display = 'none';

    if (imagemAtual.nextElementSibling) {
        imagemAtual.nextElementSibling.style.display = 'block';
    } else {
        imagens[0].style.display = 'block';
    }
}

setInterval(trocarImagem, 3000);
