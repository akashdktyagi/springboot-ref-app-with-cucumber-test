# Notes

---
* For CI-CD Flow: [CI CD Flow Diagram](CI_CD_FLOW.md)
* You Tube Link for this tutorial: [Dev Ops CI CD Tutorial](https://www.youtube.com/watch?v=GqNm2Fb2_Fw&list=PLBiGKr76xSBAllrsfTQLrZfjI8jwjOzOr)
---

Steps to Follow:

* Generate API template from Spring Initilaizer. Check the POM.xml for what dependencies to be added.
* Add cucumber dependencies in the pom. Check pom.
* Since we are following Test Driven Development, we will start by writing tests first
* We will use Cucumber BDD FW for writing our Integration Tests
* Create Feature file with CreateEntity.feature and add a Simple CreateEntity Scenario
* Run the Feature file, it shows error. 
* Create a Step Defs package under test and add a class as mentioned in the Cucumber Error earlier i.e.
a class with annotation as ```@SpringBootTest``` or other TestSliced Annotation like ```@WebMvcTest``` and ```@CucumberContextConfiguration```
* These annotations tell cucumber to bring the Spring Application Context up before the test starts.
* Run the test again, Cucumber will fail and it will ask to add step defs
* Create a new Class file as StepDefs under test segment of maven.
* Run it again and now your Scenario is failing again saying steps are to be implemented.
* Add ```@AutoConfigureMockMvc``` in CucumberSpringConfiguration.class. We will use MockMvc to call endpoints. Other Libraries can also be used instead of MockMvc, for instance I could also use RestAssured as a java client. But MockMvc is recommended because it is near to Spring Eco system and developers in general are more comfortable with it.
* Autowire MockMvc Instance in the Step Defs ```    @Autowired MockMvc mockMvc;```
* Write Step defs
* Write the Step Defs Impl using Mock MVC
* Impl controller and add post mapping
* Impl Model
* Impl Service
* Impl  DTO
* Run curl cmd:
  * ```curl --data '{"name":"a","description":"b","company":"c"}' -H 'Content-Type: application/json' --request POST 'http://localhost:9096/api/entity'```
* Implement Sonar Qube analysis
* Implement Dockerfile and Google JIB
* Implement Swagger Open API 3

---

## Details: Design CI CD Pipeline for a Spring Boot Application and Dockerize the app

In this tutorial we are going to be doing below things:

1. Spring Boot Application
2. Containerize the application
   a. DockerFile
   b. JIB plugin
3. Push in to the registry
4. Design a CI Pipeline via Jenkins
5. Sonar Analysis

Code is kept here: https://github.com/akashdktyagi/springboot-ref-app-with-cucumber-test


### Dockerize or containerise a spring boot application rest api.
1. The Spring boot rest for this demonstration has two end points 1. Get and 2. Post
2. We are using embed H2 DB for persistence and using JPA for data access.
3. This API is responsible for creating and retrieving an entity i.e. Product.

###How to build, run and Push a Docker image using Docker File and locally

#### Build, Run and Push Locally:
For this to work, Docker Desktop has to be installed first.
* Install from here: https://www.docker.com/products/docker-desktop
* The root of the project need to have a file with name as “Dockerfile” with content as below:
```dockerfile

FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 9096
ENTRYPOINT ["java","-jar","/app.jar"]
  
```
### Common Docker Commands for Local push, pull, build, tag commands
* Below commands then can be used to build, run and push the docker images.
  * Get all docker images: docker images -a
  * Delete all docker images: docker rmi $(docker images -a -q)
  * List all containers: docker ps -a
  * Stop all containers: docker stop $(docker ps -a -q)
  * Delete all containers: docker rm $(docker ps -a -q)
  * Build simple Docker image: docker build .
  * Tag an image with Build:
  * docker build -t vieux/apache:2.0:latest .
  * docker build --tag com.yantracloud/spring-ref-app:latest .
* For forcefully build the image: docker build --pull --no-cache --tag yantracloud/spring-ref-app:latest .
* Run the image:
  * docker run -itd -p9096:9096 com.yantracloud/spring-ref-app:latest
  * Check if the port is free in Mac: netstat -vanp tcp | grep 8083
* Push Images:
  * First login to docker hub: docker login
  * Make sure you have
  * docker image push yantraqa/spring-ref-app:latest

#### Use Google Jib Plugin to Build and Push(Run is not Required in CI)

  * This is industry standard way of building and running the app.
  * If this is used there is no need to have docker desktop/ deamon to be available on the running machine
  * No Docker file is needed as well. 
  * All you have to provide is maven plugin your pom.xml file like this:
  * Credentials of the plugin can be passed from command line or Jenkins file like this:

#### Jenkins Stage which will pass the Docker login crendential to google jib plugin during maven Build Process
```shell
stage('Build Create and Push Image') {
  steps {
    withCredentials([usernamePassword(credentialsId: 'docker-hub', passwordVariable: 'p', usernameVariable: 'u')]) {
      sh "mvn -B clean install -Djib.to.auth.username=$u -Djib.to.auth.password=$p"
    }

  }
}

```

#### Maven Google JIB Plugin:
```xml

<plugin>
  <groupId>com.google.cloud.tools</groupId>
  <artifactId>jib-maven-plugin</artifactId>
  <version>3.2.0</version>
  <configuration>
    <from>
      <image>openjdk:11</image>
    </from>
    <to>
      <image>docker.io/yantraqa/spring-ref-app:latest</image>
    </to>
    <container>
      <jvmFlags>
        <jvmFlag>-Xms256m</jvmFlag>
        <jvmFlag>-Xmx512m</jvmFlag>
      </jvmFlags>
      <ports>
        <port>9096</port>
      </ports>
    </container>
    <allowInsecureRegistries>true</allowInsecureRegistries>
  </configuration>
  <executions>
    <execution>
      <phase>install</phase>
      <goals>
        <goal>build</goal>
      </goals>
    </execution>
  </executions>
</plugin>

```

#### Jenkins Pipeline Set Up:
* Add a Jenkinsfile in the root folder with below code.
* This will run the sonar analysis on the code and build the images.
  File will look like this:
  ```shell
  
  pipeline {
    agent any
    stages {
    stage('sonar') {
      steps {
        withEnv(['SONAR_TOKEN=0ab2cf1faf9fb442a7fa818f57cf552e1d3b9649']) {
          sh 'mvn -B verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dsonar.projectKey=akashdktyagi_springboot-ref-app-with-cucumber-test'
        }
      
        }
      }
    stage('Build Create and Push Image') {
        steps {
            withCredentials([usernamePassword(credentialsId: 'docker-hub', passwordVariable: 'p', usernameVariable: 'u')]) {
               sh "mvn -B clean install -Djib.to.auth.username=$u -Djib.to.auth.password=$p"
            }

        }     
      }
    }
  }
  ```

### How a CI and CD looks like: 
* [CI CD Flow Diagram](CI_CD_FLOW.md)
* We are only doing the CI in this tutorial and only two imp stages in CI i.e. Sonar Code Analysis and Image Building and Pushing.
* To understand how to implement both of these things, please check the video.

# References:
https://www.baeldung.com/cucumber-spring-integration
https://www.baeldung.com/spring-rest-openapi-documentation

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.3/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.3/maven-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#using-boot-devtools)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#boot-features-developing-web-applications)
* [Thymeleaf](https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#boot-features-spring-mvc-template-engines)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Handling Form Submission](https://spring.io/guides/gs/handling-form-submission/)

