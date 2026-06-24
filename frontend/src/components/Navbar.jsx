import { NavLink } from 'react-router-dom';

/**
 * Menu de navegação principal, fixo no topo, com link para todas as entidades.
 */
export default function Navbar() {
  const links = [
    { to: '/', label: '🏠 Início' },
    { to: '/jogadores', label: '⚽ Jogadores' },
    { to: '/partidas', label: '🏟️ Partidas' },
    { to: '/campeoes', label: '🏆 Campeões' },
    { to: '/paises', label: '🌎 Países' },
    { to: '/estadios', label: '🏛️ Estádios' },
    { to: '/finais', label: '📜 Finais' },
    { to: '/bolas', label: '🔵 Bolas' },
    { to: '/mascotes', label: '🦸 Mascotes' },
    { to: '/musicas', label: '🎵 Músicas' },
  ];

  return (
    <header className="navbar">
      <div className="navbar-inner">
        <NavLink to="/" className="navbar-marca">
          <span className="bola">⚽</span>
          <span>Copa 2026</span>
        </NavLink>
        <nav className="navbar-links">
          {links.map((link) => (
            <NavLink
              key={link.to}
              to={link.to}
              className={({ isActive }) => (isActive ? 'ativo' : '')}
              end={link.to === '/'}
            >
              {link.label}
            </NavLink>
          ))}
        </nav>
      </div>
    </header>
  );
}
