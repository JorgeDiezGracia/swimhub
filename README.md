🏊 SwimHub — API REST de Natación Federada Española


Trabajo de Fin de Grado Superior · CFGS DAM · Centro San Valero · Curso 2025/2026





📋 Descripción

SwimHub es una API REST de solo lectura que centraliza y estandariza el acceso a los datos de la natación federada española. Modela la estructura organizativa real del deporte: Comunidad Autónoma → Federación → Liga → Club → Nadador.

Desarrollada con Java 21 + Spring Boot 3.5, implementa seguridad JWT, paginación, filtros avanzados, un módulo de análisis de tendencias de rendimiento deportivo y gestión mediante API Gateway (APIMan).


🚀 Demo en AWS

La API está desplegada en AWS EC2 y accesible públicamente:

RecursoURLSwagger UIhttp://18.215.149.98:8081/swagger-ui/index.htmlAPI Base URLhttp://18.215.149.98:8081/apiLoginPOST /api/auth/login


Credenciales de prueba: admin / admin123




🛠️ Stack Tecnológico

CapaTecnologíaBackendJava 21, Spring Boot 3.5, Spring Security, Spring Data JPASeguridadJWT (jjwt 0.12.6)Base de datosMariaDB 11, Flyway, H2 (tests)DocumentaciónSwagger / OpenAPI 3.0 (springdoc-openapi 2.5.0)API GatewayAPIMan 3.1.3 + Keycloak 21InfraestructuraDocker, Docker Compose, AWS EC2 (t2.micro)TestingJUnit 5, Mockito, MockMvc — 77 testsControl de versionesGit + GitFlow


📡 Endpoints principales

Autenticación

MétodoEndpointDescripciónPOST/api/auth/loginObtener token JWT

Estructura organizativa

MétodoEndpointDescripciónGET/api/communitiesComunidades autónomasGET/api/federationsFederaciones (paginado)GET/api/federations/{id}/clubsClubes de una federaciónGET/api/federations/{id}/eventsEventos de una federaciónGET/api/leaguesLigas (paginado)GET/api/clubsClubes (paginado)GET/api/clubs/{id}/swimmersNadadores de un club

Nadadores y competición

MétodoEndpointDescripciónGET/api/swimmersNadadores con filtros (paginado)GET/api/swimmers/{id}Nadador por IDGET/api/swimmers/{id}/performanceAnálisis de tendencias de rendimientoGET/api/categoriesCategorías por edadGET/api/racesPruebas (estilos y distancias)GET/api/time-recordsMarcas personales (cruce de tablas)GET/api/time-records/{id}Marca personal por IDGET/api/recordsRécords oficiales con filtros


🔐 Seguridad

La API implementa autenticación en dos fases mediante JWT:


El cliente realiza POST /api/auth/login con sus credenciales
El servidor devuelve un token JWT firmado con HS384
Todas las peticiones deben incluir el header: Authorization: Bearer <token>


Adicionalmente, APIMan añade una capa de control con:


Rate Limiting: máximo 10 peticiones por minuto
IP Blocklist: bloqueo de IPs no autorizadas



🧩 Funcionalidad destacada: Análisis de Rendimiento

GET /api/swimmers/{id}/performance?raceId={id}

Módulo de análisis estadístico que calcula, sin librerías externas:


Mejor tiempo, peor tiempo y media
Porcentaje de mejora entre primera y última marca
Tendencia: MEJORANDO / ESTABLE / EMPEORANDO / SIN DATOS



🗄️ Base de datos

10 entidades modeladas con JPA/Hibernate. Las migraciones se gestionan con Flyway:


V1__schema.sql — Creación del esquema
V2__data.sql — Datos iniciales de ejemplo



▶️ Ejecución en local

Requisitos


Java 21
Docker Desktop


Pasos

bash# 1. Clonar el repositorio
git clone https://github.com/tu-usuario/swimhub.git
cd swimhub

# 2. Levantar la base de datos
docker-compose up -d mariadb

# 3. Ejecutar la aplicación
./mvnw spring-boot:run

La API estará disponible en http://localhost:8081

Swagger UI en http://localhost:8081/swagger-ui/index.html


☁️ Despliegue en AWS

SwimHub está desplegado en AWS EC2 t2.micro (Ubuntu 24.04 LTS, us-east-1) mediante Docker Compose.

bash# Transferir archivos a la instancia EC2
scp -i labsuser.pem swimhub-0.0.1-SNAPSHOT.jar ubuntu@18.215.149.98:~/
scp -i labsuser.pem docker-compose.yml ubuntu@18.215.149.98:~/

# Conectarse y arrancar
ssh -i labsuser.pem ubuntu@18.215.149.98
docker-compose up -d

Puertos abiertos en Security Group: 22 (SSH), 8081 (API), 8080 (APIMan)


🧪 Tests

bashmvn test

77 tests automatizados:


36 tests unitarios de servicios (Mockito)
41 tests de integración de controladores (MockMvc + H2)



🌿 GitFlow

main        ← releases estables (v1.0.0)
develop     ← integración continua
feature/*   ← desarrollo de funcionalidades


## 📁 Estructura del proyecto

```
src/main/java/com/svalero/swimhub/
├── config/        # Configuración Spring Security, Swagger
├── controller/    # Controladores REST
├── domain/        # Entidades JPA
├── dto/           # Data Transfer Objects
├── exception/     # Manejo de excepciones
├── repository/    # Repositorios Spring Data
├── security/      # JWT Filter, UserDetailsService
└── service/       # Lógica de negocio

src/main/resources/
├── db/migration/  # Scripts Flyway (V1, V2)
└── application.properties

src/test/          # 77 tests automatizados
docker-compose.yml
```


👨‍💻 Autor

Jorge Díez Gracia

CFGS Desarrollo de Aplicaciones Multiplataforma

Centro San Valero · Zaragoza · Curso 2025/2026
