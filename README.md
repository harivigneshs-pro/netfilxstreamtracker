# Netflix Tracker

A Spring Boot web application for tracking movie viewing behavior, storing ratings, and generating recommendations.

## Features

- User management with subscription tracking
- Movie catalog with ratings and metadata
- Watch history tracking with user ratings
- Recommendation system
- Analytics reports for top-rated and most-watched movies
- RESTful API endpoints
- Web interface with Thymeleaf templates

## Technology Stack

- **Backend**: Spring Boot 3.2.0, Spring Web, Spring Data JPA
- **Database**: MySQL 8.0
- **Frontend**: Thymeleaf, Bootstrap 5.1.3
- **Build Tool**: Maven
- **Java Version**: 17
- **Additional Libraries**: Lombok, Validation

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- MySQL 8.0 or higher

## Database Setup

1. Install MySQL and create a database:
```sql
CREATE DATABASE netflix_tracker;
```

2. Update the database credentials in `src/main/resources/application.properties`:
```properties
spring.datasource.username=your_username
spring.datasource.password=your_password
```

## Installation & Running

1. Clone the repository:
```bash
git clone <repository-url>
cd NetflixTracker
```

2. Build the project:
```bash
mvn clean install
```

3. Run the application:
```bash
mvn spring-boot:run
```

4. Access the application:
- Web Interface: http://localhost:8080
- API Base URL: http://localhost:8080/api

## API Endpoints

### Users
- `GET /api/users` - Get all users
- `GET /api/users/{id}` - Get user by ID
- `POST /api/users` - Create new user

### Movies
- `GET /api/movies` - Get all movies
- `GET /api/movies/{id}` - Get movie by ID
- `POST /api/movies` - Add new movie
- `GET /api/movies/top-rated` - Get top 5 rated movies
- `GET /api/movies/top-watched` - Get top 5 watched movies

### Watch History
- `GET /api/history` - Get all watch history
- `GET /api/history/user/{userId}` - Get watch history by user
- `GET /api/history/movie/{movieId}` - Get watch history by movie
- `POST /api/history` - Add watch record

### Recommendations
- `GET /api/recommendations` - Get all recommendations
- `GET /api/recommendations/user/{userId}` - Get recommendations by user
- `POST /api/recommendations` - Add recommendation

## Web Pages

- `/` - Dashboard homepage
- `/users` - User management page
- `/movies` - Movie catalog page
- `/history` - Watch history page
- `/recommendations/{userId}` - User recommendations page
- `/reports` - Analytics reports page

## Sample API Usage

### Create a User
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john@example.com",
    "subscriptionType": "Premium"
  }'
```

### Add a Movie
```bash
curl -X POST http://localhost:8080/api/movies \
  -H "Content-Type: application/json" \
  -d '{
    "title": "The Matrix",
    "genre": "Sci-Fi",
    "releaseYear": 1999,
    "duration": 136,
    "contentType": "Movie"
  }'
```

### Record Watch History
```bash
curl -X POST http://localhost:8080/api/history \
  -H "Content-Type: application/json" \
  -d '{
    "user": {"userId": 1},
    "movie": {"movieId": 1},
    "rating": 5
  }'
```

## Database Schema

The application uses the following entities:

- **Users**: user_id, name, email, subscription_type, join_date
- **Movies**: movie_id, title, genre, release_year, duration, rating_avg, content_type, created_at
- **WatchHistory**: watch_id, user_id (FK), movie_id (FK), watch_date, rating
- **Recommendations**: rec_id, user_id (FK), movie_id (FK), reason, created_at

## Sample Data

The application automatically initializes with sample data including:
- 3 sample users
- 5 sample movies
- Watch history records
- Recommendation entries

## Project Structure

```
src/main/java/com/netflixtracker/
├── controller/          # REST and Web controllers
├── service/            # Business logic layer
├── repository/         # Data access layer
├── model/             # JPA entities
├── dto/               # Data transfer objects (optional)
├── DataInitializer.java
└── NetflixTrackerApplication.java

src/main/resources/
├── templates/         # Thymeleaf templates
├── static/           # Static web resources
└── application.properties
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License.