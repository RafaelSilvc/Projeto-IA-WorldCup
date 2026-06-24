import { criarServicoCrud } from './criarServicoCrud';

// Serviço de Músicas tema (/api/musicas)
const musicaService = criarServicoCrud('/musicas');

export default musicaService;
