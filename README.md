We would like you to complete a coding exercise for us. We'd like to see an example of your coding abilities, style, and how you make use of the best coding standards and practices.

Exercise: Write a Spring Boot REST API that models a simple payroll service that manages the employees of a company. Simply put, you need to store employee objects in any database of your choice, and access them via JPA. The employee object should store information such as employee id, name, and job role.

You have to build the following API endpoints:

1. Get all employees
2. Find an employee by id
3. Add new employee
4. Update employee details

You can write unit tests to verify if your code is working as expected. Finally, dockerize the application and create a build script to build the docker image, and run the script to run the built image as a docker container. You can create a GitHub repository and share the repository link with us for review.

Let me know if you have questions or are unable to complete the exercise.



/* SQL */

CREATE TABLE EMPLOYEE
( "ID" number(10) NOT NULL,
"NAME" varchar2(50) NOT NULL,
"JOB" varchar2(50)
);



CREATE SEQUENCE SEQ_EMPLOYEE_ID
  MINVALUE 1
  MAXVALUE 9999999999
  START WITH 1
  INCREMENT BY 1
  CACHE 20;
  

/* to run application */

gradlew build && java -jar build/libs/ms_ibm-0.0.1-SNAPSHOT.jar

/* to build docker image */

docker build -t ibm:0.0.1 .

/* to make docker container */

docker run -d --name ibmRun -p 8080:8080 ibm:0.0.1
