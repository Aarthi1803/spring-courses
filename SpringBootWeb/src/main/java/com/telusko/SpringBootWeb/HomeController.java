package com.telusko.SpringBootWeb;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/*@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String home() {
		System.out.println("home method called");
		return "index.jsp";
	}
}*/

/*************************************************************************************************************************************************************/

/*@Controller
public class HomeController {
	
@RequestMapping("/")
	public String home() {
		System.out.println("home method called");
		return "index.jsp";
	}

	@RequestMapping("add")
//	Using Servlet way - HttpServletRequest 
//  In "Servlet" if you want to accept data from client we use HttpServletRequest
	public String add(HttpServletRequest req) { //method name can be any name
		System.out.println("add method called");
		int num1 = Integer.parseInt(req.getParameter("num1"));
		int num2 = Integer.parseInt(req.getParameter("num2"));
		int result = num1 + num2;
		return "result.jsp";
	}

}*/

/*************************************************************************************************************************************************************/

/*@Controller
public class HomeController {
	@RequestMapping("/")
	public String home() {
		System.out.println("home method called");
		return "index.jsp";
	}	
	
	@RequestMapping("add")
//	Using Servlet way - HttpServletRequest and Session
//  In "Servlet" if you want to accept data from client we use HttpServletRequest
	public String add(HttpServletRequest req, HttpSession session) { //method name can be any name
		int num1 = Integer.parseInt(req.getParameter("num1"));
		int num2 = Integer.parseInt(req.getParameter("num2"));
		int result = num1 + num2;
// 		To maintain data when user navigates to different pages we use "Session". Since we are moving from index.jsp to result.jsp we need to maintain that data and pass data to another page so we use session. 
//		We are adding data into session by using setAttribute
		session.setAttribute("result", result); //1st parameter we pass variable name and 2nd parameter we pass variable value
		return "result.jsp";
	}
	
}*/

/*************************************************************************************************************************************************************/	
/*@Controller
public class HomeController {
	@RequestMapping("/")
	public String home() {
		System.out.println("home method called");
		return "index.jsp";
	}
	
	@RequestMapping("add")
//	Using Spring way
//  In "Spring" way we can get values from browser url by directly using same variable name we see in url and add them as parameters to the method instead of using HttpServletRequest
	public String add(int num1, int num2, HttpSession session) { //method name can be any name
		int result = num1 + num2; // not using HttpServletRequest so directly adding num1 and num2
// 		To maintain data when user navigates to different pages we use "Session". Since we are moving from index.jsp to result.jsp we need to maintain that data and pass data to another page so we use session. 
//		We are adding data into session by using setAttribute
		session.setAttribute("result", result); //1st parameter we pass variable name and 2nd parameter we pass variable value
		return "result.jsp";
	}
}*/

//@RequestMapping("add")
//Using Spring - @RequestParam
//In "Spring" we can get values from browser url by directly using same variable name we see in url and add them as parameters to the method instead of using HttpServletRequest
//url - http://localhost:8080/add?num1=10&num2=12 if we pass any other variable name as parameter to method instead of num1 and num2 like 
//add(int var1, int var2) we will get error. variable names in url and variables names in method as parameter must be same.
//To create own names while passing as parameter in method we use @RequestParam("num1") int var1 , @RequestParam("num2") int var2
//We must pass variable name we get in url to @RequestParam and assign it to our own created variable name in method 
//like @RequestParam("num1") value is assigned to var1 
//public String add(@RequestParam("num1") int var1 , @RequestParam("num2") int var2, HttpSession session) { .... }

/*************************************************************************************************************************************************************/	

/*@Controller
public class HomeController {
	@RequestMapping("/")
	public String home() {
		System.out.println("home method called");
		return "index.jsp";
	}
	
	@RequestMapping("add")
//	Using Spring - @RequestParam and Model
//	In "Spring" way to transfer data from Controller to View we use Model object. Instead of HttpSession object we can use Model object
	public String add(@RequestParam("num1") int var1 , @RequestParam("num2") int var2, Model model) { //method name can be any name
		int result = var1 + var2; 
		model.addAttribute("result", result); //1st parameter we pass variable name and 2nd parameter we pass variable value
		return "result.jsp";
	}
}*/

