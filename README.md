# Connect Azul - Guia do Desenvolvedor

## 🔧 Stack Tecnológica

### Backend
- Java 17
- Spring Boot 3.x
- Spring Security
- Spring Data JPA
- H2 Database
- Maven

### Frontend
- React.js
- Axios
- React Query

### CI/CD
- GitHub Actions
- AWS

## 💻 Ambiente de Desenvolvimento

### Pré-requisitos
- JDK 17+
- Maven 3.8+
- Node.js 18+
- Git

### Setup Backend

1. Clone e acesse o projeto
```bash
git clone https://github.com/Em4nueel/connect-azul.git
cd connect-azul/backend
```

2. Configuração do application.properties
```properties
# H2
spring.datasource.url=jdbc:h2:file:./data/connect_azul
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true

# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Security
jwt.secret=${JWT_SECRET:your-secret-key}
jwt.expiration=86400000

# Server
server.port=8080
```

3. Build e execução
```bash
mvn clean install
mvn spring-boot:run
```

### Setup Frontend

1. Acesse a pasta e instale dependências
```bash
cd frontend
npm install
```

2. Configure o .env
```env
VITE_API_URL=http://localhost:8080
```

3. Execute
```bash
npm run dev
```

## 📚 API e Banco de Dados

### Swagger
```
http://localhost:8080/swagger-ui.html
```

### H2 Console
```
URL: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:file:./data/connect_azul
User: sa
Password: password
```

## 🧪 Testes

### Backend
```bash
mvn test
# Cobertura de testes
mvn jacoco:report
```

### Frontend
```bash
npm test
# Modo watch
npm test -- --watch
```

## 📝 Padrões de Desenvolvimento

### Estrutura de Diretórios

```
backend/
├── src/
│   ├── main/
│   │   ├── java/com/connectazul/
│   │   │   ├── config/
│   │   │   ├── controller/
│   │   │   ├── model/
│   │   │   ├── repository/
│   │   │   ├── service/
│   │   │   └── security/
│   │   └── resources/
│   └── test/
frontend/
├── src/
│   ├── components/
│   ├── pages/
│   ├── services/
│   ├── hooks/
│   └── utils/
```

### Padrões de Código

#### Backend
- Google Java Style Guide
- Classes imutáveis quando possível
- DTOs para transferência de dados
- Princípios SOLID
- Injeção de dependências

#### Frontend
- ESLint + Prettier
- Componentes funcionais
- Custom hooks para lógica reutilizável
- React Query para gerenciamento de estado
- Tailwind para estilização

### Git

#### Branches
- main: produção
- develop: desenvolvimento
- feature/*: novas funcionalidades
- fix/*: correções
- release/*: preparação para release

#### Commits
```bash
feat: nova funcionalidade
fix: correção de bug
docs: documentação
style: formatação
refactor: refatoração
test: testes
chore: manutenção
```

## 🔒 Segurança

### Endpoints Protegidos
Todos os endpoints, exceto `/auth/**` e `/public/**`, requerem autenticação.

### JWT
```java
Authorization: Bearer <token>
```

### CORS
Configurado para aceitar apenas origens específicas:
- `http://localhost:5173` (dev)
- `https://connectazul.com.br` (prod)

## 📦 Build e Deploy

### Build Backend
```bash
mvn clean package -DskipTests
```

### Build Frontend
```bash
npm run build
```

### Scripts CI/CD
Localização: `.github/workflows/`
- `build.yml`: build e testes
- `deploy.yml`: deploy para AWS

## 🐛 Debug

### Backend
```bash
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005"
```

### Frontend
```bash
# Chrome DevTools
npm run dev -- --debug
```

## 📚 Documentação Adicional
- [Documentação da API](http://localhost:8080/swagger-ui.html)
- [Javadoc](./backend/target/site/apidocs/index.html)
- [Relatório de Cobertura](./backend/target/site/jacoco/index.html)

---
Para dúvidas técnicas, contate: dev@connectazul.com.br
