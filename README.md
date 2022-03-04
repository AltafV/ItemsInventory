# ITEMS INVENTORY REST CRUD API with Spring Boot, Mysql, JPA

## Steps to Setup

**1. Create Mysql database**
```bash
create database inventorydb
```

**2. Create tables in database either using the create stmts or changing the value to create**
```bash
Option 1 -  run scripts
-- inventorydb.item definition
CREATE TABLE `item` (
  `item_id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `item_condition` varchar(255) DEFAULT NULL,
  `item_specifics` varchar(255) DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
  
-- inventorydb.list_of_imageurl definition

CREATE TABLE `list_of_imageurl` (
  `item_item_id` bigint(20) NOT NULL,
  `imageurl` varchar(255) DEFAULT NULL,
  KEY `FK2b837w9q5g3w3c2nbgc1n7uos` (`item_item_id`),
  CONSTRAINT `FK2b837w9q5g3w3c2nbgc1n7uos` FOREIGN KEY (`item_item_id`) REFERENCES `item` (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

Option 2 - do below  
Change spring.jpa.hibernate.ddl-auto in application.properties to create
For all future runs please change to update.
```

**3. Change mysql username and password as per your installation**

+ open `src/main/resources/application.properties`

+ change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation

**4. Build and run the app using maven**

```bash
mvn package
java -jar target/inventory-0.0.1-SNAPSHOT.jar

```

Alternatively, you can run the app without packaging it using -

```bash
mvn spring-boot:run
```

## Explore Rest APIs at <http://localhost:8080/swagger-ui.html>



