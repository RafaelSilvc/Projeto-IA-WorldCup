import BotaoAcao from './BotaoAcao';

/**
 * Tabela genérica reutilizável.
 *
 * @param {Array} colunas - [{ chave: 'nome', titulo: 'Nome', render?: (item) => JSX }]
 * @param {Array} dados - lista de objetos a serem exibidos
 * @param {Function} onEditar - callback chamado com o item ao clicar em "Editar"
 * @param {Function} onExcluir - callback chamado com o item ao clicar em "Excluir"
 * @param {string} mensagemVazia - texto exibido quando não há dados
 */
function renderizarCelula(item, coluna) {
  if (coluna.render) return coluna.render(item);

  const valor = item[coluna.chave];
  if (valor === null || valor === undefined || valor === '') {
    return <span className="celula-vazia">—</span>;
  }

  return valor;
}

export default function Tabela({ colunas, dados, onEditar, onExcluir, mensagemVazia = 'Nenhum registro encontrado.' }) {
  if (!dados || dados.length === 0) {
    return (
      <div className="tabela-wrapper">
        <div className="tabela-vazia">
          <span className="tabela-vazia-icone">⚽</span>
          <p>{mensagemVazia}</p>
        </div>
      </div>
    );
  }

  return (
    <div className="tabela-wrapper">
      <table className="tabela">
        <thead>
          <tr>
            {colunas.map((coluna) => (
              <th key={coluna.chave}>{coluna.titulo}</th>
            ))}
            {(onEditar || onExcluir) && <th>Ações</th>}
          </tr>
        </thead>
        <tbody>
          {dados.map((item, indice) => (
            <tr key={item.id ?? indice}>
              {colunas.map((coluna) => (
                <td key={coluna.chave}>
                  {renderizarCelula(item, coluna)}
                </td>
              ))}
              {(onEditar || onExcluir) && (
                <td>
                  <div className="coluna-acoes">
                    {onEditar && (
                      <BotaoAcao variante="secundario" tamanho="sm" onClick={() => onEditar(item)}>
                        ✏️ Editar
                      </BotaoAcao>
                    )}
                    {onExcluir && (
                      <BotaoAcao variante="perigo" tamanho="sm" onClick={() => onExcluir(item)}>
                        🗑️ Excluir
                      </BotaoAcao>
                    )}
                  </div>
                </td>
              )}
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
