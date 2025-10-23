<!-- @format -->

# Cities-SearchBar-Demo

## Project Overview

This is a simple web app that lets users search for city names using a search bar. As you type, the app shows auto-suggestions based on city names stored in the backend. Clicking a suggested city fills the search box and shows a confirmation message below the bar.

The goal is to provide a fast, interactive search experience and show how a React frontend connects to a Spring Boot backend with a MySQL database.

---

## Features

- Auto-suggestions while typing.
- Click to select a suggestion and show a confirmation message.
- Live search: suggestions are fetched from the backend as you type.
- Simple light/dark theme toggle and clean UI styling.
- Backend-ready: Spring Boot REST APIs connected to MySQL.

---

## Technologies Used

- Frontend: React, Vite, JSX, HTML, CSS
- Backend: Spring Boot, Java
- Database: MySQL
- Other: Fetch API for frontend-backend calls, SLF4J for logging

---

## Project Structure

### Backend (Spring Boot)

- `model/` – entity classes for cities.
- `repository/` – JPA repositories to query the database.
- `service/` – business logic for fetching cities.
- `controller/` – REST API endpoints the frontend calls.

### Frontend (React)

- `src/components/` – `SearchBar.jsx` handles input and suggestions.
- `src/styles/` – theme and UI styles (`theme.css`).
- `src/App.jsx` – main app component rendering the search bar.

---

## Setup Instructions

### Backend

1. Make sure MySQL is running and create a database named `hotel_locations_db`.
2. Update `application.properties` (or `application.yml`) with your MySQL credentials.
3. Run the Spring Boot application.
4. Backend API endpoints (examples):
   - `GET /cities/getAllCities` – returns all cities.
   - `GET /cities/suggest?keyword=<term>` – returns cities that match the input term.

### Frontend

1. Open a terminal and go to the frontend folder:

```powershell
cd city-search-ui
```

2. Install dependencies:

```powershell
npm install
```

3. Start the dev server:

```powershell
npm run dev
```

4. Open the app in your browser (default Vite URL):

http://localhost:5173

---

## How to Use

- Type a city name into the search bar.
- Auto-suggestions appear below the input.
- Click a suggestion to select it; a confirmation message appears below the search bar.
- Toggle light/dark theme using the theme button.

---

## Build & Run (Detailed)

This section explains how to build and run both frontend and backend, set up the database, and create a production build.

Prerequisites

- Node.js >= 16 and npm
- Java 17+ and Maven (or Gradle) for backend
- MySQL server
- (Optional) Git, Docker

1. Database (MySQL)

- Start MySQL and create the database used by the backend (update name if your config differs). Example:
  - Database name used by this project by default: hotel_locations_db
- Create table and insert sample Telangana cities:

```sql
CREATE DATABASE IF NOT EXISTS hotel_locations_db;
USE hotel_locations_db;

CREATE TABLE IF NOT EXISTS search_input_entity (
  city_id INT PRIMARY KEY,
  city_name VARCHAR(255) NOT NULL
);

INSERT INTO search_input_entity (city_id, city_name) VALUES
(1, 'Hyderabad'),
(2, 'Warangal'),
(3, 'Nizamabad'),
(4, 'Karimnagar'),
(5, 'Khammam'),
(6, 'Ramagundam'),
(7, 'Mahbubnagar'),
(8, 'Nalgonda'),
(9, 'Adilabad'),
(10, 'Suryapet'),
(11, 'Siddipet'),
(12, 'Miryalaguda'),
(13, 'Jagtial'),
(14, 'Mancherial'),
(15, 'Bhongir');
```

- Update backend DB credentials in src/main/resources/application.properties (or application.yml):

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/hotel_locations_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=validate
```

2. Backend (Spring Boot)

- From project root (backend module), build and run:
  - Build: mvn clean package
  - Run jar: java -jar target/<your-backend-jar>.jar
  - Or run in dev: mvn spring-boot:run
- Default API port: 8080
- Key endpoints:
  - GET /cities/getAllCities
  - GET /cities/suggest?keyword=<term>

3. Frontend (React / Vite)

- Change to frontend folder and install:

```powershell
cd city-search-ui
npm install
```

- Dev server:

```powershell
npm run dev
# open http://localhost:5173
```

- Production build:

```powershell
npm run build
# Built files in dist/ — serve with any static server or integrate into backend.
```

4. Run full stack (recommended)

- Start MySQL
- Start backend (ensure it connects to DB)
- Start frontend (dev) or serve built frontend
- Confirm suggestions endpoint works: http://localhost:8080/cities/suggest?keyword=Hyd

5. Troubleshooting

- 1054 unknown column errors: verify table columns match the backend entity (city_id, city_name).
- CORS errors: enable CORS in backend controller or allow origin http://localhost:5173 during development.
- Backend DB connection failures: check credentials, DB URL, and that MySQL is running.

6. Optional: Docker

- You can containerize MySQL, backend and frontend with Docker Compose (not included here). Ensure network and environment variables are set correctly.

7. Notes

- Update application.properties if you change DB name/credentials.
- If you want the frontend served by Spring Boot, copy contents of city-search-ui/dist into backend static resources (e.g., src/main/resources/static) after building the frontend.

---

## Future Improvements

- Highlight matching characters in suggestions.
- Keyboard navigation (arrow keys + Enter to select).
- Show a clear "No results found" message when there are no matches.
- Animate the suggestion dropdown for a smoother UI.
- Integrate external city APIs for live data.

---
