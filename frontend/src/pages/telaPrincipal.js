import React from "react";

const TelaPrincipal = () => {
    return (
        <div>
            <h1>Página Principal</h1>
            <p>Bem-vindo ao seu painel de controle.</p>
            
            <div>
                <h2>Opções de Navegação</h2>
                <ul>
                    <li><a href="/perfil">Meu Perfil</a></li>
                    <li><a href="/consultas">Consultas Agendadas</a></li>
                    <li><a href="/clinicas">Buscar Clínicas</a></li>
                </ul>
            </div>
        </div>
    );
};

export default TelaPrincipal;
