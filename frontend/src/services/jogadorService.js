import { criarServicoCrud } from './criarServicoCrud';

/**
 * Serviço de Jogadores.
 * O backend aceita o parâmetro ?busca=texto em GET /api/jogadores
 * para filtrar por nome ou país (ver JogadorController.java).
 */
const servicoBase = criarServicoCrud('/jogadores');

const jogadorService = {
  ...servicoBase,
  buscar: (termo) => servicoBase.getAll({ busca: termo }),
};

export default jogadorService;
