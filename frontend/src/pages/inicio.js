import React from "react";
import { Link } from "react-router-dom";

const Inicio = () => {
    return (
        <div>
            <h1>Bem-vindo ao Connect Azul!</h1>
            <p>Encontre apoio especializado para o autismo e melhore a qualidade de vida.</p>

            <div>
                <Link to="/cadastroPessoa">Sou um Paciente</Link>
            </div>
            <div>
                <Link to="/cadastroPJ">Sou uma Clínica</Link>
            </div>
        </div>
    );
};

export default Inicio;
