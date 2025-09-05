# Etapa 1: Build do Angular
FROM node:20 AS build
WORKDIR /app

# Copia configs e instala dependências
COPY package*.json ./
RUN npm install

# Copia código e builda
COPY . .
RUN npm run build -- --configuration production

# Etapa 2: Servir com Nginx
FROM nginx:alpine
COPY --from=build /app/dist/* /usr/share/nginx/html

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
