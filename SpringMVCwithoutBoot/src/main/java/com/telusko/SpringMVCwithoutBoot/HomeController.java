package com.telusko.SpringMVCwithoutBoot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller // Detected because of <ctx:component-scan> in telusko-servlet.xml - Since HomeController is inside com.telusko, Spring automatically registers it as a bean.
public class HomeController {
	
	@RequestMapping("/")
	public String home() {
		System.out.println("home method called");
		return "index"; // index.jsp is inside webapps/views so directly mentioning view name and removing .jsp file extension
	}
	
	@RequestMapping("addAlien")
//	Using Spring - @ModelAttribute
//	public String addAlien(@ModelAttribute("alien1") Alien alien) { // if we are creating our own object name alien1 we need to mention inside @ModelAttribute("alien1")
	public String addAlien(Alien alien) { // @ModelAttribute is optional attribute here not mandatory to add this annotation
		System.out.println("add method called");
		return "result";
	}
	 
	@ModelAttribute("course") // using @ModelAttribute at method level. @ModelAttribute can be used at method level and also by passing as parameter in method
	public String courseName() {
		return "Java";
	}
	
}

/*
Overview of Spring MVC without Spring Boot(using Spring core concepts):

Initially when you build this project, you will get a maven project. 
You have to make sure that you add this dependency for spring MVC - spring-webmvc version 6.2.2
create our controller a home controller in which I have different methods. we are using annotations here for the mapping, 
but then to make this work, we have to make
sure that you ask your Tomcat because Tomcat by default have no idea that you are using a spring project or spring MVC project.
So you have to tell, I want to use spring MVC here, and the first controller or the first servlet you have to interact with is Dispatcher Servlet, 
which is given by the spring framework. For all the requests you have to call a Dispatcher Servlet. 
Dispatcher Servlet we are mentioning in the web.xml file.
Now, whom we are talking to Tomcat by using web XML. Web.xml is a way to talk to Tomcat. 
And then when you call Dispatcher Servlet it has no idea that you're using a home controller.
So in this case we use telusko-server.xml. This is a file which will be there in the WEB-INF folder.
In this you have to mention that I'm using annotations in home controller using annotation config - <ctx:annotation-config/>, 
and the package you will find all these classes is com.telusko - <ctx:component-scan base-package="com.telusko" />
This basically knows that hey I have to look at home controller and map it and then I have to call a particular page.
I'm calling the home method in home controller which is calling index.jsp. Spring has no idea where the index.jsp is located. 
So I have to mention the location of file and the file extension. 
The way you can do that is with the help of Internal View Resolver which we have configured in telusko-server.xml.
So Internal Resource View Resolver you have to mention the prefix that means the location of the file and the extension 
for the file which is suffix and that is your .jsp.
<bean class = "org.springframework.web.servlet.view.InternalResourceViewResolver"> 
  	<property name="prefix" value="/views/"></property>
  	<property name="suffix" value=".jsp"></property>
</bean>

But remember for a big project you will do this configuration only once. As you add more features, you have to do more configuration. 
But again, remember you will do this configuration only once for the entire project. 
So is it worth building project in spring MVC? Yes, you get more controls. 
Is it worth building project in Spring Boot? Yes, if it will save your time.
So it depends upon what kind of project you are building.
*/
