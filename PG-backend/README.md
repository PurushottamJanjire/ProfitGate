# ProfitGate Backend (Spring Boot)

## Quick Start
1. Open in IntelliJ IDEA as Maven project.
2. Edit `src/main/resources/application-local.properties` with your MySQL username/password and ensure DB `progate` exists.
3. Run `ProfitGateApplication`.

## Test with curl
- Health: `curl http://localhost:8080/api/health`
- Create industry: `curl -X POST http://localhost:8080/api/industries -H 'Content-Type: application/json' -d '{"name":"Logistics"}'`
- Create user: `curl -X POST http://localhost:8080/api/users -H 'Content-Type: application/json' -d '{"fullName":"Alice CEO","email":"alice@example.com","passwordHash":"hash","role":"business_owner"}'`
- Create company: see CompanyController (uses IDs of user and industry via DTO).

Note: Hibernate is set to `ddl-auto=none` because tables already exist in MySQL.
