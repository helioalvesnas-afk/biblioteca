// Lê valor de window.__env injetado pelo Nginx
export const runtimeEnv = (window as any).__env || { API_URL: 'http://localhost:8080' };
