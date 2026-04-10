<div align="center">

# 🧪 Lab Registry API

> API REST para registro e gerenciamento do ciclo de vida de experimentos científicos históricos.

![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.5-brightgreen?style=flat-square&logo=springboot)
![H2](https://img.shields.io/badge/H2-Database-blue?style=flat-square)
![Maven](https://img.shields.io/badge/Maven-build-red?style=flat-square&logo=apachemaven)
![Architecture](https://img.shields.io/badge/Architecture-MVC-purple?style=flat-square)

</div>

---

## Visão geral

**Problema:** Registros de experimentos científicos são frequentemente mantidos em cadernos físicos ou planilhas dispersas, sem controle formal do ciclo de vida (qualquer pessoa pode marcar um experimento como concluído sem que ele tenha sequer sido iniciado).

**Solução:** O Lab Registry API centraliza o registro de experimentos e impõe o ciclo científico real: um experimento só pode ser concluído se tiver data de início, só pode ser replicado se estiver concluído, e estados finais como FAILED e REPLICATED são imutáveis, refletindo como a ciência de verdade funciona.

**O que este projeto demonstra tecnicamente:**

- Arquitetura MVC com Spring Boot 4, camadas bem definidas entre controller, service, repository e entity
- Gerenciamento de dependências e build com Apache Maven (`mvnw spring-boot:run`)
- API REST com verbos HTTP corretos (POST, GET, PUT, DELETE, PATCH) e status codes semânticos (201, 200, 204, 404, 422)
- Persistência com Spring Data JPA + Hibernate e banco H2 embutido
- Máquina de estados com `switch expression` validando transições do ciclo científico
- DTOs com `record` do Java 21 separados por responsabilidade, sem expor a entidade JPA
- Tratamento centralizado de erros com `@RestControllerAdvice` e exceções customizadas

---

## Funcionalidades

**Core:**
- `POST /experiments` — cria experimento com status `PLANNED` e `active: true` automáticos
- `GET /experiments` — lista todos os experimentos com resposta resumida
- `GET /experiments/{id}` — retorna detalhes completos de um experimento
- `PUT /experiments/{id}` — atualiza dados do experimento
- `DELETE /experiments/{id}` — remove experimento
- `PATCH /experiments/{id}/active` — toggle ativo/inativo
- `PATCH /experiments/{id}/status` — avança o ciclo científico com validação de transição

**Filtros:**
- `GET /experiments/field/{field}` — filtra por área científica (BIOLOGY, CHEMISTRY, PHYSICS, NEUROSCIENCE, GENETICS, ASTRONOMY)
- `GET /experiments/peer-reviewed` — lista apenas experimentos revisados por pares

**Validações:**
- Campos obrigatórios validados via Bean Validation (`@NotBlank`, `@NotNull`)
- Transição para `IN_PROGRESS` exige `startedAt` preenchido
- Transição para `CONCLUDED` exige `concludedAt` preenchido
- Transições inválidas retornam `422 Unprocessable Entity` com mensagem descritiva
- Experimento não encontrado retorna `404 Not Found`
- Erros de validação retornam `400 Bad Request` com campo e mensagem

---

## Ciclo de vida do experimento

```
PLANNED ──── (startedAt obrigatório) ───► IN_PROGRESS ──── (concludedAt obrigatório) ───► CONCLUDED ───► REPLICATED
                                               │
                                               └──────────────────────────────────────────────────────► FAILED
```

`FAILED` e `REPLICATED` são estados finais, nenhuma transição é permitida a partir deles.

---

## Arquitetura & decisões

**Enums para `Field` e `Status` em vez de `String`**
Garante consistência dos dados no banco e rejeição automática de valores inválidos pelo Spring. Elimina erros de digitação e facilita a manutenção.

**DTOs com `record` do Java 21 separados por responsabilidade**
`ExperimentRequest` controla entrada e validação. `ExperimentResponse` expõe detalhes completos. `ExperimentSummaryResponse` é usado em listagens para evitar payloads pesados. A entidade JPA nunca é exposta diretamente.

**H2 in-memory em vez de Postgres**
Permite clonar e rodar sem nenhuma configuração de banco.

**`@RestControllerAdvice` centralizado**
Todo tratamento de erro vive em `GlobalExceptionHandler`. Resposta padronizada com `status`, `error`, `message` e `timestamp` em todos os endpoints.

---

## Como rodar localmente

**Pré-requisitos:**
- Java 21
- Maven 3.9+

**Passos:**

```bash
# 1. Clone o repositório
git clone https://github.com/karolinexo/lab-registry-api.git
cd lab-registry-api

# 2. Suba a aplicação
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

O banco H2 é populado automaticamente com 15 experimentos históricos ao subir.

**Console H2** (visualizar banco): `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:labregistry`
- Usuário: `sa`
- Senha: *(vazio)*

---

## Exemplos de requisição

**Criar experimento:**
```bash
curl -X POST http://localhost:8080/experiments \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Novo Experimento",
    "field": "BIOLOGY",
    "researcher": "Nome do Pesquisador",
    "hypothesis": "Hipótese do experimento",
    "peerReviewed": false,
    "startedAt": "2024-01-01"
  }'
```

**Avançar status:**
```bash
curl -X PATCH http://localhost:8080/experiments/{id}/status \
  -H "Content-Type: application/json" \
  -d '{"status": "IN_PROGRESS"}'
```

**Filtrar por área:**
```bash
curl http://localhost:8080/experiments/field/GENETICS
```

---

## Estrutura do projeto

```
src/main/java/com/karolinexo/lab_registry_api/
├── controller/        # Camada HTTP — rotas e mapeamento
├── service/           # Regras de negócio e máquina de estados
├── repository/        # Acesso a dados — derived queries Spring Data
├── entity/            # Entidade JPA Experiment
├── dto/               # Records de entrada/saída (Request, Response, Summary)
├── enums/             # Field e Status
├── exception/         # Exceções customizadas e GlobalExceptionHandler
├── DataLoader.java    # Seed com 15 experimentos históricos reais
└── LabRegistryApiApplication.java
```

Organização por camada técnica seguindo o padrão MVC do Spring Boot.

---

## Métricas & insights

Acesse diretamente pelo GitHub:

- **Pulse** (atividade recente): `Insights → Pulse`
- **Commits ao longo do tempo**: `Insights → Graphs → Code frequency`
- **Tráfego**: `Insights → Traffic`

## Autor

**Maria Karoline**

[![GitHub](https://img.shields.io/badge/GitHub-karolinexo-181717?style=flat-square&logo=github)](https://github.com/karolinexo)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-mariakaroline-0077B5?style=flat-square&logo=linkedin)](https://www.linkedin.com/in/maria-karoline/)

---

> Feito com muito ☕ e Java.
