import { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import partidaService from '../services/partidaService';
import Formulario from '../components/Formulario';
import Spinner from '../components/Spinner';
import Alerta from '../components/Alerta';

const campos = [
  { nome: 'paisMandante', label: 'País mandante', tipo: 'text', obrigatorio: true },
  { nome: 'paisVisitante', label: 'País visitante', tipo: 'text', obrigatorio: true },
  { nome: 'golsMandante', label: 'Gols do mandante', tipo: 'number', obrigatorio: true, min: 0 },
  { nome: 'golsVisitante', label: 'Gols do visitante', tipo: 'number', obrigatorio: true, min: 0 },
  {
    nome: 'fase',
    label: 'Fase',
    tipo: 'select',
    obrigatorio: true,
    opcoes: ['GRUPO', 'OITAVAS', 'QUARTAS', 'SEMI', 'FINAL'],
  },
  { nome: 'estadio', label: 'Estádio', tipo: 'text', obrigatorio: true },
  { nome: 'dataDaPartida', label: 'Data da partida', tipo: 'date', obrigatorio: true },
];

export default function PartidaForm() {
  const { id } = useParams();
  const navegar = useNavigate();
  const emEdicao = Boolean(id);

  const [valoresIniciais, setValoresIniciais] = useState({});
  const [carregando, setCarregando] = useState(emEdicao);
  const [salvando, setSalvando] = useState(false);
  const [erro, setErro] = useState('');

  useEffect(() => {
    if (!emEdicao) return;

    async function carregarPartida() {
      try {
        const dados = await partidaService.getById(id);
        setValoresIniciais(dados);
      } catch (e) {
        setErro('Não foi possível carregar os dados da partida.');
      } finally {
        setCarregando(false);
      }
    }
    carregarPartida();
  }, [id, emEdicao]);

  async function salvar(dados) {
    setSalvando(true);
    setErro('');
    try {
      if (emEdicao) {
        await partidaService.update(id, dados);
      } else {
        await partidaService.create(dados);
      }
      navegar('/partidas');
    } catch (e) {
      const mensagemApi = e.response?.data?.mensagem;
      setErro(mensagemApi || 'Erro ao salvar a partida. Verifique os dados e tente novamente.');
    } finally {
      setSalvando(false);
    }
  }

  if (carregando) return <Spinner texto="Carregando dados da partida..." />;

  return (
    <div>
      <div className="cabecalho-pagina">
        <h2>{emEdicao ? '✏️ Editar Partida' : '➕ Nova Partida'}</h2>
      </div>

      <Alerta tipo="erro" mensagem={erro} onFechar={() => setErro('')} />

      <Formulario
        campos={campos}
        valoresIniciais={valoresIniciais}
        onSalvar={salvar}
        onCancelar={() => navegar('/partidas')}
        salvando={salvando}
      />
    </div>
  );
}