/*************************************************************************************************************************************************************/		
/*@Controller
public class HomeController {	
	
	@RequestMapping("/")
	public String home() {
		System.out.println("home method called");
		return "index"; // index.jsp is inside webapps/views and directly mentioning view name and removing .jsp file extension
	}
	
	@RequestMapping("add")
//	In "Spring way" to transfer data from Controller to View we use Model object. Instead of HttpSession object we can use Model object
	public String add(@RequestParam("num1") int var1 , @RequestParam("num2") int var2, Model model) { //method name can be any name
		int result = var1 + var2; 
		model.addAttribute("result", result); //1st parameter we pass variable name and 2nd parameter we pass variable value
		return "result"; // result.jsp is inside webapps/views and directly mentioning view name and removing .jsp file extension
	}
	
}*/	
//	 For the view we have multiple options like we got JSP, we got Free marker, we got Theme Leaf. 
//	 If we want to move from let's say JSP to Theme Leaf. In that case, you will have to go to each controller and 
//	 check where are we using this .JSP extension.
//   Next, Maybe I want to keep my jsp files not in the web app folder, but in new "views" folder. So I will create new folder called "views".
//   So we'll restart the application. And if I go back to the browser now let's say refresh the home page and 
//   even the home page you can see the URL I'm calling is not able to find the home page. Why the home page is not 
//   there because jsp is in different "views" folder. Plus we are not even specifying the extension.
//   So there is a component which helps you to resolve this and this component is called "View Resolver". 
//   So when you say I'm returning a result or index here - return "index"; or return "result";  Now "View Resolver" has no idea 
//   first of all where this file are located and what is extension. So we can talk to "View Resolver" to find files and what is their extension. 
//   And the way you can do that is with the help of a property file. When you configure your spring framework, Spring Boot basically 
//   gives you "application.properties". 
//   This is where you can mention the properties of "views" folder that has jsp files and file type extension which is .jsp
//
//   So prefix = views folder
//   suffix = .jsp
//
//   like spring.mvc.view.prefix = /views/
//   spring.mvc.view.suffix = .jsp
	
	
/*************************************************************************************************************************************************************/		
/*@Controller
public class HomeController {	
	@RequestMapping("/")
	public String home() {
		System.out.println("home method called");
		return "index"; // index.jsp is inside webapps/views and directly mentioning view name and removing .jsp file extension
	}
	
	@RequestMapping("add")
//	Using Spring - @RequestParam and ModelAndView
//	In "Spring" way to transfer data from Controller to View we use Model object. Instead of HttpSession object we can use Model object
	public ModelAndView add(@RequestParam("num1") int var1 , @RequestParam("num2") int var2, ModelAndView mv) { //method name can be any name
		int result = var1 + var2; 
		mv.addObject("result", result); //add result into ModelAndView object - 1st parameter we pass variable name and 2nd parameter we pass variable value
		mv.setViewName("result"); // When you add the data into ModelAndView object you have to also set the view name so that it goes to result.jsp
		return mv;
	}
}*/
	
//	 Model object is only for data. So we are using model object to add the data. And also we are specifying a view name in the result. 
//		public String add(Model model) { 
//		....
//		return "result" 
//		}
//
//	What if you can put that view name in the Model object itself. Instead of using "Model" object we can use "ModelAndView" object.
//	"ModelAndView" object will have two things the data which is Model as well as the view for page.
//	mv.addObject("result", result);  // add the data into Model object
//	
//	When you add the data you have to also set the view name.
//	mv.setViewName("result"); // mentioning to send data to view result.jsp
//	
//	So basically you can use a Model object if you just want to work with data. But if you want to return the view name as well use "ModelAndView" object.

/*************************************************************************************************************************************************************/	
/*@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String home() {
		System.out.println("home method called");
		return "index"; // index.jsp is inside webapps/views and directly mentioning view name and removing .jsp file extension
	}
	
//	@RequestMapping("add")
//	Using Spring - @RequestParam and ModelAndView. In "Spring" way to transfer data from Controller to View we use Model object. Instead of HttpSession object we can use Model object
//	public ModelAndView add(@RequestParam("num1") int var1 , @RequestParam("num2") int var2, ModelAndView mv) { //method name can be any name
//		int result = var1 + var2; 
//		mv.addObject("result", result); //add result into ModelAndView object - 1st parameter we pass variable name and 2nd parameter we pass variable value
//		mv.setViewName("result"); // When you add the data into ModelAndView object you have to also set the view name so that it goes to result.jsp
//		return mv;
//	}
	
	@RequestMapping("addAlien")
	public ModelAndView addAlien(@RequestParam("aid") int aid , @RequestParam("aname") String aname, ModelAndView mv) { //method name can be any name
		Alien alien = new Alien();
		alien.setAid(aid);
		alien.setAname(aname);
		mv.addObject("alien", alien); //add alien object into ModelAndView object - 1st parameter we pass object name and 2nd parameter we pass object value
		mv.setViewName("result"); // When you add the data into ModelAndView object you have to also set the view name so that it goes to result.jsp
		return mv;
	}
	
}*/	
	
