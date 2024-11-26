import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";

// Importando as páginas
import Inicio from "../pages/inicio";
import Login from "../pages/login";
import CadastroPessoa from "../pages/cadastroPessoa";
import CadastroPJ from "../pages/cadastroPJ";
import TelaPrincipal from "../pages/telaPrincipal";

const Rotas = () => {
  return (
    <BrowserRouter>
      <Routes>
        {/* Definindo as rotas */}
        <Route path="/" element={<Inicio />} />
        <Route path="/login" element={<Login />} />
        <Route path="/cadastroPessoa" element={<CadastroPessoa />} />
        <Route path="/cadastroPJ" element={<CadastroPJ />} />
        <Route path="/telaPrincipal" element={<TelaPrincipal />} />
      </Routes>
    </BrowserRouter>
  );
};

export default Rotas;
