# Sistema Copa do Mundo 2026 — Backend (Spring Boot)

API REST do sistema de gerenciamento de dados da Copa do Mundo FIFA 2026, desenvolvida em **Spring Boot 3 + Java 17**, seguindo a arquitetura em camadas:

```
Controller → Service → Repository → Model (Entity)
```

## 🚀 Como rodar o projeto

### Pré-requisitos
- Java 17 ou superior
- Maven 3.8+ (ou use o wrapper `./mvnw`, se disponível)

### Passos

```bash
# 1. Entre na pasta do backend
cd backend

# 2. Rode a aplicação
mvn spring-boot:run
```

A API ficará disponível em:

```
http://localhost:8080
```

Todos os endpoints estão sob o prefixo `/api`, por exemplo:

```
http://localhost:8080/api/jogadores
http://localhost:8080/api/partidas
```

## 🗄️ Banco de dados (H2)

O projeto usa o banco **H2 em memória** por padrão (ideal para desenvolvimento). Os dados são recriados a cada reinício da aplicação e populados automaticamente com alguns registros de exemplo (países, jogadores, estádios, partidas, campeões etc.) através de um `CommandLineRunner` (`DataSeeder.java`).

### Console do H2

Acesse o console web do banco em:

```
http://localhost:8080/h2-console
```

Use as seguintes credenciais de conexão:

| Campo       | Valor                              |
|-------------|-------------------------------------|
| JDBC URL    | `jdbc:h2:mem:copa2026db`            |
| Usuário     | `sa`                                 |
| Senha       | *(em branco)*                       |

### Trocar para MySQL ou PostgreSQL (produção)

No arquivo `src/main/resources/application.properties`, comente as propriedades do H2 e descomente o bloco correspondente ao banco desejado (MySQL ou PostgreSQL). Não esqueça de adicionar a dependência do driver correspondente no `pom.xml` (instruções já comentadas no arquivo).

## 🔗 CORS

O CORS está configurado em `config/CorsConfig.java` para liberar requisições vindas do frontend React em:

```
http://localhost:3000
```

Caso o frontend rode em outra porta/origem, ajuste essa configuração.

## 📁 Estrutura de pastas

```
backend/
├── src/main/java/com/copa2026/
│   ├── controller/    -> Endpoints REST (@RestController)
│   ├── service/       -> Regras de negócio (@Service)
│   ├── repository/    -> Acesso a dados via Spring Data JPA (@Repository)
│   ├── model/          -> Entidades JPA (@Entity)
│   ├── exception/     -> Exceções customizadas e tratamento global de erros
│   └── config/        -> Configuração de CORS e seed de dados
└── src/main/resources/
    └── application.properties
```

## 📌 Endpoints disponíveis

Todos os recursos abaixo suportam `GET` (listar todos), `GET /{id}` (buscar por id), `POST` (criar), `PUT /{id}` (editar) e `DELETE /{id}` (excluir):

| Recurso                          | Endpoint                          |
|-----------------------------------|------------------------------------|
| Países                            | `/api/paises`                      |
| Jogadores                         | `/api/jogadores` (suporta `?busca=texto`) |
| Estádios                          | `/api/estadios`                    |
| Bolas oficiais                    | `/api/bolas`                       |
| Mascotes oficiais                 | `/api/mascotes`                    |
| Partidas                          | `/api/partidas`                    |
| Histórico de finais               | `/api/finais`                      |
| Melhor desempenho por jogador     | `/api/jogadores/desempenho`        |
| Artilheiros por edição da copa    | `/api/jogadores/artilheiros`       |
| Países campeões                   | `/api/campeoes`                    |
| Músicas tema                      | `/api/musicas`                     |

## ⚠️ Tratamento de erros

Erros são retornados em formato JSON padronizado (ver `exception/GlobalExceptionHandler.java`):

- `404 Not Found` → recurso não encontrado (id inexistente)
- `400 Bad Request` → erro de validação dos campos enviados
- `500 Internal Server Error` → erro inesperado

Exemplo de resposta de erro de validação:

```json
{
  "timestamp": "2026-06-23T10:00:00",
  "status": 400,
  "erro": "Erro de validação",
  "mensagem": "Um ou mais campos são inválidos. Verifique os detalhes.",
  "validacoes": {
    "nome": "O nome do jogador é obrigatório"
  }
}
```
