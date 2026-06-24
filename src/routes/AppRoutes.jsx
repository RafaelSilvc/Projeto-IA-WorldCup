import { Routes, Route } from 'react-router-dom';

import Dashboard from '../pages/Dashboard';

import JogadorList from '../pages/JogadorList';
import JogadorForm from '../pages/JogadorForm';

import PartidaList from '../pages/PartidaList';
import PartidaForm from '../pages/PartidaForm';

import CampeaoList from '../pages/CampeaoList';
import CampeaoForm from '../pages/CampeaoForm';

import GenericList from '../pages/GenericList';
import GenericForm from '../pages/GenericForm';
import {
  configPais,
  configEstadio,
  configBola,
  configMascote,
  configFinal,
  configMusica,
} from '../pages/configEntidades';

/**
 * Define todas as rotas da aplicação.
 * Jogadores, Partidas e Campeões têm páginas próprias (CRUD completo e específico).
 * As demais entidades reaproveitam as páginas genéricas (GenericList/GenericForm).
 */
export default function AppRoutes() {
  return (
    <Routes>
      <Route path="/" element={<Dashboard />} />

      {/* Jogadores */}
      <Route path="/jogadores" element={<JogadorList />} />
      <Route path="/jogadores/novo" element={<JogadorForm />} />
      <Route path="/jogadores/editar/:id" element={<JogadorForm />} />

      {/* Partidas */}
      <Route path="/partidas" element={<PartidaList />} />
      <Route path="/partidas/novo" element={<PartidaForm />} />
      <Route path="/partidas/editar/:id" element={<PartidaForm />} />

      {/* Campeões */}
      <Route path="/campeoes" element={<CampeaoList />} />
      <Route path="/campeoes/novo" element={<CampeaoForm />} />
      <Route path="/campeoes/editar/:id" element={<CampeaoForm />} />

      {/* Países */}
      <Route path="/paises" element={<GenericList key="paises-list" config={configPais} />} />
      <Route path="/paises/novo" element={<GenericForm key="paises-novo" config={configPais} />} />
      <Route path="/paises/editar/:id" element={<GenericForm key="paises-form" config={configPais} />} />

      {/* Estádios */}
      <Route path="/estadios" element={<GenericList key="estadios-list" config={configEstadio} />} />
      <Route path="/estadios/novo" element={<GenericForm key="estadios-novo" config={configEstadio} />} />
      <Route path="/estadios/editar/:id" element={<GenericForm key="estadios-form" config={configEstadio} />} />

      {/* Bolas oficiais */}
      <Route path="/bolas" element={<GenericList key="bolas-list" config={configBola} />} />
      <Route path="/bolas/novo" element={<GenericForm key="bolas-novo" config={configBola} />} />
      <Route path="/bolas/editar/:id" element={<GenericForm key="bolas-form" config={configBola} />} />

      {/* Mascotes */}
      <Route path="/mascotes" element={<GenericList key="mascotes-list" config={configMascote} />} />
      <Route path="/mascotes/novo" element={<GenericForm key="mascotes-novo" config={configMascote} />} />
      <Route path="/mascotes/editar/:id" element={<GenericForm key="mascotes-form" config={configMascote} />} />

      {/* Finais */}
      <Route path="/finais" element={<GenericList key="finais-list" config={configFinal} />} />
      <Route path="/finais/novo" element={<GenericForm key="finais-novo" config={configFinal} />} />
      <Route path="/finais/editar/:id" element={<GenericForm key="finais-form" config={configFinal} />} />

      {/* Músicas tema */}
      <Route path="/musicas" element={<GenericList key="musicas-list" config={configMusica} />} />
      <Route path="/musicas/novo" element={<GenericForm key="musicas-novo" config={configMusica} />} />
      <Route path="/musicas/editar/:id" element={<GenericForm key="musicas-form" config={configMusica} />} />

      {/* Rota 404 simples */}
      <Route
        path="*"
        element={
          <div style={{ textAlign: 'center', padding: '60px 20px' }}>
            <h2>404 — Página não encontrada</h2>
            <p>O endereço acessado não existe neste sistema.</p>
          </div>
        }
      />
    </Routes>
  );
}
