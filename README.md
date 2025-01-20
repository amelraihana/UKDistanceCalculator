# UK Distance Calculator

This project provides a RESTful service for calculating the distance between UK postal codes. It uses Spring Boot for the backend and includes several useful features.

## Prerequisites

Before you start, make sure you have the following installed:
- **Java 23** (or compatible version)
- **Maven** (for building the project)
- **Eclipse or IntelliJ** (for developing the project)

## How to Build and Run the Project (Command Line)

### 1\. Clone the Repository 
Navigate to the folder where you want to store the project and clone the repository:
```bash
cd /path/to/your/folder
git clone https://github.com/amelraihana/ukdistancecalculator.git
```

### 2\. Build the project with Maven
Navigate to the project folder and run:
```bash
cd ukdistancecalculator
mvn clean install
```
This will download all necessary dependencies and build the application.

### 3\. Run the Spring Boot application
Start the application with the following command:
```bash
mvn spring-boot:run
```
The app will be up and running at http://localhost:8080.


API Endpoints
-------------

### 1\. Get Distance Between Two Postal Codes

-   **Request Type**: `GET`
-   **URL**: `http://localhost:8080/distance?postcode1={postcode1}&postcode2={postcode2}`
-   **Parameters**:
    -   `postcode1`: The first postal code (e.g., "AB11 6UL").
    -   `postcode2`: The second postal code (e.g., "AB11 8RQ").
-   **Response**: Returns the distance between the two postal codes.

### 2\. Update Postal Code Coordinates

-   **Request Type**: `PUT`
-   **URL**: `http://localhost:8080/update-postcode?postcode={postcode}&latitude={latitude}&longitude={longitude}`
-   **Parameters**:
    -   `postcode`: The postal code you want to update (e.g., "AB10 1XG").
    -   `latitude`: The latitude of the postal code (e.g., "56.789").
    -   `longitude`: The longitude of the postal code (e.g., "-2.123").
-   **Response**: Confirms the update of the postal code's coordinates.

### 3\. Get Coordinates for a Postal Code

-   **Request Type**: `GET`
-   **URL**: `http://localhost:8080/get-coordinates?postcode={postcode}`
-   **Parameters**:
    -   `postcode`: The postal code to retrieve coordinates for (e.g., "AB10 1XG").
-   **Response**: Returns the latitude and longitude for the specified postal code.


### Bonus Features

#### Unit Tests

The project includes unit tests with JUnit 5 to ensure the service works correctly. You can run the tests with Maven:

```bash
mvn test
```

#### Updating Postal Codes

You can update the postal code-to-coordinate mapping by sending a PUT request to /update-postcode. This updates and saves the coordinates in the CSV file.

#### Request Logging

The application logs every request to the /distance endpoint, including the postal codes used. These logs can be analyzed later for reporting purposes.

#### Authentication

Basic authentication is implemented to restrict access to the API. Users need a valid username and password to use the service. You can configure these in the application.properties file.

Example configuration:
```properties
spring.security.user.name=user
spring.security.user.password=password
```

Technology Stack
----------------

-   **Spring Boot**: For building the RESTful service.
-   **JUnit 5**: For writing unit tests.
-   **Maven**: For managing dependencies and building the project.
-   **Spring Security**: For basic authentication.
-   **Apache Commons CSV**: For parsing CSV data.

