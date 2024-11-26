import React, { useState } from 'react';
import { Link } from 'react-router-dom';

function CadastroPaciente() {
  const [nome, setNome] = useState('');
  const [email, setEmail] = useState('');
  const [cpf, setCpf] = useState('');
  const [dataNascimento, setDataNascimento] = useState('');
  const [telefone, setTelefone] = useState('');
  const [senha, setSenha] = useState('');
  const [confirmacaoSenha, setConfirmacaoSenha] = useState('');
  const [responsavelNome, setResponsavelNome] = useState('');
  const [responsavelCpf, setResponsavelCpf] = useState('');
  const [responsavelTelefone, setResponsavelTelefone] = useState('');
  const [error, setError] = useState('');

  const validarCadastro = () => {
    if (senha !== confirmacaoSenha) {
      setError('Senhas não coincidem');
      return false;
    }

    const senhaRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*]).{6,}$/;
    if (!senhaRegex.test(senha)) {
      setError('A senha deve ter no mínimo 6 caracteres, uma letra minúscula, uma maiúscula e um caractere especial.');
      return false;
    }

    return true;
  };

  const handleSubmit = (evento) => {
    evento.preventDefault();

    if (!validarCadastro()) return;

    const dadosCadastro = {
      nome,
      email,
      cpf,
      dataNascimento,
      telefone,
      senha,
      responsavel: {
        nome: responsavelNome,
        cpf: responsavelCpf,
        telefone: responsavelTelefone
      }
    };

    console.log('Dados de Cadastro:', dadosCadastro);
    alert('Cadastro realizado com sucesso!');
  };

  const handleChange = (event) => {
    const { id, value } = event.target;
    
    switch(id) {
      case 'cpf':
        const numericValue = value.replace(/\D/g, ''); 
        setCpf(numericValue);
        break;
      case 'telefone':
        const numericPhone = value.replace(/\D/g, ''); 
        setTelefone(numericPhone);
        break;
      case 'responsavelCpf':
        const numericResponsavelCpf = value.replace(/\D/g, ''); 
        setResponsavelCpf(numericResponsavelCpf);
        break;
      case 'responsavelTelefone':
        const numericResponsavelTelefone = value.replace(/\D/g, ''); 
        setResponsavelTelefone(numericResponsavelTelefone);
        break;
      default:
        setError('');
    }
  };

  return (
    <>
      <style>{`
        .tela-cadastroPessoa {
          display: flex;
          flex-direction: column;
          align-items: center;
          justify-content: center;
          min-height: 100vh;
          background-color: #f0f2f5;
          padding: 20px;
        }

        .white-box-cadastroPessoa {
          background-color: white;
          border-radius: 8px;
          box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
          width: 100%;
          max-width: 400px;
          padding: 30px;
        }

        .titulo-cadastroPessoa {
          font-size: 24px;
          font-weight: bold;
          text-align: center;
          margin-bottom: 30px;
          color: #4A90E2;
        }

        .label-input-cadastroPessoa {
          display: block;
          margin-bottom: 15px;
        }

        .input-cadastroPessoa {
          width: 100%;
          padding: 10px;
          border: 1px solid #ddd;
          border-radius: 4px;
          font-size: 16px;
          transition: border-color 0.3s ease;
        }

        .input-cadastroPessoa:focus {
          outline: none;
          border-color: #4A90E2;
        }

        .submit-cadastroPessoa {
          background-color: #4A90E2;
          color: white;
          border: none;
          padding: 12px 24px;
          border-radius: 4px;
          font-size: 16px;
          cursor: pointer;
          transition: background-color 0.3s ease;
        }

        .submit-cadastroPessoa:hover {
          background-color: #357ABD;
        }

        .link-login {
          margin-top: 20px;
          color: #4A90E2;
          text-decoration: none;
        }

        .error-message {
          color: red;
          text-align: center;
          margin-bottom: 10px;
        }

        small {
          display: block;
          margin-top: 8px;
          font-size: 12px;
          color: #666;
        }
      `}</style>

      <div className='tela-cadastroPessoa'>
        <div className="white-box-cadastroPessoa">
          <div className="titulo-cadastroPessoa">Cadastro de Paciente</div>
          <form onSubmit={handleSubmit}>
            <div>
              <label className='label-input-cadastroPessoa'>
                <input 
                  className='input-cadastroPessoa' 
                  type="text" 
                  value={nome} 
                  onChange={(evento) => setNome(evento.target.value)} 
                  placeholder='Nome completo' 
                  required
                />
              </label>
            </div>
            <div>
              <label className='label-input-cadastroPessoa'>
                <input 
                  className='input-cadastroPessoa' 
                  type="email" 
                  value={email} 
                  onChange={(evento) => setEmail(evento.target.value)} 
                  placeholder='E-mail' 
                  required
                />
              </label>
            </div>
            <div>
              <label className='label-input-cadastroPessoa'>
                <input 
                  className='input-cadastroPessoa' 
                  type="text" 
                  id="cpf"
                  value={cpf} 
                  onChange={handleChange} 
                  placeholder='CPF' 
                  minLength={11} 
                  maxLength={11}  
                  required 
                />
              </label> 
            </div>
            <div>
              <label className='label-input-cadastroPessoa'>
                <input 
                  className='input-cadastroPessoa' 
                  placeholder='Data de Nascimento' 
                  type="date" 
                  value={dataNascimento} 
                  onChange={(evento) => setDataNascimento(evento.target.value)} 
                  required 
                />
              </label>
            </div>
            <div>
              <label className='label-input-cadastroPessoa'>
                <input 
                  className='input-cadastroPessoa' 
                  type="tel" 
                  id="telefone"
                  value={telefone} 
                  onChange={handleChange} 
                  placeholder='Telefone' 
                  required
                />
              </label>
            </div>
            <div>
              <label className='label-input-cadastroPessoa'>
                <input 
                  className='input-cadastroPessoa' 
                  type="password" 
                  value={senha} 
                  onChange={(evento) => setSenha(evento.target.value)} 
                  placeholder='Senha' 
                  required
                />
                <small>Senha deve conter no mínimo 6 dígitos, uma letra minúscula, uma maiúscula e um caractere especial.</small>
              </label>
            </div>
            <div>
              <label className='label-input-cadastroPessoa'>
                <input 
                  className='input-cadastroPessoa' 
                  type="password" 
                  value={confirmacaoSenha} 
                  onChange={(evento) => setConfirmacaoSenha(evento.target.value)} 
                  placeholder='Confirme sua Senha' 
                  required
                />
              </label>
            </div>

            <div>
              <h3>Informações do Responsável</h3>
              <label className='label-input-cadastroPessoa'>
                <input 
                  className='input-cadastroPessoa' 
                  type="text" 
                  value={responsavelNome} 
                  onChange={(evento) => setResponsavelNome(evento.target.value)} 
                  placeholder='Nome do Responsável' 
                  required
                />
              </label>
            </div>
            <div>
              <label className='label-input-cadastroPessoa'>
                <input 
                  className='input-cadastroPessoa' 
                  type="text" 
                  id="responsavelCpf"
                  value={responsavelCpf} 
                  onChange={handleChange} 
                  placeholder='CPF do Responsável' 
                  minLength={11} 
                  maxLength={11} 
                  required 
                />
              </label> 
            </div>
            <div>
              <label className='label-input-cadastroPessoa'>
                <input 
                  className='input-cadastroPessoa' 
                  type="tel" 
                  id="responsavelTelefone"
                  value={responsavelTelefone} 
                  onChange={handleChange} 
                  placeholder='Telefone do Responsável' 
                  required
                />
              </label>
            </div>

            {error && <div className="error-message">{error}</div>}

            <div style={{display: 'flex', justifyContent: 'center'}}>
              <button type="submit" className="submit-cadastroPessoa">Cadastrar</button>
            </div>
            <div style={{ textAlign: 'center', marginTop: '10px' }}>
              <Link className="link-login" to="/login">Já tem uma conta? Faça login</Link>
            </div>
          </form>
        </div>
      </div>
    </>
  );
}

export default CadastroPaciente;
