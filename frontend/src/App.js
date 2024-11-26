import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";

import Inicio from "./pages/inicio";
import Login from "./pages/login";
import Cadastro from "./pages/cadastroPessoa"; 
import CadastroPJ from "./pages/cadastroPJ";
import TelaPrincipal from "./pages/telaPrincipal";

const Rotas = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Inicio />} />
        <Route path="/login" element={<Login />} />
        <Route path="/cadastroPessoa" element={<Cadastro />} />
        <Route path="/cadastroPJ" element={<CadastroPJ />} />
        <Route path="/telaPrincipal" element={<TelaPrincipal />} />
      </Routes>
    </BrowserRouter>
  );
};

export default Rotas;
