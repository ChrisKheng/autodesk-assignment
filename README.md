# README
## Reports
Please find the answer to the SQL assignment and report for the Java microservice application in the `reports` directory.

## Steps to set up the application
1. Install the necessary tools:
* MySQL 8.0.33
* Maven 3.9.4
* OpenJDK 17

2. Set up the database by performing the steps below:
* Run `database/create_table.sql` and `database/init_db.sql` in the MySQL database.
* Create a user for the Spring microservice to connect to the MySQL database.
```aidl
mysql> create user 'springuser'@'%' identified by 'ThePassword'; 
mysql> grant all on book_store_db.* to 'springuser'@'%';
```

3. Make sure the JAVA_HOME environment variable is set to the installed location of OpenJDK 17, e.g. `/usr/local/opt/openjdk@17/`. 

4. Compile the Spring microservice jar by running the commands below:
```
mvn -N wrapper:wrapper
mnvw clean package
```

5. Run the microservice jar by running the command below:
```aidl
java -jar target/bookstore-service-0.0.1-SNAPSHOT.jar
```
* Make sure that the MySQL database is up.
* If you encounter issue when running the command above, make sure the `java` executable version used is Java 17.
  * You can specify the full path of the installed java executable of version 17 when running the command above too, e.g. `/usr/local/opt/openjdk@17/bin/java -jar target/bookstore-service-0.0.1-SNAPSHOT.jar`.  
* The service is configured to listen at port 8080.
  
6. Start using the application by following the steps below:
* Sign up an user through the `http://localhost:8080/employees/auth/signup` endpoint.
* Sign in using the created user through the `http://localhost:8080/employees/auth/signin` endpoint.
* Get all the books in the db through the `http://localhost:8080/books` endpoint.
* You can use [PostMan](https://www.postman.com/downloads) to make it easier to test the endpoints.
* Please refer to the report of this java program for all the endpoints.