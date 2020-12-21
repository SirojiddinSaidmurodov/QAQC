# QA/QC

Sign in / Sign up web-forms with a login and password validation + JUnit and Selenium tests

# Content

- [Content](#content)
- [Web App](#web-app)
- [Junit 5](#junit-5-tests)
- [PageObject](#pageobject-pattern)
- [Cucumber + Selenide](#cucumber--selenide-test)

## Web App

![img](img/login.png)

Making a web application with log in, sign up pages, and some database manipulating page. Additionally, made a login and
password validation on sign up. Server has a custom error handlers (for 404, 400, 500 errors).

❗ Note: server runs on 8888 port specified in [properties](src/main/resources/application.properties)

[SourceCode](src/main/java/edu/keepeasy/testcode)

### References

- YouTube [playlist](https://www.youtube.com/playlist?list=PLU2ftbIeotGpAYRP9Iv2KLIwK36-o_qYk) with step-by-step
  creating of web application on Russian (You need only first 6 videos)
- [Documentation](https://spring.io/projects/spring-boot) of Spring Boot
- [Documentation](https://spring.io/projects/spring-security) of Spring Security
- Serving Web Content with Spring MVC [guide](https://spring.io/guides/gs/serving-web-content/)
- Accessing data with MySQL [guide](https://spring.io/guides/gs/accessing-data-mysql/)
- Securing a Web Application [guide](https://spring.io/guides/gs/securing-web/)
- [Documentation](http://mustache.github.io/mustache.5.html) of Mustache
- [Guide](https://www.baeldung.com/spring-boot-custom-error-page) for making custom error pages on Spring Boot

## JUnit 5 tests

Test application as a [white box](https://en.wikipedia.org/wiki/White-box_testing), we know internal structure and
working of application.

[SourceCode](src/test/java/edu/keepeasy/testcode)

### References

- [Documentation](https://junit.org/junit5/docs/current/user-guide/) of JUnit 5

## Selenium tests

Functional testing of application, that means we don't know anything about internal structure of software we are
testing, also known as [Black box](https://en.wikipedia.org/wiki/Black_box)

[SourceCode](src/test/java/selenium)

### References

- [Documentation](https://www.selenium.dev/documentation/en/) of Selenium

## PageObject pattern

[SourceCode](src/test/java/selenium/pageObjectTest)

### References

- [Page Object pattern](http://internetka.in.ua/selenium-page-object/) in Russian

## Cucumber + Selenide test

❗ You may need Cucumber plugin for your IDE to be able to run tests
[SourceCode](src/test/java/cucumber/test)

### References

- Cucumber [docs](https://cucumber.io/docs/cucumber/)
    - Cucumber [installation](https://cucumber.io/docs/installation/java/)
- Selenide [docs](https://ru.selenide.org/documentation.html)
- [Gherkin](https://cucumber.io/docs/gherkin/reference/) syntax
- IntelliJ IDEA [plugin](https://plugins.jetbrains.com/plugin/7212-cucumber-for-java)
