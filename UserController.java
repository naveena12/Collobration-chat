package com.niit.colloboration.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.tinkerpop.shaded.minlog.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.colloboration.model.User;
import com.niit.collobration.DAO.UserDAO;

@RestController
public class UserController {


    private static final Logger Logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	User user;
	
	/*@Autowired
	FriendDAO friendDAO;*/
	
	@RequestMapping(value="/users",method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers()
	{
		Logger.debug("->->->calling method listAllUsers");
		List<User> users = userDAO.list();
		
		if(users.isEmpty()||users==null)
		{
			user=new User();
			user.setErrorCode("404");
			 user.setErrorMessage("no users are avaliable");
			
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	
	
	 @RequestMapping(value="/user/", method = RequestMethod.POST)
	   public ResponseEntity<User> register(@RequestBody User user)
	   {
		   Logger.debug("Starting the method register");
		   
		   if(userDAO.get(user.getUser_id()) != null)
		   {//User Exist with this id.
			   
			   user.setErrorCode("404"); 
			   user.setErrorMessage("User already exist with the id :" + user.getUser_id());
		   }
		   else
		   {
			   
			   user.setStatus('N');
			   user.setIsOnline('N');
			   Logger.debug("Going to save in DB");
			   
			   boolean flag = userDAO.save(user);
			   
			   if(flag==false)
			   {
				   Logger.debug("Not able to register, please contact admin");
				   user.setErrorCode("404");
				   user.setErrorMessage("Not able to register, Please contact admin");
			   }
			   else
			   {
				   user.setErrorCode("200");
				   user.setErrorMessage("Registered successfully");
			   }
		   }
		   
		  /* MultipartFile file=user.getImage();
			String rootDirectory=request.getSession().getServletContext().getRealPath("/");
			path=(Path) Paths.get(rootDirectory + "\\resources\\images\\"+user.getId()+".jpg");
			
			
			if(file!=null && !file.isEmpty()){
				try{
					file.transferTo(new File(path.toString()));
					System.out.println("Image Uploaded to Product.....");
				}catch(Exception e)
				{
					e.printStackTrace();
					throw new RuntimeException("image saving failed ",e);
				}
			}
			
			FileUtil.upload(path.toString(), file, user.getId() + ".jpg");
		   */
		   Logger.debug("Ending the method register");
		   
		   return new ResponseEntity<User>(user , HttpStatus.OK);  
	   }
	
  
   
   @RequestMapping(value = "/user/{id}" , method = RequestMethod.DELETE)
   public ResponseEntity<User> deleteuser (@PathVariable("id") String id, @RequestBody User user)
   {
	   Logger.debug("->->-> calling method deleteUser");
	   if(userDAO.get(id) == null)
	   { 
		   Logger.debug("->->->->User does not exist with id " +id);
		   user = new User();
		   user.setErrorMessage("User does not exist with id ");
		   return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
	   }
	   user.setErrorCode("200");
	   user.setErrorMessage("User Deleted Successfully with id "+user.getUser_id());
	   userDAO.delete(id);
	   
	   Logger.debug("->->->User Deleted Successfully");
	   return new ResponseEntity<User>(user, HttpStatus.OK); 
   }

   @RequestMapping(value = "/user" , method = RequestMethod.PUT)
   public ResponseEntity<User> updateuser (@RequestBody User user)
   {
	   
	   Logger.debug("->->-> calling method UpdateUser");
	   /*user = userDAO.get(id);
	   if(user== null)
	   { 
		   Logger.debug("->->->->User does not exist with id "+ user.getUser_id());
		   user = new User();
		   user.setErrorCode("404");
		   user.setErrorMessage("User does not exist with id "+ user.getUser_id());
		   return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
	   }
	   else*/
	   
	   //boolean flag=userDAO.update(user);
	   //Log.debug("Status of update is "+flag);
	   if(userDAO.update(user))
	   {
	   user.setErrorCode("200");
	   user.setErrorMessage("User updated successfully with id "+ user.getUser_id());
	   }
	   else
	   {
		   user.setErrorCode("404");
		   user.setErrorMessage("User not updated successfully with id "+ user.getUser_id()+"Contact admin");
	   }
	   //}
	   return new ResponseEntity<User>(user, HttpStatus.OK);
   }
   
   @RequestMapping(value="/user/{id}",method = RequestMethod.GET)
	public ResponseEntity<User> getuser(@PathVariable("id") String id)
	{
	   Logger.debug("->->-> calling method getUser");
	   Logger.debug("->->->->"+id);
	   User user = userDAO.get(id);
	   if(userDAO.get(id) == null)
	   { 
		   Logger.debug("->->->->User does not exist with id " +id);
		   user = new User();
		   user.setErrorMessage("User does not exist with id ");
		   return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
	   }
	   Logger.debug("->->->->User exist with id " +id);
	   return new ResponseEntity<User>(user, HttpStatus.OK); 
	}
   
 
   
   @RequestMapping(value = "/user/authenticate/" , method  = RequestMethod.POST)
   public ResponseEntity<User> login(@RequestBody User user , HttpSession httpSession)
   {
	   Logger.debug("->->-> calling method authencate"); 
	   user = userDAO.validate(user.getUser_id() , user.getPassword());
	   
	   if (user == null) {
		   user = new User();// to avoid NLP (NullPointerException)
		   user.setErrorCode("404");
		   user.setErrorMessage("Invalid Credentials Please try again...");
	   }
	   else
	   {  
		   // Store userId in HttpSession
		   
		   //If the registration not approved
		   
		   if(user.getStatus() == 'R')
		   {
			   user.setErrorCode("404");
			   user.setErrorMessage("Your registration is not approved. Please contact Admin");
		   }
		   else
		   {
			   httpSession.setAttribute("loggedInUser", user);
			   httpSession.setAttribute("loggedInUserID", user.getUser_id());
			   user.setIsOnline('Y');
			   
			   if(userDAO.update(user) == true)
			   {
				   user.setErrorCode("200");
				   user.setErrorMessage("You successfully logged and updated the status as y");
			   }
			   else
			   {
				   user.setErrorCode("404");
				   user.setErrorMessage("Not able to do the operation");
			   }
  
		   }
		   
	   }
	   
	  return new ResponseEntity<User>(user , HttpStatus.OK); 
   }
   
   
   @RequestMapping(value="/user/logout/" , method = RequestMethod.GET)
   public  ResponseEntity<User> logout(HttpSession session) 
   {
	   Logger.debug("->->->->calling method logout");
	   String loggedInUserID = (String) session.getAttribute("loggedInUserID");
	   
	   Logger.debug("loggedInUserID : " + loggedInUserID);
	   user = userDAO.get(loggedInUserID);
	   
	   user.setIsOnline('N');
	  
	   session.invalidate();
	   
	  Logger.debug("->->->->calling method logout function");
	 
	  if(userDAO.update(user)){
		  user.setErrorCode("200");
		  user.setErrorMessage("you have logged out successgully");
	  }else
	  {
		  user.setErrorCode("404");
		  user.setErrorMessage("you have not logged out successgully");
	  }
	  Logger.debug("ending the method logout");
	  
	  return new ResponseEntity<User>(user, HttpStatus.OK);
   }
   
   @RequestMapping(value="/user/makeadmin/{id}" , method = RequestMethod.POST)
   public  ResponseEntity<User> makeadmin(@PathVariable("id") String id,@RequestBody User user) 
   {
	   user = userDAO.get(id);
	   user.setRole("admin");
	   
	   userDAO.update(user);
	   
	   return new ResponseEntity<User>(user, HttpStatus.OK);
   }
  
   @RequestMapping(value="/useraccept/{id}" , method = RequestMethod.PUT)
   public  ResponseEntity<User> useraccept(@PathVariable("id") String id, @RequestBody User user ) 
   {
	   
	  user = userDAO.get(user.getUser_id());
	  if(user==null)
	  {
		  Logger.debug("->->->->User does not exist with id "+ user.getUser_id());
		   user = new User();
		   user.setErrorMessage("User does not exist with id "+ user.getUser_id());
		   return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
	  }
	  
	   user.setStatus('A');
	   
	   userDAO.update(user);
	   
	   return new ResponseEntity<User>(user, HttpStatus.OK);
   }
  

   
   @RequestMapping(value="/userreject/{id}" , method = RequestMethod.PUT)
   public  ResponseEntity<User> userreject(@PathVariable("id") String id, @RequestBody User user ) 
   {
	   
	  user = userDAO.get(user.getUser_id());
	  if(user==null)
	  {
		  Logger.debug("->->->->User does not exist with id "+ user.getUser_id());
		   user = new User();
		   user.setErrorMessage("User does not exist with id "+ user.getUser_id());
		   return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
	  }
	  
	   user.setStatus('R');
	   
	   userDAO.update(user);
	   
	   return new ResponseEntity<User>(user, HttpStatus.OK);
   }
   
   
}
