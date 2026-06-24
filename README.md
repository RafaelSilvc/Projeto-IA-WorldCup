# Sistema Copa do Mundo 2026 — Frontend (React)

Aplicação React responsável pela interface do sistema de gestão da Copa do Mundo FIFA 2026, consumindo a API REST Spring Boot via `axios`.

Visual com tema da Copa: gradientes em verde-campo, azul-celeste e amarelo-ouro, cards, tabelas estilizadas e emojis temáticos (⚽🏆🌎) no lugar de imagens externas — sem dependência de assets pesados ou direitos de imagem.

## 🚀 Como rodar o projeto

### Pré-requisitos
- Node.js 18+ e npm
- **Backend rodando em `http://localhost:8080`** (ver projeto `/backend`)

### Passos

```bash
# 1. Entre na pasta do frontend
cd frontend

# 2. Instale as dependências
npm install

# 3. Rode em modo desenvolvimento
npm run dev
```

A aplicação abrirá em:

```
http://localhost:3000
```

> ⚠️ Importante: o backend deve estar rodando em `http://localhost:8080` antes de abrir o frontend, pois o CORS do Spring Boot está liberado especificamente para `http://localhost:3000`.

## 📁 Estrutura de pastas

```
frontend/
└── src/
    ├── components/      -> Tabela, Formulario, BotaoAcao, Spinner, Alerta, Navbar
    ├── pages/           -> Páginas de cada entidade (List/Form) + páginas genéricas
    ├── services/        -> api.js (axios) + um arquivo de serviço por entidade
    ├── routes/          -> AppRoutes.jsx (todas as rotas da aplicação)
    ├── styles/          -> global.css (tema visual da Copa)
    └── App.jsx
```

## 🧩 Entidades implementadas

| Entidade        | CRUD completo | Página própria             |
|------------------|---------------|------------------------------|
| Jogadores        | ✅            | JogadorList / JogadorForm   |
| Partidas         | ✅            | PartidaList / PartidaForm   |
| Campeões         | ✅            | CampeaoList / CampeaoForm   |
| Países           | ✅            | Página genérica (config)    |
| Estádios         | ✅            | Página genérica (config)    |
| Bolas oficiais   | ✅            | Página genérica (config)    |
| Mascotes         | ✅            | Página genérica (config)    |
| Finais           | ✅            | Página genérica (config)    |
| Músicas tema     | ✅            | Página genérica (config)    |

As entidades mais simples reaproveitam os componentes `GenericList.jsx` e `GenericForm.jsx`, configurados em `src/pages/configEntidades.js`, evitando duplicação de código.

## 🔌 Integração com a API

A baseURL do axios está definida em `src/services/api.js`:

```js
baseURL: 'http://localhost:8080/api'
```

Cada entidade possui seu próprio arquivo de serviço (`jogadorService.js`, `partidaService.js`, `campeaoService.js` etc.), todos construídos a partir da factory `criarServicoCrud.js`, que expõe:

```js
getAll(params)
getById(id)
create(dados)
update(id, dados)
remove(id)
```

## ✅ Funcionalidades

- Listagem em tabela com botões **Editar** / **Excluir**
- Botão **Novo Cadastro** em todas as entidades
- Formulário único e reutilizável (`Formulario.jsx`) com validação básica (campos obrigatórios, tipos numéricos)
- Campo de busca por nome/país (via API para Jogadores, filtro local para as demais)
- Spinner de carregamento durante chamadas à API
- Alertas de erro e sucesso
- Confirmação via `window.confirm` antes de excluir qualquer registro
- Tratamento de erros de requisição em todas as telas (nunca fica em branco)

## 🛠️ Build para produção

```bash
npm run build
npm run preview
```
