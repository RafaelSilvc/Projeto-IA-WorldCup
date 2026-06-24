import { useState } from 'react';
import { NavLink } from 'react-router-dom';

/**
 * Menu de navegação principal, fixo no topo, com link para todas as entidades.
 */
export default function Navbar() {
  const [menuAberto, setMenuAberto] = useState(false);

  const links = [
    { to: '/', label: 'Início', icone: '🏠' },
    { to: '/jogadores', label: 'Jogadores', icone: '⚽' },
    { to: '/partidas', label: 'Partidas', icone: '🏟️' },
    { to: '/campeoes', label: 'Campeões', icone: '🏆' },
    { to: '/paises', label: 'Países', icone: '🌎' },
    { to: '/estadios', label: 'Estádios', icone: '🏛️' },
    { to: '/finais', label: 'Finais', icone: '📜' },
    { to: '/bolas', label: 'Bolas', icone: '🔵' },
    { to: '/mascotes', label: 'Mascotes', icone: '🦸' },
    { to: '/musicas', label: 'Músicas', icone: '🎵' },
  ];

  function fecharMenu() {
    setMenuAberto(false);
  }

  return (
    <header className="navbar">
      <div className="navbar-inner">
        <NavLink to="/" className="navbar-marca" onClick={fecharMenu}>
          <span className="bola">⚽</span>
          <div className="navbar-marca-texto">
            <span className="navbar-titulo">Copa 2026</span>
            <span className="navbar-subtitulo">World Cup Hub</span>
          </div>
        </NavLink>

        <button
          type="button"
          className="navbar-toggle"
          aria-label={menuAberto ? 'Fechar menu' : 'Abrir menu'}
          aria-expanded={menuAberto}
          onClick={() => setMenuAberto((anterior) => !anterior)}
        >
          <span />
          <span />
          <span />
        </button>

        <nav className={`navbar-links ${menuAberto ? 'aberto' : ''}`}>
          {links.map((link) => (
            <NavLink
              key={link.to}
              to={link.to}
              className={({ isActive }) => (isActive ? 'ativo' : '')}
              end={link.to === '/'}
              onClick={fecharMenu}
            >
              <span className="nav-icone">{link.icone}</span>
              {link.label}
            </NavLink>
          ))}
        </nav>
      </div>
    </header>
  );
}
