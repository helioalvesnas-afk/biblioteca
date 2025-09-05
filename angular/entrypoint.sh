#!/bin/sh
# Sobrescreve API_URL no config.json com a variável do docker-compose
if [ -n "$API_URL" ]; then
  echo "Atualizando API_URL para $API_URL"
  sed -i "s|\"apiUrl\": \".*\"|\"apiUrl\": \"$API_URL\"|g" /usr/share/nginx/html/assets/config.json
fi

# Iniciar o Nginx
exec "$@"
