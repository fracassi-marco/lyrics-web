# Lyrics Web

Lyrics Web is a symple project based on Boot Start and Kotlin. 
It's goal is to explore th testing stragegies related to a Spring Boot web application written in Kotlin.
It provide a set of examples for:
* acceptance tests with remote endpoint
* acceptance tests with local edpoint
* acceptance tests with RestTemplate
* external rest service integration tests
* controller unit tests
* Freemarker templates tests
* error handling tests

## Getting Started

Clone the repository:

```
git clone https://github.com/fracassi-marco/lyrics-web.git
cd lyrics-web
```

Run the application:

```
mvn mvn spring-boot:run
```

Open browser:

```
http://localhost:8080/
```

Search for author: ```U2``` title: ```Pride```

Enjoy

### Prerequisites

You must have Maven

## Running the tests

```
mvn test
```

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **[Marco Fracassi](https://github.com/fracassi-marco)**

## License

This project is licensed under the GNU GENERAL PUBLIC LICENSE Version 3 - see the [LICENSE](LICENSE) file for details
