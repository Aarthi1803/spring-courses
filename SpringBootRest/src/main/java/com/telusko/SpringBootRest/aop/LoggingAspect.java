package com.telusko.SpringBootRest.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/*@Component // Marks this class as a Spring-managed bean
@Aspect // Declares this class as an Aspect for AOP
public class LoggingAspect {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class); // Creates a logger instance to log messages in the console.
	
	// return type, fully qualified class name.method name(args)
	// inside () - are called as PointCut meaning an expression to specify when you want your advice to be called
	@Before("execution(* com.telusko.SpringBootRest.service.JobService.*(..))")
	public void logMethodCall() {
		LOGGER.info("Method called");
	}
	
}

*/

/******************************************************************/

/*@Component
@Aspect
public class LoggingAspect {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);
	
	// inside () we mention - return type, fully qualified class name.method name(args)
	// inside () - are called as PointCut meaning an expression to specify when you want your advice to be called
//		@Before("execution(* com.telusko.SpringBootRest.service.JobService.*(..))")
//		public void logMethodCall() {
//			LOGGER.info("Method called");
//		}
	
	// inside () we mention - return type, fully qualified class name.method name(args)
	// inside () - are called as PointCut meaning an expression to specify when you want your advice to be called
	// || - pipe symbol used to call different methods for advice to get executed
	@Before("execution(* com.telusko.SpringBootRest.service.JobService.getJob(..)) || execution(* com.telusko.SpringBootRest.service.JobService.updateJob(..))")
	public void logMethodCall(JoinPoint jp) { // To get hold on getJob method we use JoinPoint
		LOGGER.info("Method called " + jp.getSignature().getName());
	}
}


// Spring AOP (Aspect-Oriented Programming) allows us to add reusable behavior (like logging, validation, and performance monitoring) without modifying existing code.
 
	// @Before: Before Advice - Logging Method Call Before Execution - Executes before the getJob() or updateJob() method runs.
	// Pointcut (execution(...)): Matches methods in JobService named getJob(..) or updateJob(..). (..) means any parameters.
    // ✔ JoinPoint jp: Captures method details.
    // ✔ jp.getSignature().getName(): Gets the method name being executed.

*/

/******************************************************************/

@Component
@Aspect
public class LoggingAspect {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);
	
	// inside () we mention - return type, fully qualified class name.method name(args)
	// inside () - are called as PointCut meaning an expression to specify when you want your advice to be called
	// || - pipe symbol used to call different methods for advice to get executed
	@Before("execution(* com.telusko.SpringBootRest.service.JobService.getJob(..)) || execution(* com.telusko.SpringBootRest.service.JobService.updateJob(..))")
	public void logMethodCall(JoinPoint jp) { // To get hold on getJob method we use JoinPoint
		LOGGER.info(" Method called Before " + jp.getSignature().getName());
	}
	
	//@After means it calls @AfterFinally - calls after executing method which results in either success or failure 
	@After("execution(* com.telusko.SpringBootRest.service.JobService.getJob(..)) || execution(* com.telusko.SpringBootRest.service.JobService.updateJob(..))")
	public void logMethodExecuted(JoinPoint jp) { // To get hold on getJob method we use JoinPoint
		LOGGER.info(" Method executed After " + jp.getSignature().getName());
	}
	
	//@AfterThrowing - calls after executing method which results in failure 
	@AfterThrowing("execution(* com.telusko.SpringBootRest.service.JobService.getJob(..)) || execution(* com.telusko.SpringBootRest.service.JobService.updateJob(..))")
	public void logMethodCrash(JoinPoint jp) { // To get hold on getJob method we use JoinPoint
		LOGGER.info(" Method has some issues " + jp.getSignature().getName());
	}
	
	//@AfterReturning - calls after executing method which results in success 
	@AfterReturning("execution(* com.telusko.SpringBootRest.service.JobService.getJob(..)) || execution(* com.telusko.SpringBootRest.service.JobService.updateJob(..))")
	public void logMethodExecutedSuccess(JoinPoint jp) { // To get hold on getJob method we use JoinPoint
		LOGGER.info(" Method executed successfully " + jp.getSignature().getName());
	}
	
}

/* 
What is JoinPoint? Why is it used?
A JoinPoint represents a specific execution point in your application where an aspect (advice) can be applied.

🔹 Real-World Example
Imagine you are tracking customer calls in a call center.
    When a customer calls (JoinPoint), you log who called, when they called, and which department they contacted.
    Similarly, in AOP, JoinPoint allows us to capture method execution details.

What is JoinPoint?
Definition:
    A JoinPoint represents a point in the execution of a program where an aspect (e.g., logging, validation) can be applied.
    In Spring AOP, a JoinPoint typically refers to a method execution.

Why is JoinPoint Used?
    It provides metadata about the method being executed, such as:
        Method name (jp.getSignature().getName()).
        Method arguments (jp.getArgs()).
        Target object (jp.getTarget()).
    This metadata is useful for logging, validation, and other cross-cutting concerns.


What is ProceedingJoinPoint? Why is it used?
🔹 ProceedingJoinPoint is a subclass of JoinPoint that allows us to control method execution.
🔹 It is used only inside @Around advice because @Around advice controls when and how a method executes.

What is ProceedingJoinPoint?
Definition:
    A ProceedingJoinPoint is a special type of JoinPoint used in around advice.
    It allows you to control whether the original method should proceed or not.

Why is ProceedingJoinPoint Used?
    It provides the ability to:
        Execute the original method using jp.proceed().
        Modify method arguments before proceeding.
        Skip the original method execution (if needed).
        
Key Differences Between JoinPoint and ProceedingJoinPoint:
Aspect				JoinPoint													ProceedingJoinPoint
Usage				Used in before, after, and after returning advice.			Used in around advice.
Method Execution	Cannot control method execution.							Can control method execution using proceed().
Modify Arguments	Cannot modify method arguments.								Can modify method arguments before proceeding.

Summary:
    JoinPoint: Provides metadata about the method being executed. Used in before, after, and after returning advice.
    ProceedingJoinPoint: Extends JoinPoint and allows you to control method execution. Used in around advice.
    Real-World Use Cases:
        JoinPoint: Logging method executions, successes, and failures.
        ProceedingJoinPoint: Measuring method execution time, validating and updating method arguments.

Summary
Concept								What it does?									Example Use Case
JoinPoint							Captures method execution details				Logging method names when executed
ProceedingJoinPoint					Controls method execution (start/stop)			Measuring execution time
jp.proceed()						Runs the original method						Running getJob(101) normally
jp.proceed(new Object[] {postId})	Runs method with modified arguments				Converting getJob(-4) to getJob(4)

*/

// After Advice - Logging Method Execution After Completion - @After: Runs after the method finishes (whether successful or failed).
// ✔ Logs after getJob(..) or updateJob(..) execution.

// ✔ @AfterThrowing: Runs only if the method throws an exception.
// ✔ Logs that the method had issues.

// AfterReturning Advice - Logging Successful Execution
// ✔ @AfterReturning: Runs only if the method executes successfully.
// ✔ Logs successful method execution.

