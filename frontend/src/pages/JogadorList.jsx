import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import jogadorService from '../services/jogadorService';
import Tabela from '../components/Tabela';
import Spinner from '../components/Spinner';
import Alerta from '../components/Alerta';
import BotaoAcao from '../components/BotaoAcao';

export default function JogadorList() {
  const [jogadores, setJogadores] = useState([]);
  const [carregando, setCarregando] = useState(true);
  const [erro, setErro] = useState('');
  const [sucesso, setSucesso] = useState('');
  const [termoBusca, setTermoBusca] = useState('');
  const navegar = useNavigate();

  // Carrega a lista de jogadores ao montar a página.
  useEffect(() => {
    carregarJogadores();
  }, []);

  async function carregarJogadores(busca = '') {
    setCarregando(true);
    setErro('');
    try {
      const dados = busca ? await jogadorService.buscar(busca) : await jogadorService.getAll();
      setJogadores(dados);
    } catch (e) {
      setErro('Não foi possível carregar os jogadores. Verifique se o backend está rodando em localhost:8080.');
    } finally {
      setCarregando(false);
    }
  }

  function lidarComBusca(evento) {
    const valor = evento.target.value;
    setTermoBusca(valor);
    carregarJogadores(valor);
  }

  async function excluirJogador(jogador) {
    const confirmar = window.confirm(`Tem certeza que deseja excluir o jogador "${jogador.nome}"?`);
    if (!confirmar) return;

    try {
      await jogadorService.remove(jogador.id);
      setSucesso(`Jogador "${jogador.nome}" excluído com sucesso.`);
      carregarJogadores(termoBusca);
    } catch (e) {
      setErro('Erro ao excluir o jogador. Tente novamente.');
    }
  }

  const colunas = [
    { chave: 'nome', titulo: 'Nome' },
    { chave: 'pais', titulo: 'País', render: (item) => <>{item.pais}</> },
    { chave: 'posicao', titulo: 'Posição' },
    { chave: 'numeroCamisa', titulo: 'Camisa' },
    { chave: 'totalGols', titulo: 'Gols' },
    { chave: 'copasDisputadas', titulo: 'Copas' },
  ];

  return (
    <div>
      <div className="cabecalho-pagina">
        <h2>⚽ Jogadores</h2>
        <BotaoAcao variante="primario" onClick={() => navegar('/jogadores/novo')}>
          ➕ Novo Cadastro
        </BotaoAcao>
      </div>

      <Alerta tipo="erro" mensagem={erro} onFechar={() => setErro('')} />
      <Alerta tipo="sucesso" mensagem={sucesso} onFechar={() => setSucesso('')} />

      <div className="barra-busca">
        <input
          type="text"
          placeholder="🔍 Buscar por nome ou país..."
          value={termoBusca}
          onChange={lidarComBusca}
        />
      </div>

      {carregando ? (
        <Spinner texto="Carregando jogadores..." />
      ) : (
        <Tabela
          colunas={colunas}
          dados={jogadores}
          onEditar={(item) => navegar(`/jogadores/editar/${item.id}`)}
          onExcluir={excluirJogador}
          mensagemVazia="Nenhum jogador encontrado."
        />
      )}
    </div>
  );
}
