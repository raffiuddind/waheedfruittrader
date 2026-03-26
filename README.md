# Waheed Fruit Trader - Spring Boot Web Application

A production-ready Spring Boot REST API for managing fruit trading operations including inventory, transactions, customers, suppliers, reporting, and WhatsApp notifications.

## 📋 Table of Contents

- [Overview](#overview)
- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Database Setup](#database-setup)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [API Documentation](#api-documentation)
- [Docker Setup](#docker-setup)
- [WhatsApp Integration](#whatsapp-integration)
- [Default Users](#default-users)
- [Troubleshooting](#troubleshooting)

## Overview

Waheed Fruit Trader Web Application is a comprehensive Spring Boot REST API that provides:
- Complete fruit inventory management
- Sales and purchase transaction processing
- Customer and supplier management
- Multi-location inventory tracking
- PDF and Excel report generation
- WhatsApp notification integration
- JWT-based authentication with role-based access control
- Swagger API documentation

## Technology Stack

| Component | Technology |
|-----------|-----------|
| Framework | Spring Boot 3.2.0 |
| Language | Java 17 |
| Database | MySQL 8.0 |
| ORM | MyBatis 3.0.3 |
| Migrations | Flyway |
| Security | Spring Security + JWT (JJWT 0.12.3) |
| Cache | Redis |
| PDF Reports | iTextPDF 5.5.13 |
| Excel Reports | Apache POI 5.2.4 |
| API Docs | SpringDoc OpenAPI 2.2.0 |
| Build | Maven |
| Container | Docker |

## Project Structure

```
src/
├── main/
│   ├── java/com/waheedfruittrader/
│   │   ├── WaheedFruitTraderApplication.java
│   │   ├── config/          # DatabaseConfig, SecurityConfig, SwaggerConfig, etc.
│   │   ├── controller/      # REST Controllers
│   │   ├── exception/       # Custom exceptions & GlobalExceptionHandler
│   │   ├── mapper/          # MyBatis mapper interfaces
│   │   ├── model/
│   │   │   ├── dto/         # Data Transfer Objects
│   │   │   └── entity/      # JPA Entities
│   │   ├── security/        # JWT, filters, UserDetailsService
│   │   ├── service/         # Business logic services
│   │   └── util/            # Utility classes
│   └── resources/
│       ├── application.yml
│       ├── application-dev.yml
│       ├── application-prod.yml
│       ├── db/migration/    # Flyway SQL migration scripts
│       └── mybatis/mapper/  # MyBatis XML mapper files
└── test/
    └── java/com/waheedfruittrader/
```

## Prerequisites

- **JDK 17** or higher
- **Maven 3.6+**
- **MySQL 8.0+**
- **Redis 6+** (optional, for caching)
- **Docker & Docker Compose** (optional, for containerized setup)

### Verify Installation

```bash
java -version    # Should show 17+
mvn -version     # Should show 3.6+
mysql --version  # Should show 8.0+
```

## Installation

### 1. Clone the Repository

```bash
git clone https://github.com/raffiuddind/waheedfruittrader.git
cd waheedfruittrader
```

### 2. Install Dependencies

```bash
mvn clean install -DskipTests
```

## Database Setup

### Create MySQL Database

```sql
CREATE DATABASE waheed_fruit_trader CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER 'waheed_user'@'localhost' IDENTIFIED BY 'waheed_pass';
GRANT ALL PRIVILEGES ON waheed_fruit_trader.* TO 'waheed_user'@'localhost';
FLUSH PRIVILEGES;
```

Flyway will automatically run migration scripts on startup.

## Configuration

### application.yml (Key Settings)

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/waheed_fruit_trader
    username: waheed_user
    password: waheed_pass

app:
  jwt:
    secret: your-secure-jwt-secret-key-minimum-32-chars
    expiration: 86400000  # 24 hours in ms

whatsapp:
  enabled: false  # Set to true to enable WhatsApp notifications
  api-token: your-whatsapp-api-token
  phone-number-id: your-phone-number-id
```

### Environment Variables

| Variable | Description | Default |
|----------|-------------|---------|
| `DB_USERNAME` | MySQL username | `waheed_user` |
| `DB_PASSWORD` | MySQL password | `waheed_pass` |
| `JWT_SECRET` | JWT signing secret | (built-in default) |
| `REDIS_HOST` | Redis hostname | `localhost` |
| `REDIS_PORT` | Redis port | `6379` |
| `WHATSAPP_TOKEN` | WhatsApp API token | (empty) |
| `WHATSAPP_ENABLED` | Enable WhatsApp | `false` |
| `SERVER_PORT` | Application port | `8080` |

## Running the Application

### Development Mode

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### Production Mode

```bash
mvn clean package -DskipTests
java -jar -Dspring.profiles.active=prod target/waheed-fruit-trader-1.0.0.jar
```

### With Environment Variables

```bash
export DB_USERNAME=waheed_user
export DB_PASSWORD=waheed_pass
export JWT_SECRET=my-secret-key
mvn spring-boot:run
```

## API Documentation

After starting the application, access Swagger UI at:
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **API Docs**: http://localhost:8080/v3/api-docs

### Authentication

All API endpoints (except `/api/v1/auth/**`) require JWT authentication.

```bash
# Login
POST /api/v1/auth/login
{
  "username": "admin",
  "password": "admin123"
}

# Use returned token in subsequent requests
Authorization: Bearer <token>
```

### API Endpoints Summary

| Endpoint | Method | Description |
|----------|--------|-------------|
| `/api/v1/auth/login` | POST | User login |
| `/api/v1/fruits` | GET | List all fruits |
| `/api/v1/fruits/{id}` | GET | Get fruit by ID |
| `/api/v1/fruits` | POST | Create fruit |
| `/api/v1/fruits/{id}` | PUT | Update fruit |
| `/api/v1/fruits/{id}` | DELETE | Delete fruit |
| `/api/v1/customers` | GET/POST | Customers CRUD |
| `/api/v1/suppliers` | GET/POST | Suppliers CRUD |
| `/api/v1/transactions` | GET/POST | Transactions |
| `/api/v1/transactions/{id}/status` | PATCH | Update status |
| `/api/v1/inventory` | GET | Inventory list |
| `/api/v1/inventory/low-stock` | GET | Low stock alerts |
| `/api/v1/inventory/locations` | GET/POST | Locations |
| `/api/v1/reports/sales` | GET | Sales summary |
| `/api/v1/reports/sales/pdf` | GET | Download sales PDF |
| `/api/v1/reports/sales/excel` | GET | Download sales Excel |
| `/api/v1/dashboard` | GET | Dashboard metrics |
| `/api/v1/notifications/whatsapp/send` | POST | Send WhatsApp msg |

## Docker Setup

### Start with Docker Compose

```bash
# Start MySQL, Redis, and application
docker-compose up -d

# View logs
docker-compose logs -f app

# Stop all services
docker-compose down
```

### Services

| Service | Port | Description |
|---------|------|-------------|
| MySQL | 3306 | Database server |
| Redis | 6379 | Cache server |
| App | 8080 | Spring Boot app |

## WhatsApp Integration

To enable WhatsApp notifications:

1. Create a Meta Developer account at https://developers.facebook.com
2. Create a WhatsApp Business API application
3. Get your Phone Number ID and API Token
4. Configure in `application.yml`:

```yaml
whatsapp:
  enabled: true
  api-token: your-token
  phone-number-id: your-phone-number-id
```

### Notification Types

- Order confirmation on transaction creation
- Low stock alerts (scheduled)
- Custom messages via `/api/v1/notifications/whatsapp/send`

## Default Users

After first startup, these default users are created:

| Username | Password | Role |
|----------|----------|------|
| `admin` | `admin123` | ADMIN |
| `manager` | `manager123` | MANAGER |

> **⚠️ Security**: Change default passwords immediately in production!

## Troubleshooting

### Application Won't Start

1. Check MySQL is running: `mysql -u root -p`
2. Verify database exists: `SHOW DATABASES;`
3. Check application logs: `tail -f logs/app.log`

### Flyway Migration Errors

```bash
# Clean and re-run migrations (development only)
mvn flyway:clean flyway:migrate -Dflyway.url=jdbc:mysql://localhost:3306/waheed_fruit_trader
```

### JWT Token Issues

- Ensure `app.jwt.secret` is at least 32 characters
- Token expires after 24 hours by default (configurable)

### WhatsApp Not Working

1. Verify `whatsapp.enabled=true`
2. Check API token validity
3. Ensure phone number is registered with WhatsApp Business

---

**Version**: 1.0.0  
**Last Updated**: 2026-03-26  
**Repository**: [raffiuddind/waheedfruittrader](https://github.com/raffiuddind/waheedfruittrader)
