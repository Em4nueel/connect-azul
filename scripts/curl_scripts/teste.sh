#!/bin/bash

curl -X POST "http://localhost:8080/api/clinicas" -H "Content-Type: application/json" -d '{
    "nome": "Clínica Saúde",
    "endereco": "Rua das Flores, 123",
    "telefone": "123456789",
    "especialidade": "Cardiologia"
}'
