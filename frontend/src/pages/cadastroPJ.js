import React, { useState } from 'react';

const styles = {
  container: {
    minHeight: '100vh',
    backgroundColor: '#f3f4f6',
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
    padding: '1rem'
  },
  formContainer: {
    backgroundColor: 'white',
    width: '100%',
    maxWidth: '28rem',
    borderRadius: '0.75rem',
    boxShadow: '0 10px 15px -3px rgba(0, 0, 0, 0.1)',
    padding: '2rem'
  },
  title: {
    fontSize: '1.875rem',
    fontWeight: 'bold',
    textAlign: 'center',
    color: '#2563eb',
    marginBottom: '1.5rem'
  },
  form: {
    display: 'flex',
    flexDirection: 'column',
    gap: '1rem'
  },
  label: {
    display: 'block',
    color: '#374151',
    fontWeight: 600,
    marginBottom: '0.5rem'
  },
  input: {
    width: '100%',
    padding: '0.5rem 1rem',
    border: '1px solid #d1d5db',
    borderRadius: '0.375rem',
    focus: {
      outline: 'none',
      boxShadow: '0 0 0 2px rgba(59, 130, 246, 0.5)'
    }
  },
  button: {
    width: '100%',
    backgroundColor: '#2563eb',
    color: 'white',
    padding: '0.75rem',
    borderRadius: '0.375rem',
    border: 'none',
    cursor: 'pointer',
    transition: 'background-color 0.3s ease',
    hover: {
      backgroundColor: '#1d4ed8'
    }
  }
};

const CadastroPJ = () => {
  const [formData, setFormData] = useState({
    nomeClínica: '',
    email: '',
    telefone: '',
    especialidade: ''
  });

  const handleChange = (e) => {
    const { id, value } = e.target;
    setFormData(prevState => ({
      ...prevState,
      [id]: value
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log('Dados do formulário:', formData);
    alert('Cadastro enviado com sucesso!');
  };

  return (
    <div style={styles.container}>
      <div style={styles.formContainer}>
        <h1 style={styles.title}>
          Cadastro de Clínica
        </h1>
        <form onSubmit={handleSubmit} style={styles.form}>
          <div>
            <label 
              htmlFor="nomeClínica" 
              style={styles.label}
            >
              Nome da Clínica
            </label>
            <input
              type="text"
              id="nomeClínica"
              value={formData.nomeClínica}
              onChange={handleChange}
              placeholder="Digite o nome da clínica"
              required
              style={{
                ...styles.input,
                ':focus': styles.input.focus
              }}
            />
          </div>

          <div>
            <label 
              htmlFor="email" 
              style={styles.label}
            >
              Email
            </label>
            <input
              type="email"
              id="email"
              value={formData.email}
              onChange={handleChange}
              placeholder="Digite seu email"
              required
              style={{
                ...styles.input,
                ':focus': styles.input.focus
              }}
            />
          </div>

          <div>
            <label 
              htmlFor="telefone" 
              style={styles.label}
            >
              Telefone
            </label>
            <input
              type="tel"
              id="telefone"
              value={formData.telefone}
              onChange={handleChange}
              placeholder="Digite o telefone da clínica"
              required
              style={{
                ...styles.input,
                ':focus': styles.input.focus
              }}
            />
          </div>

          <div>
            <label 
              htmlFor="especialidade" 
              style={styles.label}
            >
              Especialidade
            </label>
            <input
              type="text"
              id="especialidade"
              value={formData.especialidade}
              onChange={handleChange}
              placeholder="Especialidade da clínica"
              required
              style={{
                ...styles.input,
                ':focus': styles.input.focus
              }}
            />
          </div>

          <button 
            type="submit" 
            style={{
              ...styles.button,
              ':hover': styles.button.hover
            }}
          >
            Cadastrar
          </button>
        </form>
      </div>
    </div>
  );
};

export default CadastroPJ;