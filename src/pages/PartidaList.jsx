import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import partidaService from '../services/partidaService';
import Tabela from '../components/Tabela';
import Spinner from '../components/Spinner';
import Alerta from '../components/Alerta';
import BotaoAcao from '../components/BotaoAcao';

export default function PartidaList() {
  const [partidas, setPartidas] = useState([]);
  const [carregando, setCarregando] = useState(true);
  const [erro, setErro] = useState('');
  const [sucesso, setSucesso] = useState('');
  const [termoBusca, setTermoBusca] = useState('');
  const navegar = useNavigate();

  useEffect(() => {
    carregarPartidas();
  }, []);

  async function carregarPartidas() {
    setCarregando(true);
    setErro('');
    try {
      const dados = await partidaService.getAll();
      setPartidas(dados);
    } catch (e) {
      setErro('Não foi possível carregar as partidas. Verifique se o backend está rodando em localhost:8080.');
    } finally {
      setCarregando(false);
    }
  }

  async function excluirPartida(partida) {
    const confirmar = window.confirm(
      `Excluir a partida ${partida.paisMandante} x ${partida.paisVisitante}?`
    );
    if (!confirmar) return;

    try {
      await partidaService.remove(partida.id);
      setSucesso('Partida excluída com sucesso.');
      carregarPartidas();
    } catch (e) {
      setErro('Erro ao excluir a partida. Tente novamente.');
    }
  }

  // Filtro local por país mandante ou visitante (a API de partidas não possui ?busca=).
  const partidasFiltradas = partidas.filter((p) => {
    if (!termoBusca) return true;
    const termo = termoBusca.toLowerCase();
    return (
      p.paisMandante?.toLowerCase().includes(termo) ||
      p.paisVisitante?.toLowerCase().includes(termo)
    );
  });

  const colunas = [
    {
      chave: 'confronto',
      titulo: 'Confronto',
      render: (item) => (
        <strong>
          {item.paisMandante} {item.golsMandante} x {item.golsVisitante} {item.paisVisitante}
        </strong>
      ),
    },
    { chave: 'fase', titulo: 'Fase', render: (item) => <span className="tag-fase">{item.fase}</span> },
    { chave: 'estadio', titulo: 'Estádio' },
    { chave: 'dataDaPartida', titulo: 'Data' },
  ];

  return (
    <div>
      <div className="cabecalho-pagina">
        <h2>🏟️ Partidas</h2>
        <BotaoAcao variante="primario" onClick={() => navegar('/partidas/novo')}>
          ➕ Novo Cadastro
        </BotaoAcao>
      </div>

      <Alerta tipo="erro" mensagem={erro} onFechar={() => setErro('')} />
      <Alerta tipo="sucesso" mensagem={sucesso} onFechar={() => setSucesso('')} />

      <div className="barra-busca">
        <input
          type="text"
          placeholder="🔍 Buscar por país mandante ou visitante..."
          value={termoBusca}
          onChange={(e) => setTermoBusca(e.target.value)}
        />
      </div>

      {carregando ? (
        <Spinner texto="Carregando partidas..." />
      ) : (
        <Tabela
          colunas={colunas}
          dados={partidasFiltradas}
          onEditar={(item) => navegar(`/partidas/editar/${item.id}`)}
          onExcluir={excluirPartida}
          mensagemVazia="Nenhuma partida encontrada."
        />
      )}
    </div>
  );
}
