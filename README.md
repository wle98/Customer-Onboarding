# Customer Onboarding Backend

A Spring Boot REST API for managing customer onboarding: profile creation, document upload/validation, onboarding activity tracking, and status history.

## Tech Stack

- **Language:** Java 21 (Amazon Corretto)
- **Framework:** Spring Boot 4.1.1 (Spring Web, Spring Data JPA, Spring Security, Validation)
- **Database:** PostgreSQL (AWS RDS in production, local Postgres for dev)
- **Build tool:** Gradle
- **API docs:** springdoc-openapi / Swagger UI
- **Deployment:** AWS EC2 + RDS

## Live Deployment

- Swagger UI: Link will change (Dynamic public IP)

## API Overview

| Module | Base Path | Description |
|---|---|---|
| Customer Profile | `/api/customers` | Create, read, update, delete customer profiles |
| Onboarding Status | `/api/customers/{id}/status`, `/api/customers/{id}/status-history` | Update and track onboarding status changes |
| Onboarding Activities | `/api/customers/{customerId}/activities`, `/api/activities/{id}` | Assign and track onboarding tasks |
| Documents | `/api/customers/{customerId}/documents`, `/api/documents/{id}` | Upload and validate onboarding documents (PDF/JPG/PNG, max 10MB); status defaults to `PENDING` pending admin review |

## Deploying Changes to AWS

1. Build a fresh jar:
   ```
   ./gradlew clean build
   ```
2. Transfer it to EC2:
   ```
   scp -i customer-onboarding-key.pem build/libs/Customer-Onboarding-0.0.1-SNAPSHOT.jar ec2-user@<EC2_PUBLIC_IP>:~/
   ```
3. SSH into EC2 (via EC2 Instance Connect or your terminal), stop the running app:
   ```
   pkill -f Customer-Onboarding
   ```
4. Restart with the new jar:
   ```
   nohup java -jar Customer-Onboarding-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod > app.log 2>&1 &
   ```
5. Confirm it's running:
   ```
   ps aux | grep java
   ```
6. Check logs if something looks off:
   ```
   tail -f app.log
