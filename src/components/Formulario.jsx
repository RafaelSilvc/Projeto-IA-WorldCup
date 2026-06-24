import { useState, useEffect } from 'react';
import BotaoAcao from './BotaoAcao';

/**
 * Formulário genérico de cadastro/edição.
 *
 * @param {Array} campos - [{ nome, label, tipo: 'text'|'number'|'date'|'select', opcoes?, obrigatorio? }]
 * @param {Object} valoresIniciais - valores para edição (vazio = novo cadastro)
 * @param {Function} onSalvar - callback chamado com os dados validados ao submeter
 * @param {Function} onCancelar - callback chamado ao clicar em "Cancelar"
 * @param {boolean} salvando - desabilita o botão enquanto a requisição está em andamento
 */
function montarValoresIniciais(campos, valoresIniciais) {
  const inicial = {};
  campos.forEach((campo) => {
    const valor = valoresIniciais[campo.nome];
    inicial[campo.nome] = valor === null || valor === undefined ? '' : valor;
  });
  return inicial;
}

export default function Formulario({ campos, valoresIniciais = {}, onSalvar, onCancelar, salvando = false }) {
  const [valores, setValores] = useState(() => montarValoresIniciais(campos, valoresIniciais));
  const [erros, setErros] = useState({});

  useEffect(() => {
    setValores(montarValoresIniciais(campos, valoresIniciais));
    setErros({});
  }, [valoresIniciais, campos]);

  function lidarComMudanca(nome, valor) {
    setValores((anterior) => ({ ...anterior, [nome]: valor }));
    // Limpa o erro do campo assim que o usuário começa a corrigir
    if (erros[nome]) {
      setErros((anterior) => ({ ...anterior, [nome]: undefined }));
    }
  }

  // Validação básica: campos obrigatórios vazios e campos numéricos com valor inválido.
  function validar() {
    const novosErros = {};
    campos.forEach((campo) => {
      const valor = valores[campo.nome];
      const vazio = valor === '' || valor === null || valor === undefined;

      if (campo.obrigatorio && vazio) {
        novosErros[campo.nome] = `O campo "${campo.label}" é obrigatório.`;
        return;
      }
      if (campo.tipo === 'number' && !vazio && isNaN(Number(valor))) {
        novosErros[campo.nome] = `O campo "${campo.label}" deve ser um número.`;
      }
      if (campo.tipo === 'number' && !vazio && campo.min !== undefined && Number(valor) < campo.min) {
        novosErros[campo.nome] = `O campo "${campo.label}" deve ser maior ou igual a ${campo.min}.`;
      }
    });
    setErros(novosErros);
    return Object.keys(novosErros).length === 0;
  }

  function lidarComSubmit(evento) {
    evento.preventDefault();
    if (!validar()) return;

    // Converte campos numéricos de string para number antes de enviar à API
    const dadosFinais = { ...valores };
    campos.forEach((campo) => {
      if (campo.tipo === 'number' && dadosFinais[campo.nome] !== '') {
        dadosFinais[campo.nome] = Number(dadosFinais[campo.nome]);
      }
    });

    onSalvar(dadosFinais);
  }

  return (
    <form className="formulario-card" onSubmit={lidarComSubmit} noValidate>
      {campos.map((campo) => (
        <div className="campo-formulario" key={campo.nome}>
          <label htmlFor={campo.nome}>
            {campo.label} {campo.obrigatorio && '*'}
          </label>

          {campo.tipo === 'select' ? (
            <select
              id={campo.nome}
              className={erros[campo.nome] ? 'com-erro' : ''}
              value={valores[campo.nome] ?? ''}
              onChange={(e) => lidarComMudanca(campo.nome, e.target.value)}
            >
              <option value="">Selecione...</option>
              {campo.opcoes.map((opcao) => (
                <option key={opcao} value={opcao}>
                  {opcao}
                </option>
              ))}
            </select>
          ) : (
            <input
              id={campo.nome}
              type={campo.tipo || 'text'}
              className={erros[campo.nome] ? 'com-erro' : ''}
              value={valores[campo.nome] ?? ''}
              onChange={(e) => lidarComMudanca(campo.nome, e.target.value)}
              placeholder={campo.placeholder || ''}
            />
          )}

          {erros[campo.nome] && <div className="mensagem-erro-campo">{erros[campo.nome]}</div>}
        </div>
      ))}

      <div className="acoes-formulario">
        <BotaoAcao type="submit" variante="primario" disabled={salvando}>
          {salvando ? 'Salvando...' : '💾 Salvar'}
        </BotaoAcao>
        <BotaoAcao variante="ghost" onClick={onCancelar} disabled={salvando}>
          Cancelar
        </BotaoAcao>
      </div>
    </form>
  );
}
