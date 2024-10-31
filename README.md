# Connect Azul

Este projeto é uma integração com o sistema Azul, permitindo a comunicação e troca de dados entre diferentes serviços.

## Estrutura do Projeto

```plaintext
connect-azul/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── connectazul/
│   │   │               ├── controller/
│   │   │               ├── service/
│   │   │               ├── model/
│   │   │               └── repository/
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── example/
│       │           └── connectazul/
│       │               └── service/
│       └── resources/
├── pom.xml
└── README.md
```

## Tecnologias Utilizadas

- **Java 11**
- **Spring Boot**
- **Maven**

## Como Executar

1. Clone o repositório:
    ```sh
    git clone https://github.com/usuario/connect-azul.git
    ```
2. Navegue até o diretório do projeto:
    ```sh
    cd connect-azul
    ```
3. Compile e execute o projeto:
    ```sh
    mvn spring-boot:run
    ```

## Contribuição

1. Faça um fork do projeto.
2. Crie uma branch para sua feature:
    ```sh
    git checkout -b minha-feature
    ```
3. Faça commit das suas alterações:
    ```sh
    git commit -m 'Adiciona minha feature'
    ```
4. Envie para o repositório remoto:
    ```sh
    git push origin minha-feature
    ```
5. Abra um Pull Request.

## Licença

Este projeto está licenciado sob a Licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.