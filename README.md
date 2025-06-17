# Fitness and Yoga Rating Page System
## Description
The Fitness and Yoga Rating System is a REST and SOAP-based web service designed to manage and rate yoga and fitness pages. It provides secure user registration, authentication, page management, rating, and commenting functionalities. The system enforces security with JWT-based authentication and role-based access control.

## Technologies Used
- Backend: Spring Boot, Spring Security

- Database: PostgreSQL

- Authentication/Authorization: JWT (JSON Web Tokens) with Spring Security

- Build Tool: Maven

- Testing: Postman (for REST and SOAP endpoints)
## Getting Started
### Installation Steps
1. Clone the repository 
```bash
   git clone https://github.com/pecakova/Fitness-and-Yoga-Rate-Service.git
   ```
2. Navigate to the project directory
``` 
cd yogafitnessrating
```
3. Configure your database in application.properties:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/yogadb
spring.datasource.username=yourusername
spring.datasource.password=yourpassword
```
4. Build and run the application:
```
mvn clean install
mvn spring-boot:run

```
5. Access the services:
- REST API base URL: http://localhost:8080/api
- SOAP endpoint URL: http://localhost:8080/ws
## API Endpoints
### REST Endpoints
- POST /api/register
Register a new user. No authentication required.

- POST /api/login
User login. No authentication required.

- POST /api/logout
User logout. Requires authentication.

- POST /api/pages/add
Add a new yoga or fitness page. Requires authentication.

### SOAP Endpoints
- POST /ws with UpdatePageRequest
Update an existing page.

- POST /ws with DeletePageRequest
Delete a page.

- POST /ws with RatePageRequest
Rate a page.

- POST /ws with AddCommentRequest
Add a comment to a page.

- POST /ws with ListPagesRequest
List pages with optional filters like type, category, and minimum rating.
## Security
- Authentication via JWT tokens.
- Passwords hashed with BCryptPasswordEncoder.
- Role-based access control enforced via Spring Security annotations.
- Protected endpoints require a valid JWT token.
## Project Structure
- src/main/java — source code
- src/main/resources/schema — SOAP XSD files
- src/test — tests
- pom.xml — Maven configuration
## Contribution
Feel free to contribute! Follow these steps:

1. Fork the repository.
2. Create a new branch:
```
git checkout -b feature/your-feature-name
```
3. Make your changes.
4. Commit your changes:
```
git commit -m "commit"
```
5. Push to your branch:
```
git push origin feature/your-feature-name
```
6. Open a Pull Request on GitHub.

## Author
Bojana Pecakova