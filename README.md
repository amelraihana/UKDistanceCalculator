# UK Distance Calculator

This project provides a RESTful service for calculating the distance between UK postal codes. It uses Spring Boot for the backend and includes several features to enhance its functionality.


## Prerequisites

Before building and running the project, make sure you have the following installed:
- **Java 23** (or a compatible version)
- **Maven** (for building the project)
- **Eclipse or IntelliJ** (for developing and running the project)

## Simple way to build and run the project (using command line)

### 1\. Clone the repository at your desired directory
```bash
cd eclipse-workspace
git clone https://github.com/amelraihana/ukdistancecalculator.git
```

### 2\. Build the project with Maven

Navigate to the root directory of the project and run:
```bash
cd ukdistancecalculator
mvn clean install
```
This will download all necessary dependencies and build the application.

### 3\. Run the Spring Boot application
```bash
mvn spring-boot:run
```
This will start the application, and it should be available at http://localhost:8080.

#### From Eclipse/IntelliJ:

1.  **Eclipse**: Right-click on the project in the Project Explorer > **Run As** > **Spring Boot App**.
2.  **IntelliJ**: Right-click on the `UkDistanceCalculatorApplication.java` class (located in the `src/main/java/com/example/ukdistancecalculator/` directory) and select **Run 'UkDistanceCalculator'**.

The application will start, and you can access the API at `http://localhost:8080`.


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
    -   `latitude`: The latitude to associate with the postal code (e.g., "56.789").
    -   `longitude`: The longitude to associate with the postal code (e.g., "-2.123").
-   **Response**: Confirms that the postal code coordinates were successfully updated.

### 3\. Get Coordinates for a Postal Code

-   **Request Type**: `GET`
-   **URL**: `http://localhost:8080/get-coordinates?postcode={postcode}`
-   **Parameters**:
    -   `postcode`: The postal code for which to retrieve coordinates (e.g., "AB10 1XG").
-   **Response**: Returns the latitude and longitude for the specified postal code.


### Bonus Features

#### Unit Tests

The project includes unit tests using JUnit 5 to verify the functionality of the service. You can run the tests using Maven:

```bash
mvn test
```

#### Updating Postal Codes

The API allows you to update the postal code-to-coordinate mapping by sending a `PUT` request to `/update-postcode`. The coordinates for the given postal code will be updated and persisted in the database.

#### Request Logging

The application logs every request to the `/api/distance` endpoint, including the postal codes used in the request. The logs can be later aggregated for analysis. These logs are stored in the application's log files.

#### Authentication

To restrict access to the service, basic authentication is implemented. Users need to provide a valid username and password to access the API. You can configure the username and password in the `application.properties` file.

Example configuration:
```properties
spring.security.user.name=user
spring.security.user.password=password
```

Technology Stack
----------------

-   **Spring Boot**: Framework for building the RESTful service.
-   **JUnit 5**: Used for writing unit tests.
-   **Maven**: Dependency management and build tool.
-   **Spring Security**: Provides basic authentication.
-   **Apache Commons CSV**: Used for parsing CSV data.
