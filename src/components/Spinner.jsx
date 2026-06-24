/**
 * Indicador de carregamento exibido durante chamadas à API.
 */
export default function Spinner({ texto = 'Carregando...' }) {
  return (
    <div className="spinner-wrapper">
      <div className="spinner" />
      <span>{texto}</span>
    </div>
  );
}
