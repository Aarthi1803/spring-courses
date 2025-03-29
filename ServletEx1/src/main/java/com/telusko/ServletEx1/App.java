package com.telusko.ServletEx1;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

// This Java program sets up an embedded Apache Tomcat server and registers a servlet to handle HTTP requests

public class App {
    public static void main( String[] args ) throws LifecycleException { // throws LifecycleException → Handles any errors that occur during Tomcat startup.
    	Tomcat tomcat = new Tomcat(); // Creates a new Tomcat server instance. This is an embedded Tomcat server, meaning it runs inside our Java application instead of being deployed separately. 
//    	tomcat.setPort(9091); // To change default tomcat port number from 8080 to some other port number we can use this line 
    	Context context = tomcat.addContext("", null); // here we are getting hold on to context object. for 1st parameter we are using default app so mentioning " " . for 2nd parameter we don't need to create new directory structure so we mention "null" 
        Tomcat.addServlet(context,"HelloServlet",new HelloServlet());  // we are doing mapping of servlet class. 2nd parameter is servlet name "HelloServlet"- servlet name can be any name eg: hi, hello etc 
        context.addServletMappingDecoded("/hello", "HelloServlet");  // 1st parameter we are using url mapping, 2nd parameter we are using servlet name "HelloServlet"- servlet name can be any name eg: hi, hello etc
//      2nd parameter of addServletMappingDecoded() must have same servletName as 2nd parameter of addServlet(). both must have same name       
        
        tomcat.start(); // Starts the Tomcat server so it can process requests. After this line, Tomcat is fully functional and ready to serve requests.
        tomcat.getServer().await(); // Informing Tomcat to hold on to server and wait for request. If this line is not mentioned it stops tomcat service abruptly. Keeps the Tomcat server running. Without this line, Tomcat would start and immediately shut down. This keeps the application alive, allowing it to accept incoming requests.
    }
}


// Context context = tomcat.addContext("", null) :  Adds a web application context (root /)
// addContext("", null) → Adds a new web application context to Tomcat.
// First parameter "" (empty string) → This means the root context (i.e., "http://localhost:8080/").
// Second parameter null → No physical directory is needed for this web application (i.e., no web.xml or static files required).
//🔹 Think of Context as a mini web application inside Tomcat. This is where we register our servlets.

// Tomcat.addServlet(context, "HelloServlet", new HelloServlet()) : Registers a servlet (HelloServlet)
// Registers a Servlet inside Tomcat.
// First parameter → context (Tells Tomcat where to add the servlet).
// Second parameter "HelloServlet" → This is the name of the servlet.
// Third parameter new HelloServlet() → Creates an instance of HelloServlet (Assuming it's a servlet class that handles requests).
//🔹 A servlet is a Java class that handles HTTP requests & responses (like a mini web server).

// context.addServletMappingDecoded("/hello", "HelloServlet") : Maps /hello to HelloServlet
// Maps the URL /hello to the "HelloServlet" servlet.
// This means: If a user visits "http://localhost:8080/hello", the HelloServlet will handle the request.

// If you are using an external Tomcat, you can do more configuration.
// Example:  In the earlier days, if you want to do the configuration, we used to use XML.
// So the file name is web.xml .
// In this xml file you do the mapping, you specify the URL and you specify which servlet to call when url is being used. Whenever someone requests for /hello URL, it execute HelloServlet and you can mention multiple servlets in web.xml. That was one approach.
// But then we wanted to move to the annotation way. In the annotation way, what you do is you on top of your servlet class we use @WebServlet. And in the bracket you mention for which request you want to work with - @WebServlet("/hello"). So what it means is whenever someone requests for hello, you have to call this particular HelloServlet. This is how basically we should do in the annotation way.