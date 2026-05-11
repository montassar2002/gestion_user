# Gestion des Utilisateurs et des Rôles

## Description
Application web full-stack de gestion des utilisateurs, rôles et permissions avec authentification JWT.

## Technologies utilisées

### Backend
- Java 17
- Spring Boot 3.2.0
- Spring Security + JWT
- Spring Data JPA
- MySQL 8
- Swagger/OpenAPI
- Maven

### Frontend
- Angular 19
- Bootstrap 5
- TypeScript

### Déploiement
- Docker
- Docker Compose

## Architecture du projet

gestion_user/
├── src/                    # Code source Spring Boot
├── frontend/               # Application Angular
│   ├── src/
│   ├── Dockerfile
│   └── nginx.conf
├── Dockerfile              # Docker backend
├── docker-compose.yml      # Docker Compose
├── pom.xml
└── README.md

## Fonctionnalités
- Authentification JWT (login/logout)
- Gestion des utilisateurs (CRUD + activation/désactivation)
- Gestion des rôles (CRUD)
- Gestion des permissions (CRUD)
- Attribution des permissions aux rôles
- Contrôle d'accès par permissions (@PreAuthorize)
- Historique des actions (audit)
- Documentation API avec Swagger

## Installation et Exécution

### Prérequis
- Java 17
- Node.js 20
- MySQL 8
- Docker (optionnel)

### Lancement sans Docker

**Backend :**
```bash
cd gestionuser
mvn spring-boot:run
```

**Frontend :**
```bash
cd frontend
npm install
ng serve
```

### Lancement avec Docker
```bash
docker-compose up --build
```

## Accès à l'application
| Service | URL |
|---------|-----|
| Frontend | http://localhost:4200 |
| Backend API | http://localhost:8081 |
| Swagger UI | http://localhost:8081/swagger-ui/index.html |

## Compte administrateur par défaut
| Champ | Valeur |
|-------|--------|
| Email | montassar@gmail.com |
| Mot de passe | 123456 |
| Rôle | ADMIN |

## Auteurs
- Montassar