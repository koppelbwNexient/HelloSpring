package com.tutorialspoint;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
	public static void main(String[] args) {
		
	      ApplicationContext context = 
	             new ClassPathXmlApplicationContext("Beans.xml");

	      // Singleton
	      HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
	      obj.getMessage();
	      
	      // Prototype
	      HelloWorld test01Beans = (HelloWorld) context.getBean("test01");
	      test01Beans.getTest01Message();
	      
	      
	      // Demonstrates Singleton Scope
	      System.out.println("\n\nSINGLETON");
	      HelloWorld objA = (HelloWorld) context.getBean("helloWorld");
	      objA.setMessage("I'm object A");
	      objA.getMessage();

	      HelloWorld objB = (HelloWorld) context.getBean("helloWorld");
	      objB.getMessage();
	      objB.setMessage("ObjB also points here and can change it!");
	      objB.getMessage();
	      
	      // Demonstrates Prototype scope
	      System.out.println("\n\nPROTOTYPE");
	      HelloWorld objC = (HelloWorld) context.getBean("test01");
	      objC.setMessage("I'm object C");
	      objC.getMessage();
	      
	      HelloWorld objD = (HelloWorld) context.getBean("test01");
	      objD.getMessage();
	      objD.setMessage("That was null because I am a different object than ObjC!!");
	      objD.getMessage();
	      
	      
	      // Demonstrates Inheritance
	      System.out.println("\n\nINHERITANCE");
	      HelloWorld objParent = (HelloWorld) context.getBean("parentBean");
	      objParent.getMessage1();
	      objParent.getMessage2();
	      
	      HelloWorld objChild = (HelloWorld) context.getBean("childBean");
	      objChild.getMessage1();
	      objChild.getMessage2();
	      objChild.getMessage3();
	      
	      HelloWorld objAbstract = (HelloWorld) context.getBean("templateUser");
	      objAbstract.getMessage1();
	      objAbstract.getMessage2();
	      objAbstract.getMessage3();
	      
	      
	      // Dependency/Object Injection
	      System.out.println("\nDEPENDENCY INJECTION");
	      Triangle triangle = (Triangle) context.getBean("triangle");
	      triangle.draw();
	      
	      
	      // DI Collections(List, Map, etc..)
	      System.out.println("\nDI COLLECTIONS");
	      Square sqr = (Square) context.getBean("square");
	      sqr.draw();
	      
	      
	      // AUTOWIRING
	      System.out.println("\nAUTO WIRING");
	      AutoWiredTriangle awTriangle = (AutoWiredTriangle) context.getBean("autoWiredTriangle");
	      awTriangle.draw();
	      
	      
	      ((AbstractApplicationContext) context).registerShutdownHook();
	   }
}
/*
 * http://www.tutorialspoint.com/spring/spring_ioc_containers.htm
 
  ----------------
  SPRING CONTAINER
  ----------------
  The Spring container is at the core of the Spring Framework.
   The container will create the objects, wire them together, configure them, 
   and manage their complete lifecycle from creation till destruction. 
   The Spring container uses dependency injection (DI) to manage the components 
   that make up an application. These objects are called Spring Beans which we 
   will discuss in next chapter.

The container gets its instructions on what objects to instantiate, configure, 
and assemble by reading configuration metadata provided. The configuration metadata 
can be represented either by XML, Java annotations, or Java code. The following diagram 
is a high-level view of how Spring works. The Spring IoC container makes use of Java POJO classes 
and configuration metadata to produce a fully configured and executable system or application.


*Spring provides following two distinct types of containers.
*	1. Spring BeanFactory Container
		basic support for DI 

* **2. Spring ApplicationContext Container
		adds more enterprise-specific functionality such as 
		the ability to resolve textual messages from a properties 
		file and the ability to publish application events to interested 
		event listeners. 
		****The ApplicationContext container includes all functionality of the BeanFactory container, so it is generally recommended over the BeanFactory


 --------
 THE BEAN
 --------
* The objects that form the backbone of your application and that are managed by the Spring IoC container are called beans. 
  A bean is an object that is instantiated, assembled, and otherwise managed by a Spring IoC container. These beans are created with 
  the configuration metadata that you supply to the container, for example, in the form of XML 

*The bean definition contains the information called configuration metadata which is needed for the container to know the followings:
	How to create a bean
	Bean's lifecycle details
	Bean's dependencies
	
*All the above configuration metadata translates into a set of the following properties that make up each bean definition.
	class, name, scope, properties, etc...

*In Spring, you can use init-method and destroy-method as attribute in bean configuration 
	file for bean to perform certain actions upon initialization and destruction
	
 ----------
 BEAN SCOPE
 ----------
* The Spring Framework supports following five scopes, three of which are available only if you use a web-aware ApplicationContext.
	singleton		This scopes the bean definition to a single instance per Spring IoC container (default).
	prototype		This scopes a single bean definition to have any number of object instances.
	request			This scopes a bean definition to an HTTP request. Only valid in the context of a web-aware Spring ApplicationContext.
	session			This scopes a bean definition to an HTTP session. Only valid in the context of a web-aware Spring ApplicationContext.
	global-session	This scopes a bean definition to a global HTTP session. Only valid in the context of a web-aware Spring ApplicationContext.
	
	
* ((As a rule, use the prototype scope for all state-full beans and the singleton scope for stateless beans.))
	
* Singleton Scoped Bean
	Spring IoC container creates exactly one instance of the object defined by that bean definition.
	((DEFAULT SCOPE IS ALWAYS SINGLETON))
	
* Prototype Scroped Bean
	Spring IoC container creates new bean instance of the object every time a request for that specific bean is made
	
	
 ---------------
 BEAN LIFE CYCLE
 ---------------
 
 * When a bean is instantiated, it may be required to perform some initialization to get it into a usable state.
   Similarly, when the bean is no longer required and is removed from the container, some cleanup may be required.
 
 * The init-method attribute specifies a method that is to be called on the bean immediately upon instantiation. 
   Similarly, destroy-method specifies a method that is called just before a bean is removed from the container.
 
 * Initialization

 * Destroy

 * Default Init and Destroy attributes
 	add this into the bean file header
 		default-init-method="init" 
    	default-destroy-method="destroy"



 --------------------
 BEAN POST PROCESSORS
 --------------------
 
 * The BeanPostProcessor interface defines callback methods that you can implement to provide your own instantiation
   logic, dependency-resolution logic etc. You can also implement some custom logic after the Spring container finishes 
   instantiating, configuring, and initializing a bean by plugging in one or more BeanPostProcessor implementations.

 * You can configure multiple BeanPostProcessor interfaces and you can control the order in which these BeanPostProcessor 
   interfaces execute by setting the order property provided the BeanPostProcessor implements the Ordered interface.

 * The BeanPostProcessors operate on bean (or object) instances which means that the Spring IoC container instantiates a 
   bean instance and then BeanPostProcessor interfaces do their work.

 * An ApplicationContext automatically detects any beans that are defined with implementation of the BeanPostProcessor 
   interface and registers these beans as post-processors, to be then called appropriately by the container upon bean creation.


 ----------------
 BEAN INHERITANCE
 ----------------

 * A child bean definition inherits configuration data from a parent definition. 
   The child definition can override some values, or add others, as needed.
  
 * Spring Bean definition inheritance has nothing to do with Java class inheritance but inheritance concept is same. 
   You can define a parent bean definition as a template and other child beans can inherit required configuration from the parent bean.
 
 * Abstract Bean class
 	Define class as abstract
 
 
 -------------------------
 DEPENDANCY INJECTION (DI)
 -------------------------
 
 * http://javabrains.koushik.org/courses/spring_core



CONSTRUCTOR INJECTION
INJECTING OBJECTS

 ------------------------------
 ANNOTATION BASED CONFIGURATION
 ------------------------------
 
 * Annotation injection is performed before XML injection, thus the latter configuration will override the former for properties wired through both approaches.
 
 





*/