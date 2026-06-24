import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

// Configuração do Vite: porta fixa 3000 para casar com o CORS liberado no backend.
export default defineConfig({
  plugins: [react()],
  server: {
    port: 3000,
  },
});
