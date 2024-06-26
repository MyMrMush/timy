![Test](https://github.com/MyMrMush/Timy/actions/workflows/test.yml/badge.svg)
![Deploy](https://github.com/MyMrMush/Timy/actions/workflows/deploy.yml/badge.svg)

# Timy: Time Tracker & Optimiser
A REST API for time tracking demonstrating Domain-Driven Design (DDD) and Clean Architecture principles.

Developed as a part of the course "Advanced Software Engineering" at the Baden-Württemberg Cooperative State University (DHBW) in Karlsruhe.

## Usage
### Online Demo
An online version of the API is available at [timy.mush-it.com](https://timy.mush-it.com). With it's Swagger UI, exposed at [timy.mush-it.com/swagger-ui](https://timy.mush-it.com/swagger-ui/index.html), you can interact with the API without any additional tools.

I recommend using the [Postman](https://www.postman.com/) app with the included [Collection](.postman/Timy%20Collection.postman_collection.json) for more complex requests.

### Running Locally
#### Setup
To run Timy locally you need either a Java 17 JDK with Maven or Docker installed on your machine.
```
git clone https://github.com/MyMrMush/timy.git
cd timy
```

#### Docker
```
docker compose up
```

#### Java CLI
When running manually, keep in mind, that the MainApplication class is located in the plugins module.
```
mvn clean install && mvn -pl plugins spring-boot:run
```

#### IDE
You can also run the application from your IDE by running the MainApplication class in the plugins module.
