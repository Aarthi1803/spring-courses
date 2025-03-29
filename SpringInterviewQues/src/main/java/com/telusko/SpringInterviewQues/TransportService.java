package com.telusko.SpringInterviewQues;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/*@Service
public class TransportService {
	private final Vehicle vehicle;

// Spring does not know which Vehicle (Car or Bike) to inject
//    public TransportService(Vehicle vehicle) {
//        this.vehicle = vehicle;
//    }
	
	public TransportService(@Qualifier("car") Vehicle vehicle) {  // Specify "car" bean. Now, Spring injects Car instead of Bike, and no error occurs.
        this.vehicle = vehicle;
    }

    public void startTransport() {
        vehicle.drive();
    }
}*/

/* @Qualifier in Spring Boot - Uses and Explanation
In Spring Boot, @Qualifier is used to resolve bean conflicts when multiple beans of the same type are available in the Spring container. 
It helps Spring identify the correct bean to inject.

Why Use @Qualifier?
When you have multiple beans of the same type, Spring does not know which one to inject and throws an NoUniqueBeanDefinitionException.
To fix this, you can use @Qualifier to specify the exact bean you want to inject.

Example Without @Qualifier (Causes Conflict)
Scenario: Two beans of Vehicle type (Car and Bike)

import org.springframework.stereotype.Component;

@Component
class Car implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Driving a Car!");
    }
}

@Component
class Bike implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Riding a Bike!");
    }
}

interface Vehicle {
    void drive();
}

Service Class (Causes Error)

import org.springframework.stereotype.Service;

@Service
public class TransportService {
    private final Vehicle vehicle;

    // Spring does not know which Vehicle (Car or Bike) to inject
    public TransportService(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void startTransport() {
        vehicle.drive();
    }
}

❌ Error: NoUniqueBeanDefinitionException

No qualifying bean of type 'Vehicle' available: expected single matching bean but found 2: car,bike

Error: NoUniqueBeanDefinitionException
No qualifying bean of type 'Vehicle' available: expected single matching bean but found 2: car,bike
🔴 Spring gets confused because both Car and Bike implement Vehicle.

Description:
Parameter 0 of constructor in com.telusko.SpringInterviewQues.TransportService required a single bean, but 2 were found:
	- bike: defined in file [D:\Downloads-D drive\eclipse workspace\SpringInterviewQues\target\classes\com\telusko\SpringInterviewQues\Bike.class]
	- car: defined in file [D:\Downloads-D drive\eclipse workspace\SpringInterviewQues\target\classes\com\telusko\SpringInterviewQues\Car.class]

This may be due to missing parameter name information

Action:
Consider marking one of the beans as @Primary, updating the consumer to accept multiple beans, 
or using @Qualifier to identify the bean that should be consumed

🔴 Spring gets confused because both Car and Bike implement Vehicle.
Solution: Use @Qualifier

You can tell Spring explicitly which bean to inject using @Qualifier.
Updated Service Class with @Qualifier

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TransportService {
    private final Vehicle vehicle;

    public TransportService(@Qualifier("car") Vehicle vehicle) {  // Specify "car" bean
        this.vehicle = vehicle;
    }

    public void startTransport() {
        vehicle.drive();
    }
}

✅ Now, Spring injects Car instead of Bike, and no error occurs.
Example with @Bean and @Qualifier

If you're using @Bean instead of @Component, you can assign custom bean names.

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean(name = "carBean")
    public Vehicle car() {
        return new Car();
    }

    @Bean(name = "bikeBean")
    public Vehicle bike() {
        return new Bike();
    }
}

Injecting Using @Qualifier with Bean Name

@Service
public class TransportService {
    private final Vehicle vehicle;

    public TransportService(@Qualifier("bikeBean") Vehicle vehicle) { // Inject Bike
        this.vehicle = vehicle;
    }

    public void startTransport() {
        vehicle.drive();
    }
}

✅ Now Bike is injected instead of Car.
 Key Takeaways

✔ @Qualifier helps resolve conflicts when multiple beans of the same type exist.
✔ Used with @Component, @Service, or @Bean.
✔ Without @Qualifier, Spring throws NoUniqueBeanDefinitionException.
✔ Use @Qualifier("beanName") to specify which bean to inject.



@Primary in Spring Boot - Simple Explanation & Example
What is @Primary?

    @Primary tells Spring to use a specific bean by default when multiple beans of the same type exist.
    It avoids the need for @Qualifier unless you explicitly want to override the default choice.
    Only one bean can be marked as @Primary in a given type.
    
Use @Primary:

We can mark one bean as the default choice using @Primary, so Spring will automatically select it.
Mark Car as Primary

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary // Car is now the default choice
@Component
class Car implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Driving a Car!");
    }
}

@Component
class Bike implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Riding a Bike!");
    }
}

Service Class (No Need for @Qualifier Now)

import org.springframework.stereotype.Service;

@Service
public class TransportService {
    private final Vehicle vehicle;

    public TransportService(Vehicle vehicle) { // No need for @Qualifier, Car is selected by default
        this.vehicle = vehicle;
    }

    public void startTransport() {
        vehicle.drive();
    }
}


Output:
Driving a Car!

Now, Car is automatically injected because we marked it as @Primary.

Overriding @Primary with @Qualifier:
If we still want to use Bike, we can override @Primary using @Qualifier:

@Service
public class TransportService {
    private final Vehicle vehicle;

    public TransportService(@Qualifier("bike") Vehicle vehicle) { // Overrides @Primary
        this.vehicle = vehicle;
    }

    public void startTransport() {
        vehicle.drive();
    }
}

Now, Bike is injected instead of Car.
4️ Key Takeaways

✔ @Primary makes a bean the default choice when multiple beans of the same type exist.
✔ Avoids @Qualifier unless overriding the default bean.
✔ Only one bean of a type can be @Primary.
✔ Use @Qualifier to override @Primary when needed.



@Component vs @Bean in Spring Boot - Simple Explanation & Examples:
Both @Component and @Bean are used to register Spring Beans in the Spring container, but they are used in different ways.

@Component - Annotation-Based Bean Creation:
    @Component is used to automatically detect and register a bean in the Spring container.
    Works with @Service, @Repository, and @Controller (specialized forms of @Component).
    Requires component scanning (@ComponentScan) to detect and register beans automatically.

Example of @Component

import org.springframework.stereotype.Component;

@Component // Spring automatically detects this class and creates a bean
public class Car {
    public void drive() {
        System.out.println("Driving a Car!");
    }
}


Injecting @Component Bean

import org.springframework.stereotype.Service;

@Service
public class TransportService {
    private final Car car;

    public TransportService(Car car) { // Spring automatically injects Car
        this.car = car;
    }

    public void start() {
        car.drive();
    }
}

✅ No need to manually declare this bean in a configuration file!
✅ @Component makes the class a Spring-managed bean automatically.


@Bean - Manual Bean Definition in Configuration Class

    @Bean is used in a Java-based configuration class (@Configuration).
    It is manually defined inside a method that returns an object.
    Used when you don’t have control over the source code (e.g., third-party libraries).

Example of @Bean

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // This class provides Spring configuration
public class AppConfig {
    
    @Bean // Manually creates a bean for Car
    public Car carBean() {
        return new Car(); // Creating and returning the bean manually
    }
}

Injecting @Bean Bean

import org.springframework.stereotype.Service;

@Service
public class TransportService {
    private final Car car;

    public TransportService(Car car) { // Spring injects the manually defined bean
        this.car = car;
    }

    public void start() {
        car.drive();
    }
}

✅ @Bean allows more control over bean creation (e.g., setting properties before returning).
✅ Useful for third-party class beans where @Component cannot be used.

Key Differences Between @Component and @Bean
Feature									@Component												@Bean
Registration				Auto-detected by Spring									Defined manually inside a @Configuration class
Usage						Used for custom classes you create						Used for third-party classes or when manual configuration is needed
Control						Less control over bean creation							Full control over object creation and dependencies
Scanning Required?			Yes (@ComponentScan needed)								No scanning needed
Example						@Component above a class								@Bean inside a @Configuration class

When to Use What?

✔ Use @Component when you create your own classes and want them to be automatically registered.
✔ Use @Bean when you need full control over bean creation, such as for third-party libraries or special configurations.

*/