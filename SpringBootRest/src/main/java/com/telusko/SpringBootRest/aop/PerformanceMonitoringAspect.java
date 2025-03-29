package com.telusko.SpringBootRest.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component // Makes this a Spring bean
@Aspect // Declares this class as an Aspect
public class PerformanceMonitoringAspect {
	//@Around - want to be called start and end of method execution. Can check how much time it takes for a particular method to execute
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PerformanceMonitoringAspect.class);
	
//	@Around("execution(* com.telusko.SpringBootRest.service.JobService.getJob(..))")
	@Around("execution(* com.telusko.SpringBootRest.service.JobService.*(..))")
	public Object monitorTime(ProceedingJoinPoint jp) throws Throwable {
		long start = System.currentTimeMillis();
		Object obj = jp.proceed(); // Runs all methods of Job Service class. Executes the target method and stores the result.
		long end = System.currentTimeMillis();
		
		LOGGER.info("Time Taken by : "  +  jp.getSignature().getName()  + " " + (end - start)  +  " miliseconds");
		return obj;
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

/*

✔ @Around: Runs before and after method execution.
✔ Pointcut: Matches any method in JobService (* means any method).
✔ ProceedingJoinPoint: Allows us to continue execution of the method.

Real-World Example:
Imagine you're tracking how long customers wait on a support call:
    Before answering, start a timer.
    Once the call ends, stop the timer.
    Log the total duration.
*/