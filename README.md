# IPO Web Application

A Spring Boot application for managing Initial Public Offerings (IPOs).

## Tech Stack

- Java 17+ with Spring Boot 3.x
- Spring Web (REST APIs)
- Spring Data JPA with Hibernate
- MySQL database
- Maven for dependency management
- Spring Boot DevTools for development
- Thymeleaf for server-side templating
- Bootstrap 5 for responsive UI
- JavaScript for frontend interactivity
- Docker for containerization

## Features

1. IPO Management:
   - Add new IPO listings
   - View all IPOs
   - Update IPO details
   - Delete IPOs
   - Search IPOs by company name or status
   
2. User Interface:
   - Responsive Bootstrap UI
   - Interactive forms for adding/editing IPOs
   - Data tables with sorting and filtering
   - Modal dialogs for IPO details and confirmations
   - Real-time feedback with alert messages

## API Endpoints

- `GET /api/ipos` - Get all IPOs
- `GET /api/ipos/{id}` - Get IPO by ID
- `POST /api/ipos` - Create new IPO
- `PUT /api/ipos/{id}` - Update IPO
- `DELETE /api/ipos/{id}` - Delete IPO
- `GET /api/ipos/search?name={companyName}` - Search by company name
- `GET /api/ipos/status/{status}` - Get IPOs by status (UPCOMING, ACTIVE, CLOSED)

## Setup Instructions

### Prerequisites

- Java 17+
- Maven 3.8+
- MySQL 8.0
- Docker and Docker Compose (optional)

### Local Development Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/ipo-application.git
   cd ipo-application
   ```

2. Configure MySQL database:
   - Create a database named `ipo_db`
   - Update database configuration in `src/main/resources/application.properties` if needed

3. Build the application:
   ```bash
   mvn clean install
   ```

4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

5. Access the application:
   - Frontend UI: `http://localhost:8080`
   - REST API: `http://localhost:8080/api/ipos`

### Docker Setup

1. Build and run using Docker Compose:
   ```bash
   docker-compose up -d
   ```

2. Access the application:
   - Frontend UI: `http://localhost:8080`
   - REST API: `http://localhost:8080/api/ipos`

## Database Schema

The application uses a single table `ipos` with the following structure:

- `id` - Auto-generated primary key
- `company_name` - Name of the company
- `issue_price` - Price per share
- `total_shares` - Total number of shares
- `open_date` - IPO opening date
- `close_date` - IPO closing date
- `status` - IPO status (UPCOMING, ACTIVE, CLOSED)
- `description` - Description of the company/IPO
- `created_at` - Record creation timestamp
- `updated_at` - Record update timestamp

## User Interface

The application provides a user-friendly interface with the following features:

### Main Dashboard
- View all IPOs in a responsive table
- Filter IPOs by status (Upcoming, Active, Closed)
- Search IPOs by company name
- Refresh data with a single click

### IPO Management
- Add new IPOs through an intuitive form
- Edit existing IPO details
- View detailed information in a modal dialog
- Delete IPOs with confirmation

### Responsive Design
- Works on desktop, tablet, and mobile devices
- Clean, modern Bootstrap 5 interface
- Interactive elements with visual feedback

## Sample API Requests

### Create a new IPO

```bash
curl -X POST http://localhost:8080/api/ipos \
  -H "Content-Type: application/json" \
  -d '{
    "companyName": "New Tech Inc.",
    "issuePrice": 100.50,
    "totalShares": 5000000,
    "openDate": "2024-06-15",
    "closeDate": "2024-06-20",
    "status": "UPCOMING",
    "description": "A new technology company"
  }'
```

### Get all IPOs

```bash
curl -X GET http://localhost:8080/api/ipos
```

### Search IPOs by company name

```bash
curl -X GET http://localhost:8080/api/ipos/search?name=Tech
```

## License

This project is licensed under the MIT License - see the LICENSE file for details. 