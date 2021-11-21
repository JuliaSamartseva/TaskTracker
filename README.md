# Task Tracker

Project consists of several microservices each transformed into a Docker image. Docker Compose is used to manage every container with an internal network between the services.

## Getting Started

To install this application, run the following commands:

```bash
git clone https://github.com/JuliaSamartseva/TaskTracker.git
cd TaskTracker/task-project
```

### Authentication

Project uses OAuth 2.0 environment with Okta as an authentication provider. Before running the application, copy your issuer, client ID, and client secret from Okta developer account into `task-project/config-data/task-ui-service.properties`.

### Docker

Run the following command from the root folder to create Docker containers for all apps:

```shell
./mvnw clean install
```

Then you can start microservices architecture using Docker Compose:

```shell
docker-compose up -d
```

After everything starts, you will be able to log in at `http://localhost:8080`.


