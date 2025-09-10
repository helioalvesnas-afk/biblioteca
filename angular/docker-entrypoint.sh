#!/bin/sh
set -e
# Gera runtime config para Angular (acessível via window.__env)
mkdir -p /usr/share/nginx/html/assets
: "${API_URL:=http://localhost:8080}"
cat > /usr/share/nginx/html/assets/env.js <<EOF
window.__env = {
	API_URL: "${API_URL}"
};
EOF
exec "$@"