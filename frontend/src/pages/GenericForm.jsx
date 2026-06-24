import { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import Formulario from '../components/Formulario';
import Spinner from '../components/Spinner';
import Alerta from '../components/Alerta';

/**
 * Formulário de cadastro/edição genérico, reaproveitado pelas entidades
 * mais simples do sistema.
 *
 * @param {Object} config - { titulo, servico, campos, rotaBase }
 */
export default function GenericForm({ config }) {
  const { titulo, servico, campos, rotaBase } = config;
  const { id } = useParams();
  const navegar = useNavigate();
  const emEdicao = Boolean(id);

  const [valoresIniciais, setValoresIniciais] = useState({});
  const [carregando, setCarregando] = useState(emEdicao);
  const [salvando, setSalvando] = useState(false);
  const [erro, setErro] = useState('');

  useEffect(() => {
    setErro('');
    setValoresIniciais({});

    if (!emEdicao) {
      setCarregando(false);
      return;
    }

    let cancelado = false;

    async function carregarItem() {
      setCarregando(true);
      try {
        const dados = await servico.getById(id);
        if (!cancelado) setValoresIniciais(dados);
      } catch (e) {
        if (!cancelado) setErro('Não foi possível carregar os dados do registro.');
      } finally {
        if (!cancelado) setCarregando(false);
      }
    }

    carregarItem();
    return () => {
      cancelado = true;
    };
  }, [id, emEdicao, servico, rotaBase]);

  async function salvar(dados) {
    setSalvando(true);
    setErro('');
    try {
      if (emEdicao) {
        await servico.update(id, dados);
      } else {
        await servico.create(dados);
      }
      navegar(rotaBase);
    } catch (e) {
      const mensagemApi = e.response?.data?.mensagem;
      setErro(mensagemApi || 'Erro ao salvar o registro. Verifique os dados e tente novamente.');
    } finally {
      setSalvando(false);
    }
  }

  if (carregando) return <Spinner texto="Carregando dados..." />;

  return (
    <div>
      <div className="cabecalho-pagina">
        <h2>{emEdicao ? `✏️ Editar ${titulo}` : `➕ Novo registro: ${titulo}`}</h2>
      </div>

      <Alerta tipo="erro" mensagem={erro} onFechar={() => setErro('')} />

      <Formulario
        key={emEdicao ? `${rotaBase}-${id}` : `${rotaBase}-novo`}
        campos={campos}
        valoresIniciais={valoresIniciais}
        onSalvar={salvar}
        onCancelar={() => navegar(rotaBase)}
        salvando={salvando}
      />
    </div>
  );
}
