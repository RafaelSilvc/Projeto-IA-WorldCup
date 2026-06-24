import { criarServicoCrud } from './criarServicoCrud';

// Serviço de Mascotes oficiais (/api/mascotes)
const mascoteService = criarServicoCrud('/mascotes');

export default mascoteService;
