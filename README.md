# Milk Collection Backend

Spring Boot REST API for the uploaded Flutter Milk Collection Inventory application.

## Current Flutter features matched

- Add farmer
- View farmers
- Add milk collection record
- View milk collection records
- Filter milk records by farmer/date/session
- Dashboard summary
- Server-side amount calculation
- MySQL persistence

## Stack

- Java 17
- Spring Boot 4.1.0
- Spring Web
- Spring Data JPA
- MySQL
- Spring Security (currently permits API access; authentication can be added later)
- Bean Validation
- Lombok

## 1. Create MySQL database

```sql
CREATE DATABASE milk_collection_db;
```

## 2. Configure database

Edit:

`src/main/resources/application.properties`

Change:

```properties
spring.datasource.username=root
spring.datasource.password=CHANGE_ME
```

## 3. Run

Windows:

```cmd
mvnw.cmd spring-boot:run
```

or:

```cmd
mvnw.cmd clean package
java -jar target/milk-collection-backend-0.0.1-SNAPSHOT.jar
```

## 4. Test

Open:

`http://localhost:8080/api/health`

Expected:

```json
{
  "status": "UP",
  "message": "Milk Collection Backend is running"
}
```

## API endpoints

### Farmers

POST `/api/farmers`

```json
{
  "name": "Rahul Patil",
  "mobile": "9876543210"
}
```

GET `/api/farmers`

GET `/api/farmers/{id}`

PUT `/api/farmers/{id}`

DELETE `/api/farmers/{id}`

### Milk

POST `/api/milk`

```json
{
  "farmerId": 1,
  "date": "16-08-2026",
  "session": "Morning",
  "time": "10:30 AM",
  "milkType": "Cow",
  "quantity": 10.5,
  "fat": 4.2,
  "rate": 42.5
}
```

`amount` is calculated by the backend as quantity × rate.

GET `/api/milk`

GET `/api/milk/{id}`

GET `/api/milk/farmer/{farmerId}`

GET `/api/milk/date/{date}`

GET `/api/milk/filter?date=16-08-2026&session=Morning`

PUT `/api/milk/{id}`

DELETE `/api/milk/{id}`

### Dashboard

GET `/api/dashboard`

## Android emulator

For the Flutter Android emulator use:

`http://10.0.2.2:8080`

For a physical Android phone, use the PC's LAN IP, for example:

`http://192.168.1.10:8080`

The phone and PC must be on the same Wi-Fi/network.