//	Need for @ModelAttribute():
//	If I want to send data regarding an entity. And then I want to directly accept that entity as an object on the server side.
//	we have a alien class. I want to ask for a particular ID and name. So every alien will have two things.
//	Let's say I want to add a new entry for the alien. Alien is an entity which has two things ID and a name. 
//	And when you click on submit, you are requesting for add alien.
//	I need a controller for adding alien. But what about the parameters? I'm not sending num1, num2. 
//	Now basically what we're doing is we are accepting that particular alien and the values as ID and a name.  
//	In the Java world everything should be in the object.
//	We can create Alien POJO class with getters and setters and toString().
//	But then the data which I'm sending now is alien data. So of course in the result.jsp I have to make changes. 
//  I will print alien object in jsp. I'm calling the addAlien request and it goes to the controller addAlien(). 
//	It will accept these two values aid and aname, save that in two variables, aid and aname. 
//	So whatever data I have received here, I will just put that in the object and that object I'm sending to jsp.
	
//	But we have one little problem here. We are accepting value one by one. 
//	We are sending aid and aname data and then we have the Alien class which can represent these two values.
//	Let's say your class has ten variables.

//	In this particular controller you have to write ten times @RequestParam like @RequestParam("aid") int aid , @RequestParam("aname"), @@RequestParam("marks") int marks etc..
//	So can we just remove @RequestParam() and accept with one particular object using @ModelAttribute().

/*************************************************************************************************************************************************************/	
@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String home() {
		System.out.println("home method called");
		return "index"; // index.jsp is inside webapps/views and directly mentioning view name and removing .jsp file extension
	}
	
	@RequestMapping("addAlien")
//	Using Spring - @ModelAttribute
//	public String addAlien(@ModelAttribute("alien1") Alien alien) { // if we are creating our own object name alien1 we need to mention inside @ModelAttribute("alien1")
//	public String addAlien(@ModelAttribute Alien alien) { 	
	public String addAlien(Alien alien) { // @ModelAttribute is optional attribute here not mandatory to add this annotation 
		return "result";
	}
	 
	@ModelAttribute("course") // using @ModelAttribute at method level. @ModelAttribute can be used at method level and also by passing as parameter in method
	public String courseName() {
		return "Java";
	}
	
}	
	
	
//	 @ModelAttribute:
//		Instead of using @RequestParam() as method parameter for two variables @RequestParam("aid") int aid, @RequestParam("aname") String aname. 
//		What if I can simply use Alien alien as method parameter?
//		When then this alien data is coming from form, it will directly assign the value to the alien object. 
//		So basically I want spring to create alien object and then assign those two values which is coming 
//		from the browser request. So to achieve that we have to use something called a @ModelAttribute. 
//		Now this is the annotation which is responsible to assign the value to the alien object. 
//		And instead of ModelAndView let's return a string so you don't have to return the ModelAndView object. 
//		So here we can mention I'm calling the result page using - return "result";.
//		So instead of alien which I'm printing in jsp page. What if I want to print alien1?
//		So we need to use @ModelAttribute("alien1") in public String addAlien(@ModelAttribute("alien1") Alien alien) {...} so that we can use in jsp page as 
//		${alien1} So basically if you want to have a different name than alien then you have to mention that using @ModelAttribute("alien1").
//		
//		But what if it is the same name? like @ModelAttribute("alien") Alien alien
//		So basically the @ModelAttribute is optional. So when you don't want to have a different name like Alien1, you can completely skip @ModelAttribute. 
//		And that's why most of the time you will not see @ModelAttribute as annotation, because most of the time we are using the same name. 
//		But if you want to have a different name here, then we have to use @ModelAttribute("alien1") Alien alien
//		
//		Not just that, we can also use @ModelAttribute on method level. Basically we have used that for the method arguments.
//		In jsp we have <p> Welcome to the ${course} World </p>
//		
//		But what if I want to print different names for different courses. Let's say for blockchain it should be welcome to the blockchain world. 
//		Welcome to the AI world. Welcome to the dotnet world. So I want this to be dynamic. We can create a separate method which will return the course names.
//		
//		@ModelAttribute("course") // using @ModelAttribute at method level. Can be used at method level and by passing as parameter in method
//		public String courseName() {
//			return "Java";
//		}
//		I want to return Java here and you can return whatever you want. So we can use model attribute on top of the method as well.


/*************************************************************************************************************************************************************/	
	
