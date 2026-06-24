/**
 * Botão de ação estilizado e reutilizável.
 * variante: 'primario' | 'secundario' | 'perigo' | 'aviso' | 'ghost'
 */
export default function BotaoAcao({
  children,
  variante = 'primario',
  tamanho = '',
  onClick,
  type = 'button',
  disabled = false,
}) {
  const classes = `botao botao-${variante} ${tamanho === 'sm' ? 'botao-sm' : ''}`.trim();

  return (
    <button type={type} className={classes} onClick={onClick} disabled={disabled}>
      {children}
    </button>
  );
}
