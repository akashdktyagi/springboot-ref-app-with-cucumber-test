# Notes

Steps:

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

# References:
https://www.baeldung.com/cucumber-spring-integration

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

