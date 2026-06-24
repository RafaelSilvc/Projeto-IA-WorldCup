import { criarServicoCrud } from './criarServicoCrud';

// Serviço de Bolas oficiais (/api/bolas)
const bolaService = criarServicoCrud('/bolas');

export default bolaService;
