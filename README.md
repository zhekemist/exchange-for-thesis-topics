# Thesis Topic Exchange

A web application for managing bachelor thesis topics. Instructors can post thesis topics, and students can browse and apply for them. The system supports two backends, one based on MariaDB and another one on MongoDB, including a one-click migration feature to transfer data from the relational database to the non-relational one.

This project was developed for the *Information Management and Systems Engineering* course at the *University of Vienna* together with my teammate [Felix Windhager](https://github.com/felixwindhager). The conceptual system design, including ER and UML Activity diagrams, is documented [here](docs/conceptual_system_design.pdf).

## Technologies

- **Backend**: Java with Spring Boot, Spring Data JPA, Spring Data MongoDB, Spring Data REST
- **Frontend**: HTML/CSS/JavaScript, Alpine.js, Bootstrap 5, Nginx as Webserver
- **Databases**: MariaDB (relational), MongoDB (NoSQL)
- **Infrastructure**: Docker, Docker Compose

## Running the Project

The whole application can be spun up using Docker:

1. After cloning the repository, build the containers:
```bash
docker compose build
```

2. Start the application:
```bash
docker compose up -d
```

3. Access the application at `http://localhost`

4. Stop the application:
```bash
docker compose down
```
