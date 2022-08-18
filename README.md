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

## Setting up database connection
In the file located in src/main/resources/application.properties enter the following:
```
spring.datasource.url= jdbc:postgresql://localhost:5432/<NameOfYourDatabase>
spring.datasource.username= <UsernameForDatabase>
spring.datasource.password= <PasswordForDatabase>

spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQL95Dialect
spring.jpa.hibernate.ddl-auto = create
logging.level.org.hibernate.state = trace
spring.jpa.show-sql = true
```

## Deployment


## Contributors
Johanna Olsson @johannaolsson & Veronica Andersen @VeronicaAndersen
