import api from './api';

/**
 * Cria um conjunto padrão de funções CRUD para um recurso da API.
 * Evita repetição de código entre os arquivos de serviço de cada entidade.
 *
 * @param {string} recurso - caminho do recurso, ex: "/jogadores"
 */
export function criarServicoCrud(recurso) {
  return {
    // GET /recurso -> lista todos (aceita query params opcionais, ex: { busca: 'texto' })
    getAll: async (params = {}) => {
      const resposta = await api.get(recurso, { params });
      return resposta.data;
    },

    // GET /recurso/{id} -> busca um registro específico
    getById: async (id) => {
      const resposta = await api.get(`${recurso}/${id}`);
      return resposta.data;
    },

    // POST /recurso -> cria um novo registro
    create: async (dados) => {
      const resposta = await api.post(recurso, dados);
      return resposta.data;
    },

    // PUT /recurso/{id} -> edita um registro existente
    update: async (id, dados) => {
      const resposta = await api.put(`${recurso}/${id}`, dados);
      return resposta.data;
    },

    // DELETE /recurso/{id} -> remove um registro
    remove: async (id) => {
      await api.delete(`${recurso}/${id}`);
    },
  };
}
