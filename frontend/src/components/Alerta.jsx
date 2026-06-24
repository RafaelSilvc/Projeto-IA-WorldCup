/**
 * Mensagem de feedback visual (erro, sucesso ou informação).
 * tipo: 'erro' | 'sucesso' | 'info'
 */
export default function Alerta({ tipo = 'info', mensagem, onFechar }) {
  if (!mensagem) return null;

  const icones = {
    erro: '⚠️',
    sucesso: '✅',
    info: 'ℹ️',
  };

  return (
    <div className={`alerta alerta-${tipo}`}>
      <span>{icones[tipo] || icones.info}</span>
      <span style={{ flex: 1 }}>{mensagem}</span>
      {onFechar && (
        <button
          onClick={onFechar}
          style={{ background: 'none', border: 'none', cursor: 'pointer', fontWeight: 700 }}
          aria-label="Fechar mensagem"
        >
          ✕
        </button>
      )}
    </div>
  );
}
