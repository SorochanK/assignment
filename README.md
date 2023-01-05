# Test assignment for AposHealth project

## To run the application:
- Set Up MongoDB server. By default, application will connect to `mongodb://localhost:27017/test` URL 
- Maven and JDK 11 should be installed on machine
- Run `mvn clean package`
- Run `mvn spring-boot:run`

## TODO items:
1) Since this application is a microservice, next improvements will may be taken into account:
    - Configuration properties can be moved into separate config-server
    - In case we will have several instances running, we may have transaction isolation problem, when multiple instances calling findAllByJob() and reset() methods at the same time
2) Add Exception Handler