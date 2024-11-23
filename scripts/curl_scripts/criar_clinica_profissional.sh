#!/bin/bash
curl -X POST "http://localhost:8080/api/clinica-profissionais/novo?clinicaId={clinicaId}" \
     -H "Content-Type: application/json" \
     -d '{
            "nome": "Dr. João Silva",
            "especialidade": "Cardiologia",
            "crm": "12345",
            "telefone": "11999999999",
            "email": "joao.silva@exemplo.com"
        }'
