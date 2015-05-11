package com.tutorialspoint;

public class HelloWorld {
	private String message;
	private String test01Message;
	private String message1, message2, message3;
	
	   public void setMessage(String message){
	      this.message  = message;
	   }
	   public void getMessage(){
	      System.out.println("Your Message : " + message);
	   }
	   
	   /* test01Message variable must match to the Bean's 'property' name.
	    * The Bean's 'value' is what will be sent back here*/
	   public void setTest01Message(String test01Message) {
		   this.test01Message = test01Message;
	   }
	   public void getTest01Message() {
		   System.out.println("Test01 message : " + test01Message);
	   }
	   
	   // In the case of XML-based configuration metadata, you can use the init-method attribute to specify 
	   // the name of the method that has a void no-argument signature
	   public void init() throws Exception {
		   // do some initialization work
		   System.out.println("Init method after properties are set : " + message);
		}
	   
	   public void initTest01() throws Exception {
		   System.out.println("Initializing test01 Object . . . " + test01Message);
	   }
	   
	   // In the case of XML-based configuration metadata, you can use the destroy-method attribute to specify 
	   // the name of the method that has a void no-argument signature
	   public void destroy() throws Exception {
		   System.out.println("Spring Container (BEAN) will destroy now!");
	   }
   
	   
	   public void setMessage1(String message){
		   this.message1  = message;
	   }
	   public void getMessage1(){
		   System.out.println("Message1 : " + message1);
	   }
	   public void setMessage2(String message){
		   this.message2  = message;
	   }
	   public void getMessage2(){
		   System.out.println("Message2 : " + message2);
	   }
	   public void setMessage3(String message){
		   this.message3  = message;
	   }
	   public void getMessage3(){
		   System.out.println("Message3 : " + message3);
	   }
}
