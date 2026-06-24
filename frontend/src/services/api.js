import axios from 'axios';

/**
 * Instância centralizada do axios.
 * Todas as chamadas à API do backend Spring Boot passam por aqui,
 * garantindo baseURL, headers e timeout consistentes em todo o app.
 */
const api = axios.create({
  baseURL: 'http://localhost:8080/api',
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json',
  },
});

export default api;
