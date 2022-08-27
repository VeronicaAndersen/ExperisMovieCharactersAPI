# Movie Characters API 
The application are constructed in Spring Web and comprise of a database made in PostgreSQL through Hibernate
with a RESTful API to allow users to manipulate the data. The database store information about characters, movies
they appear in, and the franchises these movies belong to.

## Installation 

### Repository
The application is free to clone straight from gitlab. Type this into your selected git console to get the current main version: 
```
git clone git@gitlab.com:VeronicaAndersen/movie-characters-api.git
```

### IDE software
To run and use this repository you can use a software that you like. This one was build in IntelliJ IDEA and are using spring boot with plugin Gradle.

## Setting up locally database connection
In the file located in src/main/resources/application.properties enter the following:
```
spring.datasource.url = jdbc:postgresql://localhost:5432/<NameOfYourDatabase>
spring.datasource.username = <UsernameForDatabase>
spring.datasource.password = <PasswordForDatabase>

spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQL95Dialect
spring.jpa.hibernate.ddl-auto = create
logging.level.org.hibernate.state = trace
spring.jpa.show-sql = true
spring.sql.init.platform = postgres
spring.jpa.defer-datasource-initialization = true
spring.sql.init.mode = always
springdoc.swagger-ui.operationsSorter = method
```

## Deployment [Docker & Heroku]
```
1. docker build -t <NameOfDocker> .    
2. heroku login
3. heroku create --region eu --app <NameOfDeployment>
4. heroku container:login    
5. heroku container push web --app <NameOfDeployment> 
6. docker run -p 8085:8080 <NameOfDocker>    
7. heroku container:push web --app <NameOfDeployment>   
8. heroku container:release web --app <NameOfDeployment>    
```
### Heroku
Configure Add-ons called Heroku Postgres in heroku under Overview.

<img alt="img.png" src="img.png" title="Add-ons"/>

### Gitlab
Make sure that both stages for build & deploy are passed under CI/CD Pipelines.

![img_1.png](img_1.png)
## Contributors
Johanna Olsson @johannaolsson & Veronica Andersen @VeronicaAndersen
