package com.telusko.SpringDemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.telusko.config.AppConfig;
import com.telusko.config.AppConfigAnnote;

//  XML Based Configuration

/*public class App {
    public static void main( String[] args ) {
    	ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
    	Alien obj = (Alien) context.getBean("alien");
    	obj.code();
    }
}*/

/*public class App {
    public static void main( String[] args ) {
    	ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
    	Alien obj1 = (Alien) context.getBean("alien1");
    	obj1.age = 21;
    	System.out.println(obj1.age);
//    	obj.code();
    	
    	Alien obj2 = (Alien) context.getBean("alien1");
    	System.out.println(obj2.age);
//    	obj2.code();
    }
}*/

/*public class App {
    public static void main( String[] args ) {
    	ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
    	Alien obj1 = (Alien) context.getBean("alien1");
    	System.out.println(obj1.getAge());
    	obj1.code();
    	
    }
}*/

/*public class App {
    public static void main( String[] args ) {
    	ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
    	Alien obj1 = (Alien) context.getBean("alien1");
    	System.out.println(obj1.getAge());
    	obj1.code();
    	
//    	Desktop obj = (Desktop) context.getBean("com2"); // Desktop object will not be created by default when IOC container loads because we have set Desktop class as lazy-init=true in spring.xml, Desktop object will be created only when we call context.getBean(). Here object is still singleton.  
    
    }
}*/

/*public class App {
    public static void main( String[] args ) {
    	ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
//    	Alien obj1 = (Alien) context.getBean("alien1");
    	Alien obj1 = context.getBean("alien1", Alien.class);
    	System.out.println(obj1.getAge());
    	obj1.code();
    	
    	Computer com = context.getBean(Computer.class); // gives output as "Compiling using Laptop because primary="true" is mentioned in <bean id="com1" class="com.telusko.SpringDemo.Laptop" primary="true"> </bean>. If primary="true" not given then it gives ambiguity because both Laptop and Desktop class implements Computer interface
//    	To resolve above lin e ambiguity we can mention - Desktop obj = context.getBean(Desktop.class); and Alien obj1 = context.getBean(Alien.class);

//    	Desktop obj = (Desktop) context.getBean("com2"); 
//    	Desktop obj = context.getBean("com2", Desktop.class);
    	Desktop obj = context.getBean(Desktop.class);
    }
}*/

/*public class App {
    public static void main( String[] args ) {
    	ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
//    	Alien obj1 = (Alien) context.getBean("alien1");
    	Alien obj1 = context.getBean("alien1", Alien.class);
    	System.out.println(obj1.getAge());
    	obj1.code();
    	
//    	Computer com = context.getBean(Computer.class);
    	
//    	Desktop obj = (Desktop) context.getBean("com2"); 
//    	Desktop obj = context.getBean("com2", Desktop.class);
//    	Desktop obj = context.getBean(Desktop.class);
    }
}*/

/*************************************************************************************************************/

// Java Based Configuration

/*public class App {
    public static void main( String[] args ) {
    	ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    	
    	Desktop dt = context.getBean(Desktop.class);
    	dt.compile();
    	
    }
}*/

/*public class App {
    public static void main( String[] args ) {
    	ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

//    	Desktop dt = context.getBean("desktop", Desktop.class); // Default bean name of Desktop class is "desktop1" which is method name
//    	Desktop dt = context.getBean("com2", Desktop.class); // calling our own bean name - com2
    	Desktop dt = context.getBean("Beast", Desktop.class); // calling Beast bean name which belongs to array of bean name
    	dt.compile();
    	
    }
}*/

/*public class App {
    public static void main( String[] args ) {
    	ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    	
    	Desktop dt = context.getBean(Desktop.class); 
    	dt.compile();
    	
    	Desktop dt1 = context.getBean(Desktop.class); 
    	dt1.compile();
    	
    }
}*/

/*public class App {
    public static void main( String[] args ) {
    	ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    	
    	Alien obj1 = context.getBean(Alien.class);
    	System.out.println(obj1.getAge());
    	obj1.code();
    	
//    	Desktop dt = context.getBean(Desktop.class); 
//    	dt.compile();
//    	
//    	Desktop dt1 = context.getBean(Desktop.class); 
//    	dt1.compile();
    	
    }
}*/

/*************************************************************************************************************/
// Stereotype Annotation 

public class App {
    public static void main( String[] args ) {
    	
    	ApplicationContext context = new AnnotationConfigApplicationContext(AppConfigAnnote.class);
   	  	Alien obj1 = context.getBean(Alien.class);
   	  	System.out.println("Age is : "+ obj1.getAge());
   	  	obj1.code();
   	  	
    }
    
}
