# Connect Azul 🌟

Uma plataforma inovadora que conecta famílias de pessoas com autismo a profissionais especializados em TEA (Transtorno do Espectro Autista) na região de Natal/RN.

## 🎯 Sobre o Projeto

Connect Azul é uma iniciativa desenvolvida em parceria com a APAE que visa facilitar a conexão entre famílias que possuem membros com TEA e profissionais especializados. A plataforma oferece um ambiente seguro e acolhedor para busca, conexão e troca de experiências.

### 🌟 Principais Funcionalidades

- Sistema de cadastro e perfis detalhados para famílias e profissionais
- Busca avançada com filtros especializados
- Sistema de mensagens diretas entre usuários
- Fórum de discussão moderado
- Recursos informativos sobre TEA
- Calendário de eventos relacionados ao autismo
- Sistema de avaliações e feedback

## 🚀 Tecnologias Utilizadas

- **Backend:**
  - Java 17
  - Spring Boot 3.x
  - Spring Security
  - Spring Data JPA
  - MySQL
  - Maven

- **Frontend:**
  - React.js
  - TypeScript
  - Tailwind CSS
  - Axios
  - React Query

- **Infraestrutura:**
  - Docker
  - AWS
  - CI/CD com GitHub Actions

## 📋 Pré-requisitos

- JDK 17+
- Maven 3.8+
- Node.js 18+
- PostgreSQL 14+
- Docker (opcional)

## 🛠️ Instalação e Configuração

1. Clone o repositório
```bash
git clone https://github.com/Em4nueel/connect-azul.git
cd connect-azul
```

2. Configure o banco de dados
```bash
# Crie um banco de dados PostgreSQL
createdb connect_azul

# Configure as variáveis de ambiente
cp .env.example .env
# Edite o arquivo .env com suas configurações
```

3. Execute o backend
```bash
cd backend
mvn clean install
mvn spring-boot:run
```

4. Execute o frontend
```bash
cd frontend
npm install
npm run dev
```

## 🐳 Executando com Docker

```bash
docker-compose up -d
```

## 📚 Documentação da API

A documentação da API está disponível através do Swagger UI após iniciar o servidor:
```
http://localhost:8080/swagger-ui.html
```

## 🧪 Testes

Para executar os testes do backend:
```bash
mvn test
```

Para executar os testes do frontend:
```bash
npm test
```

## 🤝 Como Contribuir

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📝 Convenções de Código

- Utilizamos o Google Java Style Guide para o backend
- ESLint e Prettier para o frontend
- Commits seguem o padrão Conventional Commits

## 🔐 Segurança

- Autenticação JWT
- Dados criptografados em trânsito e em repouso
- Conformidade com LGPD
- Proteção contra XSS e CSRF
- Rate limiting implementado

## 📈 Status do Projeto

- Versão atual: 1.0.0
- Status: Em desenvolvimento
- Data de início: 13/08/2024
- Previsão de conclusão: 03/12/2024

## 👥 Equipe

- **Gerente**: Ana Laura da Silva Pereira
- **Financeiro**: Edson Gama
- **Backend**: Arthur Holanda, João Emanuel, Samuel Farias
- **Frontend**: Edson Gama, Matheus de Lima, Jefferson Fernandes
- **Banco de Dados**: Tiago Cunha, Arthur Holanda, João Emanuel
- **Webdesign**: Girlene Goes, Ana Laura, Tiago Cunha

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 🙏 Agradecimentos

- APAE Natal/RN
- Equipe de desenvolvimento
- Comunidade autista de Natal/RN
- Profissionais e famílias que contribuíram com feedback

## 📞 Contato

Para dúvidas, sugestões ou parcerias, entre em contato através:
- Email: contato@connectazul.com.br
- Website: www.connectazul.com.br

---
Desenvolvido com 💙 pela equipe Connect Azul
