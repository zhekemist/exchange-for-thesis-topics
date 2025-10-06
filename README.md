# Topic Exchange for Bachelor Theses

This web application was developed as a project by a teammate and myself for the *Information Management and Systems Engineering* course at the *University of Vienna*. It allows professors to post bachelor thesis topics and students to browse and apply for them. The system supports either a relational database (MariaDB) or a NoSQL database (MongoDB), with a one-click feature to migrate all data and switch the system from the relational backend to MongoDB.

The conceptual system design is described [here](docs/conceptual_system_design.pdf).

## Running the Project

1. Build the backend and the containers:

```bash
docker compose build
```

2. Run the containers:

```bash
docker compose up -d
```

3. The website should now be reachable under the URL `https://localhost`.
