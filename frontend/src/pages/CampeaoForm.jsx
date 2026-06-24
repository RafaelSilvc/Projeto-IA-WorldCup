import { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import campeaoService from '../services/campeaoService';
import Formulario from '../components/Formulario';
import Spinner from '../components/Spinner';
import Alerta from '../components/Alerta';

const campos = [
  { nome: 'pais', label: 'País campeão', tipo: 'text', obrigatorio: true },
  { nome: 'ano', label: 'Ano', tipo: 'number', obrigatorio: true, min: 1930 },
  { nome: 'tecnico', label: 'Técnico', tipo: 'text', obrigatorio: true },
  { nome: 'artilheiro', label: 'Artilheiro da campanha', tipo: 'text', obrigatorio: true },
];

export default function CampeaoForm() {
  const { id } = useParams();
  const navegar = useNavigate();
  const emEdicao = Boolean(id);

  const [valoresIniciais, setValoresIniciais] = useState({});
  const [carregando, setCarregando] = useState(emEdicao);
  const [salvando, setSalvando] = useState(false);
  const [erro, setErro] = useState('');

  useEffect(() => {
    if (!emEdicao) return;

    async function carregarCampeao() {
      try {
        const dados = await campeaoService.getById(id);
        setValoresIniciais(dados);
      } catch (e) {
        setErro('Não foi possível carregar os dados do campeão.');
      } finally {
        setCarregando(false);
      }
    }
    carregarCampeao();
  }, [id, emEdicao]);

  async function salvar(dados) {
    setSalvando(true);
    setErro('');
    try {
      if (emEdicao) {
        await campeaoService.update(id, dados);
      } else {
        await campeaoService.create(dados);
      }
      navegar('/campeoes');
    } catch (e) {
      const mensagemApi = e.response?.data?.mensagem;
      setErro(mensagemApi || 'Erro ao salvar o campeão. Verifique os dados e tente novamente.');
    } finally {
      setSalvando(false);
    }
  }

  if (carregando) return <Spinner texto="Carregando dados do campeão..." />;

  return (
    <div>
      <div className="cabecalho-pagina">
        <h2>{emEdicao ? '✏️ Editar Campeão' : '➕ Novo Campeão'}</h2>
      </div>

      <Alerta tipo="erro" mensagem={erro} onFechar={() => setErro('')} />

      <Formulario
        campos={campos}
        valoresIniciais={valoresIniciais}
        onSalvar={salvar}
        onCancelar={() => navegar('/campeoes')}
        salvando={salvando}
      />
    </div>
  );
}
