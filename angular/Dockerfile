# Etapa de build
FROM node:20-alpine AS build
WORKDIR /app

# Instala dependências primeiro para aproveitar cache
COPY package*.json ./
RUN npm install

# Copia código
COPY . .
# Build de produção
RUN npm run build -- --configuration=production

# Etapa de runtime com Nginx
FROM nginx:alpine

# Copia build para Nginx
COPY --from=build /app/dist/biblioteca/* /usr/share/nginx/html/

# Copia config SPA + entrypoint
COPY nginx.conf /etc/nginx/conf.d/default.conf
COPY docker-entrypoint.sh /docker-entrypoint.sh
RUN chmod +x /docker-entrypoint.sh

# assets/env.js será gerado no entrypoint via variável de ambiente API_URL
ENTRYPOINT ["/docker-entrypoint.sh"]
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
