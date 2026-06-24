import { Link } from 'react-router-dom';

/**
 * Página inicial: apresenta o sistema e atalhos visuais para cada entidade.
 */
export default function Dashboard() {
  const cards = [
    { to: '/jogadores', icone: '⚽', titulo: 'Jogadores', desc: 'Atletas, posições e estatísticas de gols.' },
    { to: '/partidas', icone: '🏟️', titulo: 'Partidas', desc: 'Resultados e fases do torneio.' },
    { to: '/campeoes', icone: '🏆', titulo: 'Campeões', desc: 'Países campeões e seus títulos.' },
    { to: '/paises', icone: '🌎', titulo: 'Países', desc: 'Seleções participantes e confederações.' },
    { to: '/estadios', icone: '🏛️', titulo: 'Estádios', desc: 'Sedes nos EUA, México e Canadá.' },
    { to: '/finais', icone: '📜', titulo: 'Finais', desc: 'Histórico das grandes decisões.' },
    { to: '/bolas', icone: '🔵', titulo: 'Bolas Oficiais', desc: 'As bolas de cada edição da copa.' },
    { to: '/mascotes', icone: '🦸', titulo: 'Mascotes', desc: 'Os mascotes de cada Copa do Mundo.' },
    { to: '/musicas', icone: '🎵', titulo: 'Músicas Tema', desc: 'As trilhas sonoras oficiais.' },
  ];

  return (
    <div>
      <section className="hero">
        <h1>Sistema Copa do Mundo 2026 🏆</h1>
        <p>
          Gerencie jogadores, partidas, estádios, campeões e toda a história da Copa do Mundo
          em um só lugar — integrado com a API Spring Boot do torneio.
        </p>
        <div className="faixa-paises">🇺🇸 🇲🇽 🇨🇦 — Sede da Copa 2026</div>
      </section>

      <h2 style={{ color: 'var(--azul-escuro)', marginBottom: 16 }}>Acesso rápido</h2>
      <div className="grid-cards">
        {cards.map((card) => (
          <Link to={card.to} className="card-entidade" key={card.to}>
            <span className="icone">{card.icone}</span>
            <h3>{card.titulo}</h3>
            <p>{card.desc}</p>
          </Link>
        ))}
      </div>
    </div>
  );
}
