# devsuBanking
DEVSU technical interview project

### Services
- Account Service: Manages account and customer information.
- Customer Service: Manages customer information.
- Reports Service: Generates reports based on account and movement information.
- Postgres Database: Stores all information.
- RabbitMQ: Handles asyncrhonous messaging between services.


### Extra uses cases

1. When a new Customer is created, a default SAVINGS account must be created with 100 balance, as courtesy.
2. When a Customer erases its profile, all the associated accounts are deactivated and reference is cleared.
3. Validations as per business rules. For instance, a customer cannot be younger than 18 years old. At the time of account creation. Or cannot make movement if not enought balance in Account.


### Instalation

docker compose -d up --build

### Tests

- TDD methodology
- Testcontainers for database integration
- Unit tests for business logic

to run the tests:mvn test

### CUSTOMER SERVICE
- URL: http://localhost:8081
- Endpoints:
- Customer: /customer/*

### ACCOUNT SERVICE
- URL: http://localhost:8082
- Endpoints:
- Account: /account/*

### REPORTS SERVICE
- URL: http://localhost:8083
- Endpoints:
- Reports: /reports/*