import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import campeaoService from '../services/campeaoService';
import Tabela from '../components/Tabela';
import Spinner from '../components/Spinner';
import Alerta from '../components/Alerta';
import BotaoAcao from '../components/BotaoAcao';

export default function CampeaoList() {
  const [campeoes, setCampeoes] = useState([]);
  const [carregando, setCarregando] = useState(true);
  const [erro, setErro] = useState('');
  const [sucesso, setSucesso] = useState('');
  const [termoBusca, setTermoBusca] = useState('');
  const navegar = useNavigate();

  useEffect(() => {
    carregarCampeoes();
  }, []);

  async function carregarCampeoes() {
    setCarregando(true);
    setErro('');
    try {
      const dados = await campeaoService.getAll();
      setCampeoes(dados);
    } catch (e) {
      setErro('Não foi possível carregar os campeões. Verifique se o backend está rodando em localhost:8080.');
    } finally {
      setCarregando(false);
    }
  }

  async function excluirCampeao(campeao) {
    const confirmar = window.confirm(`Excluir o título de campeão de "${campeao.pais}" em ${campeao.ano}?`);
    if (!confirmar) return;

    try {
      await campeaoService.remove(campeao.id);
      setSucesso('Registro de campeão excluído com sucesso.');
      carregarCampeoes();
    } catch (e) {
      setErro('Erro ao excluir o registro. Tente novamente.');
    }
  }

  const campeoesFiltrados = campeoes.filter((c) => {
    if (!termoBusca) return true;
    return c.pais?.toLowerCase().includes(termoBusca.toLowerCase());
  });

  const colunas = [
    { chave: 'pais', titulo: 'País Campeão' },
    { chave: 'ano', titulo: 'Ano' },
    { chave: 'tecnico', titulo: 'Técnico' },
    { chave: 'artilheiro', titulo: 'Artilheiro' },
  ];

  return (
    <div>
      <div className="cabecalho-pagina">
        <h2>🏆 Campeões</h2>
        <BotaoAcao variante="primario" onClick={() => navegar('/campeoes/novo')}>
          ➕ Novo Cadastro
        </BotaoAcao>
      </div>

      <Alerta tipo="erro" mensagem={erro} onFechar={() => setErro('')} />
      <Alerta tipo="sucesso" mensagem={sucesso} onFechar={() => setSucesso('')} />

      <div className="barra-busca">
        <input
          type="text"
          placeholder="🔍 Buscar por país..."
          value={termoBusca}
          onChange={(e) => setTermoBusca(e.target.value)}
        />
      </div>

      {carregando ? (
        <Spinner texto="Carregando campeões..." />
      ) : (
        <Tabela
          colunas={colunas}
          dados={campeoesFiltrados}
          onEditar={(item) => navegar(`/campeoes/editar/${item.id}`)}
          onExcluir={excluirCampeao}
          mensagemVazia="Nenhum campeão encontrado."
        />
      )}
    </div>
  );
}
