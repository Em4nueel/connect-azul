import React, { useState } from "react";
import { Link } from 'react-router-dom';

const Login = () => {
    const [email, setEmail] = useState('');
    const [senha, setSenha] = useState('');
    const [error, setError] = useState('');

    const handleSubmit = (evento) => {
        evento.preventDefault();
        
        // Validações básicas
        if (!email || !senha) {
            setError('Preencha todos os campos');
            return;
        }

        // Lógica de login
        console.log('Tentativa de Login:', { email, senha });
        // Aqui normalmente seria feita a chamada para API de autenticação
        alert('Tentativa de login realizada');
    };

    return (
        <>
            <style>{`
                .tela-login {
                    display: flex;
                    flex-direction: column;
                    align-items: center;
                    justify-content: center;
                    min-height: 100vh;
                    background-color: #f0f2f5;
                    padding: 20px;
                }

                .white-box-login {
                    background-color: white;
                    border-radius: 8px;
                    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                    width: 100%;
                    max-width: 400px;
                    padding: 30px;
                }

                .titulo-login {
                    text-align: center;
                    color: #333;
                    margin-bottom: 25px;
                    font-size: 24px;
                }

                .formulario-login {
                    display: flex;
                    flex-direction: column;
                }

                .grupo-input {
                    margin-bottom: 15px;
                }

                .label-input-login {
                    display: block;
                    margin-bottom: 5px;
                    color: #555;
                    font-weight: 500;
                }

                .input-login {
                    width: 100%;
                    padding: 10px;
                    border: 1px solid #ddd;
                    border-radius: 4px;
                    font-size: 16px;
                    transition: border-color 0.3s ease;
                }

                .input-login:focus {
                    outline: none;
                    border-color: #4A90E2;
                }

                .mensagem-erro {
                    color: red;
                    text-align: center;
                    margin-bottom: 15px;
                }

                .opcoes-login {
                    text-align: right;
                    margin-bottom: 15px;
                }

                .link-recuperar-senha {
                    color: #4A90E2;
                    text-decoration: none;
                    font-size: 14px;
                }

                .botao-login {
                    background-color: #4A90E2;
                    color: white;
                    border: none;
                    padding: 12px;
                    border-radius: 4px;
                    font-size: 16px;
                    cursor: pointer;
                    transition: background-color 0.3s ease;
                }

                .botao-login:hover {
                    background-color: #357ABD;
                }

                .cadastro-link {
                    text-align: center;
                    margin-top: 15px;
                    color: #666;
                }

                .link-cadastro {
                    color: #4A90E2;
                    text-decoration: none;
                    margin-left: 5px;
                }
            `}</style>

            <div className='tela-login'>
                <div className="white-box-login">
                    <h1 className="titulo-login">Login</h1>
                    
                    <form onSubmit={handleSubmit} className="formulario-login">
                        <div className="grupo-input">
                            <label htmlFor="email" className="label-input-login">Email:</label>
                            <input 
                                type="email" 
                                id="email" 
                                className="input-login" 
                                placeholder="Digite seu email"
                                value={email}
                                onChange={(e) => {
                                    setEmail(e.target.value);
                                    setError('');
                                }}
                                required
                            />
                        </div>
                        
                        <div className="grupo-input">
                            <label htmlFor="senha" className="label-input-login">Senha:</label>
                            <input 
                                type="password" 
                                id="senha" 
                                className="input-login" 
                                placeholder="Digite sua senha"
                                value={senha}
                                onChange={(e) => {
                                    setSenha(e.target.value);
                                    setError('');
                                }}
                                required
                            />
                        </div>

                        {error && (
                            <div className="mensagem-erro">
                                {error}
                            </div>
                        )}

                        <div className="opcoes-login">
                            <Link to="/recuperar-senha" className="link-recuperar-senha">
                                Esqueceu sua senha?
                            </Link>
                        </div>

                        <button 
                            type="submit" 
                            className="botao-login"
                        >
                            Entrar
                        </button>

                        <div className="cadastro-link">
                            Não tem conta? 
                            <Link to="/cadastro" className="link-cadastro">
                                Cadastre-se
                            </Link>
                        </div>
                    </form>
                </div>
            </div>
        </>
    );
};

export default Login;