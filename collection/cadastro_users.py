# -*- coding: utf-8 -*-
"""
Spyder Editor

This is a temporary script file.
"""

import requests as re
import json

if __name__ == '__main__':
    users = [{
            "nome": "Luiz",
            "email": "luiz@gmail.com",
            "telefone": "45911132333",
            "numeroDocumento": "33371412312",
            "tipoPessoa": "PESSOA_FISICA",
            "papel": "CLIENTE",
            "senha": "senha1"
            },
            {
            "nome": "Rafael",
            "email": "rafael@gmail.com",
            "telefone": "45999998888",
            "numeroDocumento": "33322211166",
            "tipoPessoa": "PESSOA_FISICA",
            "papel": "CLIENTE",
            "senha": "senha1"
            },
            {
            "nome": "Pedro",
            "email": "pedro@gmail.com",
            "telefone": "45988887777",
            "numeroDocumento": "99998888877",
            "tipoPessoa": "PESSOA_FISICA",
            "papel": "CLIENTE",
            "senha": "senha1"
            },
            {
            "nome": "Guilherme",
            "email": "guilherme@gmail.com",
            "telefone": "45977776666",
            "numeroDocumento": "33366677788",
            "tipoPessoa": "PESSOA_FISICA",
            "papel": "CLIENTE",
            "senha": "senha1"
            },
            {
            "nome": "Ana",
            "email": "ana@gmail.com",
            "telefone": "45966668888",
            "numeroDocumento": "55544433322",
            "tipoPessoa": "PESSOA_FISICA",
            "papel": "CLIENTE",
            "senha": "senha1"
            },
            {
            "nome": "Joel",
            "email": "joel@gmail.com",
            "telefone": "45967589483",
            "numeroDocumento": "44433388866",
            "tipoPessoa": "PESSOA_FISICA",
            "papel": "CLIENTE",
            "senha": "senha1"
            },
            {
            "nome": "Carolina",
            "email": "carolina@gmail.com",
            "telefone": "45988877778",
            "numeroDocumento": "11133322266",
            "tipoPessoa": "PESSOA_FISICA",
            "papel": "CLIENTE",
            "senha": "senha1"
            },
            {
            "nome": "Cleiton",
            "email": "cleiton@gmail.com",
            "telefone": "45999008877",
            "numeroDocumento": "99955588800",
            "tipoPessoa": "PESSOA_FISICA",
            "papel": "CLIENTE",
            "senha": "senha1"
            },
            {
            "nome": "Maria",
            "email": "maria@gmail.com",
            "telefone": "45966677755",
            "numeroDocumento": "34456783455",
            "tipoPessoa": "PESSOA_FISICA",
            "papel": "CLIENTE",
            "senha": "senha1"
            }
        ]
    url = 'http://localhost:8080/cadastros/pessoas'
    for user in users:
        user = json.dumps(user)
        api = re.post(url, data=user, headers={"Content-Type": "application/json"})
        
        print('API Response: ' + api.text + '\n') 
    