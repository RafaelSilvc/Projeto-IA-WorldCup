import paisService from '../services/paisService';
import estadioService from '../services/estadioService';
import bolaService from '../services/bolaService';
import mascoteService from '../services/mascoteService';
import finalService from '../services/finalService';
import musicaService from '../services/musicaService';

/**
 * Configurações usadas pelas páginas GenericList/GenericForm.
 * Centraliza colunas de tabela, campos de formulário e textos por entidade,
 * evitando criar arquivos List/Form repetidos para cada recurso simples.
 */

export const configPais = {
  titulo: 'Países',
  icone: '🌎',
  servico: paisService,
  rotaBase: '/paises',
  campoBusca: 'nome',
  colunas: [
    { chave: 'nome', titulo: 'Nome' },
    { chave: 'sigla', titulo: 'Sigla', render: (item) => <span className="badge-sigla">{item.sigla}</span> },
    { chave: 'confederacao', titulo: 'Confederação', render: (item) => <span className="badge-confed">{item.confederacao}</span> },
  ],
  campos: [
    { nome: 'nome', label: 'Nome do país', tipo: 'text', obrigatorio: true },
    { nome: 'sigla', label: 'Sigla (3 letras)', tipo: 'text', obrigatorio: true },
    {
      nome: 'confederacao',
      label: 'Confederação',
      tipo: 'select',
      obrigatorio: true,
      opcoes: ['UEFA', 'CONMEBOL', 'CONCACAF', 'CAF', 'AFC', 'OFC'],
    },
  ],
  mensagemExcluir: (item) => `Excluir o país "${item.nome}"?`,
};

export const configEstadio = {
  titulo: 'Estádios',
  icone: '🏛️',
  servico: estadioService,
  rotaBase: '/estadios',
  campoBusca: 'nome',
  colunas: [
    { chave: 'nome', titulo: 'Nome' },
    { chave: 'cidade', titulo: 'Cidade' },
    { chave: 'paisSede', titulo: 'País sede' },
    { chave: 'capacidade', titulo: 'Capacidade', render: (item) => item.capacidade?.toLocaleString('pt-BR') },
  ],
  campos: [
    { nome: 'nome', label: 'Nome do estádio', tipo: 'text', obrigatorio: true },
    { nome: 'cidade', label: 'Cidade', tipo: 'text', obrigatorio: true },
    { nome: 'paisSede', label: 'País sede', tipo: 'text', obrigatorio: true },
    { nome: 'capacidade', label: 'Capacidade (lugares)', tipo: 'number', obrigatorio: true, min: 1 },
  ],
  mensagemExcluir: (item) => `Excluir o estádio "${item.nome}"?`,
};

export const configBola = {
  titulo: 'Bolas Oficiais',
  icone: '🔵',
  servico: bolaService,
  rotaBase: '/bolas',
  campoBusca: 'nomeOficial',
  colunas: [
    { chave: 'nomeOficial', titulo: 'Nome oficial' },
    { chave: 'anoCopa', titulo: 'Ano da copa' },
    { chave: 'fabricante', titulo: 'Fabricante' },
  ],
  campos: [
    { nome: 'nomeOficial', label: 'Nome oficial da bola', tipo: 'text', obrigatorio: true },
    { nome: 'anoCopa', label: 'Ano da copa', tipo: 'number', obrigatorio: true, min: 1930 },
    { nome: 'fabricante', label: 'Fabricante', tipo: 'text', obrigatorio: true },
  ],
  mensagemExcluir: (item) => `Excluir a bola "${item.nomeOficial}"?`,
};

export const configMascote = {
  titulo: 'Mascotes',
  icone: '🦸',
  servico: mascoteService,
  rotaBase: '/mascotes',
  campoBusca: 'nome',
  colunas: [
    { chave: 'nome', titulo: 'Nome' },
    { chave: 'anoCopa', titulo: 'Ano da copa' },
    { chave: 'paisSede', titulo: 'País sede' },
  ],
  campos: [
    { nome: 'nome', label: 'Nome do mascote', tipo: 'text', obrigatorio: true },
    { nome: 'anoCopa', label: 'Ano da copa', tipo: 'number', obrigatorio: true, min: 1930 },
    { nome: 'paisSede', label: 'País sede', tipo: 'text', obrigatorio: true },
  ],
  mensagemExcluir: (item) => `Excluir o mascote "${item.nome}"?`,
};

export const configFinal = {
  titulo: 'Finais',
  icone: '📜',
  servico: finalService,
  rotaBase: '/finais',
  campoBusca: 'paisCampeao',
  colunas: [
    { chave: 'anoCopa', titulo: 'Ano' },
    { chave: 'paisCampeao', titulo: 'Campeão' },
    { chave: 'paisVice', titulo: 'Vice' },
    {
      chave: 'placar',
      titulo: 'Placar',
      render: (item) => (
        <span className="badge-placar">
          {item.golsCampeao ?? 0} × {item.golsVice ?? 0}
        </span>
      ),
    },
    { chave: 'estadio', titulo: 'Estádio' },
  ],
  campos: [
    { nome: 'anoCopa', label: 'Ano da copa', tipo: 'number', obrigatorio: true, min: 1930 },
    { nome: 'paisCampeao', label: 'País campeão', tipo: 'text', obrigatorio: true },
    { nome: 'paisVice', label: 'País vice-campeão', tipo: 'text', obrigatorio: true },
    { nome: 'golsCampeao', label: 'Gols do campeão', tipo: 'number', obrigatorio: true, min: 0 },
    { nome: 'golsVice', label: 'Gols do vice', tipo: 'number', obrigatorio: true, min: 0 },
    { nome: 'estadio', label: 'Estádio da final', tipo: 'text', obrigatorio: true },
  ],
  mensagemExcluir: (item) => `Excluir a final de ${item.anoCopa}?`,
};

export const configMusica = {
  titulo: 'Músicas Tema',
  icone: '🎵',
  servico: musicaService,
  rotaBase: '/musicas',
  campoBusca: 'titulo',
  colunas: [
    { chave: 'titulo', titulo: 'Título' },
    { chave: 'artista', titulo: 'Artista' },
    { chave: 'anoCopa', titulo: 'Ano da copa' },
  ],
  campos: [
    { nome: 'titulo', label: 'Título da música', tipo: 'text', obrigatorio: true },
    { nome: 'artista', label: 'Artista(s)', tipo: 'text', obrigatorio: true },
    { nome: 'anoCopa', label: 'Ano da copa', tipo: 'number', obrigatorio: true, min: 1930 },
  ],
  mensagemExcluir: (item) => `Excluir a música "${item.titulo}"?`,
};
