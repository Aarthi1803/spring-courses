package com.telusko;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDataRestDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataRestDemoApplication.class, args);
	}

}

/*
 Spring data rest:
When you talk about building a web application where you have database as well, we basically go with multiple layers. So when a client sends a request, request goes to the controller.
Controller only accept data or accept the request and then respond. But if you want to do any processing that will be done by service and service says, okay, my job is processing, but what if you want to maybe get some data from database? Now that's where it will talk to repository. And repository says, okay, it's my job to connect to database. So repository connect database fetch data. So we have all these layers right now what we have understood till this point is if you want to build a controller layer we have to use a class. And that class has to be @controller. On the other hand, if you want to do any processing, you will do that in @Service and then in @Repository. Now you can write all your JDBC code inside the repository. But then we have not done that right. We have used something called a spring data JPA. Now it says you don't have to worry about the queries I will take care of it.
If you have something very advanced, you have to manually write the query for it. But then spring Data JPA helps you to reduce the number of lines in your code.

So in Spring Data JPA, we have spring data, which is actually a project inside spring which has multiple modules inside it. So basically spring data has something called Spring Data JPA we talked about. It also has something called Rest. Now what it says is if you look at your all the layers, repository says you don't have to write a lot of codes. I can simplify it for you. And then I will give you certain functions if you want to use it.
Now if you talk about the service, we have to write a lot of code, because when you talk about business logic, if you want to process something, you will do that in service.
But in our application, we are not writing a lot of code inside service reason. We are not doing much of processing or logical stuff inside our code. So service is basically responsible in our application is just to connect with repository write. Hey, I want this because I got asked this from my controller. Also, if you look at our controller APIs we have not done much. We have just written the mapping and we are calling a service. Even we are not doing any processing inside the controller. So this spring people thought can we just simplify this more? And what if we can remove the service layer in our project because that specific to our project, not in general. So in our project service layer is of no use because we are just doing Crud operations so we can remove the service layer. What about the controller? What if you can remove the controller? I know you will be thinking how is that possible? Because we need something to accept the request. Yes, we need something, but that doesn't mean that you have to build it. What if someone else will give it to you?
And that's where we have this project called "Spring Data Rest", which if you are using it, you don't even have to create a controller.


 
*/