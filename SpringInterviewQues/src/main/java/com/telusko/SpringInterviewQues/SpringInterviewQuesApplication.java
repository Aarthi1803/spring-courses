package com.telusko.SpringInterviewQues;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class SpringInterviewQuesApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringInterviewQuesApplication.class, args);
	}
}

/*@SpringBootApplication
public class SpringInterviewQuesApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringInterviewQuesApplication.class, args);
		TransportService ts = context.getBean(TransportService.class);
		ts.startTransport();
	}

}



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
}*/

