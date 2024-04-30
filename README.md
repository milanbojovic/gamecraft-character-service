# World of Gamecraft - Character Service

This is the Character Service for the World of Gamecraft project. This service allows registered users to create and manage their characters in the game world. This service is where most of the domain logic is implemented.

## Technologies Used

- Java
- SQL
- Spring Boot
- Maven

## Entities

- Character
- Class
- Item

## Setup and Running

### Prerequisites

- Java 8 or higher
- Maven
- Docker (for Redis, RabbitMQ, and SQL Server)

### Running with Spring Boot

1. Navigate to the project directory in your terminal.
2. Run the command `mvn spring-boot:run`.

### Running with Maven

1. Navigate to the project directory in your terminal.
2. Run the command `mvn clean install`.
3. Then run the command `java -jar target/character-service-0.0.1-SNAPSHOT.jar`.

## API Endpoints

- `/api/character` - GET - lists the characters with their names, health and mana. Only accessible to Game Masters.
- `/api/character/{id}` - GET - gets all the information for a given character by their Id. The stats are calculated as the sum of the base stats with with all the bonus stats from the items. Lists all of the associated items in the response as well. Accessible to Game Masters and Owners of the character. This request needs to be cached. The cache needs to be invalidated when the query result changes.
- `/api/character` - POST - creates a new character with the specified name, class and stats. Accessible to all users.
- `/api/items` - GET - lists all items in the system. Only accessible to Game Masters.
- `/api/items` - POST - creates an item with the provided data.
- `/api/items/{id}` - GET - gets all the details of the given item.
- `/api/items/grant` - POST - grants a character a specific item
- `/api/items/gift` - POST - moves an item from one player to another

## Testing

Unit tests for all the operations specified can be run with the command `mvn test`.

## Additional Features

- During character creation, check if the user id in the jwt token exists (is valid).
- Add logging

## Contact

For any issues, please contact the developer.
