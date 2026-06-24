import { Link } from 'react-router-dom';

/**
 * Página inicial: apresenta o sistema e atalhos visuais para cada entidade.
 */
export default function Dashboard() {
  const cards = [
    { to: '/jogadores', icone: '⚽', titulo: 'Jogadores', desc: 'Atletas, posições e estatísticas de gols.', cor: 'verde' },
    { to: '/partidas', icone: '🏟️', titulo: 'Partidas', desc: 'Resultados e fases do torneio.', cor: 'azul' },
    { to: '/campeoes', icone: '🏆', titulo: 'Campeões', desc: 'Países campeões e seus títulos.', cor: 'ouro' },
    { to: '/paises', icone: '🌎', titulo: 'Países', desc: 'Seleções participantes e confederações.', cor: 'azul' },
    { to: '/estadios', icone: '🏛️', titulo: 'Estádios', desc: 'Sedes nos EUA, México e Canadá.', cor: 'verde' },
    { to: '/finais', icone: '📜', titulo: 'Finais', desc: 'Histórico das grandes decisões.', cor: 'ouro' },
    { to: '/bolas', icone: '🔵', titulo: 'Bolas Oficiais', desc: 'As bolas de cada edição da copa.', cor: 'azul' },
    { to: '/mascotes', icone: '🦸', titulo: 'Mascotes', desc: 'Os mascotes de cada Copa do Mundo.', cor: 'verde' },
    { to: '/musicas', icone: '🎵', titulo: 'Músicas Tema', desc: 'As trilhas sonoras oficiais.', cor: 'ouro' },
  ];

  return (
    <div className="dashboard">
      <section className="hero">
        <div className="hero-conteudo">
          <span className="hero-badge">FIFA World Cup 2026</span>
          <h1>A maior Copa do Mundo</h1>
          <p>
            Gerencie jogadores, partidas, estádios, campeões e toda a história da Copa
            em um só lugar — com visual inspirado no futebol moderno.
          </p>
          <div className="hero-acoes">
            <Link to="/partidas" className="botao botao-primario botao-hero">
              Ver partidas
            </Link>
            <Link to="/jogadores" className="botao botao-ghost botao-hero-ghost">
              Explorar jogadores
            </Link>
          </div>
          <div className="faixa-paises">
            <span>🇺🇸</span>
            <span>🇲🇽</span>
            <span>🇨🇦</span>
            <span className="faixa-texto">Sede da Copa 2026</span>
          </div>
        </div>
        <div className="hero-visual" aria-hidden="true">
          <div className="hero-bola">⚽</div>
          <div className="hero-circulo hero-circulo-1" />
          <div className="hero-circulo hero-circulo-2" />
        </div>
      </section>

      <section className="secao-dashboard">
        <div className="secao-cabecalho">
          <h2>Acesso rápido</h2>
          <p>Navegue pelas seções do torneio</p>
        </div>
        <div className="grid-cards">
          {cards.map((card) => (
            <Link to={card.to} className={`card-entidade card-${card.cor}`} key={card.to}>
              <span className="icone">{card.icone}</span>
              <h3>{card.titulo}</h3>
              <p>{card.desc}</p>
              <span className="card-seta">→</span>
            </Link>
          ))}
        </div>
      </section>
    </div>
  );
}
