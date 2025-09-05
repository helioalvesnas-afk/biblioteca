# library-backend-final

Spring Boot 3 project scaffold with JWT authentication, Swagger and CRUD for Genre/Author/Book.

## Run

1. Start DB: `docker compose up -d`
2. Run app: `./mvnw spring-boot:run` or `mvn spring-boot:run`
3. Swagger: http://localhost:8080/swagger-ui/index.html

## Notes
- Change `app.jwt.secret` in application.yml to a secure Base64 secret for production.
- Default DB creds in docker-compose: admin / admin123
