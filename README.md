
# Empreenda API

API desenvolvida para o projeto **EmpreendaMais**, oferecendo recursos para autenticação, gestão de produtos, vendas, e integração com serviços de IA.

## Descrição

Esta aplicação foi construída utilizando **Spring Boot 3.4.3** com Java 17, seguindo arquitetura RESTful, com foco em segurança, integração com IA, e documentação automatizada.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**: Web, Data JPA, Security, Validation, Devtools
- **Banco de Dados**: H2 (em memória)
- **Lombok**: para redução de boilerplate
- **JWT**: autenticação e segurança
- **Spring AI**: integração com modelos da OpenAI
- **Swagger/OpenAPI**: documentação automática da API

## Dependências Principais

- `spring-boot-starter-web`
- `spring-boot-starter-data-jpa`
- `spring-boot-starter-security`
- `spring-boot-starter-validation`
- `springdoc-openapi-starter-webmvc-ui`
- `spring-ai-starter-model-openai`
- `com.auth0:java-jwt`
- `H2 Database`

## Estrutura do Projeto

```
├── config                # Configurações de segurança, CORS e seed
├── controller           # Endpoints REST (auth, produtos, vendas, usuários)
├── exception            # Manipuladores de exceção
├── model                # Entidades JPA e enums
├── repository           # Interfaces de persistência (Spring Data JPA)
├── service              # Regras de negócio e integrações
├── specification        # Filtros dinâmicos para vendas
└── App.java             # Classe principal de inicialização
```

## Segurança

- Implementação de **JWT** para autenticação e autorização.
- Filtro `AuthFilter` para interceptação de requisições.
- Configuração de CORS para suporte a requisições cross-origin.

## Integração com IA

- Serviço `AiAnaliseService` utilizando **Spring AI** para análises baseadas em modelos da OpenAI.

## Documentação da API

A documentação interativa da API está disponível via **Swagger UI**:

```
http://localhost:8080/swagger-ui/index.html
```

## Como Executar

1. **Pré-requisitos**:
   - Java 17
   - Maven

2. **Clonar o repositório**:

```bash
git clone [<url-do-repositorio>](https://github.com/MarsoL4/empreenda-api)
cd empreenda-api
```

3. **Compilar e executar**:

```bash
mvn spring-boot:run
```

4. Acesse em:

```
http://localhost:8080
```

## Banco de Dados

- Utiliza **H2 Database** (em memória) para facilitar testes e desenvolvimento.
- Console disponível em:

```
http://localhost:8080/db
```

## Testes

- Estrutura preparada para testes com `spring-boot-starter-test`.

## Licença

Este projeto é livre para uso acadêmico e educacional.
