import Navbar from './components/Navbar';
import AppRoutes from './routes/AppRoutes';

export default function App() {
  return (
    <div className="app-shell">
      <Navbar />
      <main className="conteudo-principal">
        <AppRoutes />
      </main>
      <footer className="rodape">
        Sistema Copa do Mundo 2026 — Projeto educacional Full Stack (React + Spring Boot)
      </footer>
    </div>
  );
}
