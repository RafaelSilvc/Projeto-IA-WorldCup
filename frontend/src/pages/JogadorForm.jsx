import { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import jogadorService from '../services/jogadorService';
import Formulario from '../components/Formulario';
import Spinner from '../components/Spinner';
import Alerta from '../components/Alerta';

const campos = [
  { nome: 'nome', label: 'Nome do jogador', tipo: 'text', obrigatorio: true },
  { nome: 'pais', label: 'País', tipo: 'text', obrigatorio: true },
  {
    nome: 'posicao',
    label: 'Posição',
    tipo: 'select',
    obrigatorio: true,
    opcoes: ['Goleiro', 'Zagueiro', 'Lateral', 'Meio-campo', 'Atacante'],
  },
  { nome: 'numeroCamisa', label: 'Número da camisa', tipo: 'number', obrigatorio: true, min: 1 },
  { nome: 'totalGols', label: 'Total de gols', tipo: 'number', obrigatorio: true, min: 0 },
  { nome: 'copasDisputadas', label: 'Copas disputadas', tipo: 'number', obrigatorio: true, min: 0 },
];

export default function JogadorForm() {
  const { id } = useParams(); // presente apenas na edição
  const navegar = useNavigate();
  const emEdicao = Boolean(id);

  const [valoresIniciais, setValoresIniciais] = useState({});
  const [carregando, setCarregando] = useState(emEdicao);
  const [salvando, setSalvando] = useState(false);
  const [erro, setErro] = useState('');

  // Se estamos editando, busca os dados do jogador para preencher o formulário.
  useEffect(() => {
    if (!emEdicao) return;

    async function carregarJogador() {
      try {
        const dados = await jogadorService.getById(id);
        setValoresIniciais(dados);
      } catch (e) {
        setErro('Não foi possível carregar os dados do jogador.');
      } finally {
        setCarregando(false);
      }
    }
    carregarJogador();
  }, [id, emEdicao]);

  async function salvar(dados) {
    setSalvando(true);
    setErro('');
    try {
      if (emEdicao) {
        await jogadorService.update(id, dados);
      } else {
        await jogadorService.create(dados);
      }
      navegar('/jogadores');
    } catch (e) {
      const mensagemApi = e.response?.data?.mensagem;
      setErro(mensagemApi || 'Erro ao salvar o jogador. Verifique os dados e tente novamente.');
    } finally {
      setSalvando(false);
    }
  }

  if (carregando) return <Spinner texto="Carregando dados do jogador..." />;

  return (
    <div>
      <div className="cabecalho-pagina">
        <h2>{emEdicao ? '✏️ Editar Jogador' : '➕ Novo Jogador'}</h2>
      </div>

      <Alerta tipo="erro" mensagem={erro} onFechar={() => setErro('')} />

      <Formulario
        campos={campos}
        valoresIniciais={valoresIniciais}
        onSalvar={salvar}
        onCancelar={() => navegar('/jogadores')}
        salvando={salvando}
      />
    </div>
  );
}
