import { useState, useEffect, useCallback } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import Tabela from '../components/Tabela';
import Spinner from '../components/Spinner';
import Alerta from '../components/Alerta';
import BotaoAcao from '../components/BotaoAcao';

/**
 * Página de listagem genérica, reaproveitada pelas entidades mais simples
 * (Países, Estádios, Bolas, Mascotes, Finais, Músicas), evitando duplicar
 * a lógica de carregamento/exclusão para cada uma.
 *
 * @param {Object} config - { titulo, icone, servico, colunas, rotaBase, campoBusca, mensagemExcluir }
 */
export default function GenericList({ config }) {
  const { titulo, icone, servico, colunas, rotaBase, campoBusca, mensagemExcluir } = config;

  const [itens, setItens] = useState([]);
  const [carregando, setCarregando] = useState(true);
  const [erro, setErro] = useState('');
  const [sucesso, setSucesso] = useState('');
  const [termoBusca, setTermoBusca] = useState('');
  const navegar = useNavigate();
  const localizacao = useLocation();

  const carregarItens = useCallback(async () => {
    setCarregando(true);
    setErro('');
    try {
      const dados = await servico.getAll();
      setItens(Array.isArray(dados) ? dados : []);
    } catch (e) {
      setErro(`Não foi possível carregar os dados de "${titulo}". Verifique se o backend está rodando em localhost:8080.`);
      setItens([]);
    } finally {
      setCarregando(false);
    }
  }, [servico, titulo]);

  // Recarrega ao trocar de entidade ou ao voltar da tela de edição/cadastro.
  useEffect(() => {
    setTermoBusca('');
    carregarItens();
  }, [rotaBase, localizacao.key, carregarItens]);

  async function excluirItem(item) {
    const confirmar = window.confirm(mensagemExcluir ? mensagemExcluir(item) : 'Tem certeza que deseja excluir este registro?');
    if (!confirmar) return;

    try {
      await servico.remove(item.id);
      setSucesso('Registro excluído com sucesso.');
      carregarItens();
    } catch (e) {
      setErro('Erro ao excluir o registro. Tente novamente.');
    }
  }

  // Filtro local simples pelo campo configurado (ex: 'nome' ou 'pais')
  const itensFiltrados = itens.filter((item) => {
    if (!termoBusca || !campoBusca) return true;
    const valor = item[campoBusca];
    return valor?.toString().toLowerCase().includes(termoBusca.toLowerCase());
  });

  return (
    <div>
      <div className="cabecalho-pagina">
        <h2>
          {icone} {titulo}
        </h2>
        <BotaoAcao variante="primario" onClick={() => navegar(`${rotaBase}/novo`)}>
          ➕ Novo Cadastro
        </BotaoAcao>
      </div>

      <Alerta tipo="erro" mensagem={erro} onFechar={() => setErro('')} />
      <Alerta tipo="sucesso" mensagem={sucesso} onFechar={() => setSucesso('')} />

      {campoBusca && (
        <div className="barra-busca">
          <input
            type="text"
            placeholder="🔍 Buscar..."
            value={termoBusca}
            onChange={(e) => setTermoBusca(e.target.value)}
          />
        </div>
      )}

      {carregando ? (
        <Spinner texto={`Carregando ${titulo.toLowerCase()}...`} />
      ) : (
        <Tabela
          colunas={colunas}
          dados={itensFiltrados}
          onEditar={(item) => navegar(`${rotaBase}/editar/${item.id}`)}
          onExcluir={excluirItem}
          mensagemVazia={`Nenhum registro de ${titulo.toLowerCase()} encontrado.`}
        />
      )}
    </div>
  );
}
