package com.niit.colloboration.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.colloboration.model.Blog;
import com.niit.colloboration.model.User;
import com.niit.collobration.DAO.BlogDAO;
@RestController
public class BlogController {
	@Autowired
	private BlogDAO blogDAO;
	
	@Autowired
	private Blog blog;
	
	@RequestMapping(value = "/blogs" , method = RequestMethod.GET)
	public ResponseEntity<List<Blog>> getblogs()
	{
		List<Blog> blogs = blogDAO.list();
		if(blogs == null)
		{
			blog = new Blog();
			 blog.setErrorCode("404");
       	  blog.setErrorMessage("No blogs are available");
       	  return new ResponseEntity<List<Blog>>(HttpStatus.NO_CONTENT);
		}
		 else
         {
       	  return new ResponseEntity<List<Blog>>(blogs,HttpStatus.OK);
         }
	}
	
	@RequestMapping(value = "/blog/{id}" , method = RequestMethod.GET)
	public ResponseEntity<Blog> getBlog(@PathVariable("id")String id){
		
		Blog blog = blogDAO.get(id);
		if(blog == null)
		{
			blog = new Blog();
			blog.setErrorCode("404");
			blog.setErrorMessage("Blog not found with the id"+ id);
		}
		
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/blog/" , method = RequestMethod.POST)
	public ResponseEntity<Blog> createBlog(@RequestBody Blog blog, HttpSession httpsession) {
		
   		String loggedInuserID = (String) httpsession.getAttribute("loggedInUserID");
	
		blog.setUser_id(loggedInuserID);
		
		
		blogDAO.save(blog);
		
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}
	 @RequestMapping(value = "/blog" , method = RequestMethod.PUT)
	   public ResponseEntity<Blog> updateblog (@RequestBody Blog blog)
	   {
		   
		   //Logger.debug("->->-> calling method UpdateUser");
		 
		   if(blogDAO.update(blog))
		   {
		   blog.setErrorCode("200");
		   blog.setErrorMessage("Blog updated successfully with id "+ blog.getBlog_id());
		   }
		   else
		   {
			   blog.setErrorCode("404");
			   blog.setErrorMessage("Blog not updated successfully with id "+ blog.getBlog_id()+"Contact admin");
		   }
		 
		   return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	   }
	@RequestMapping(value="/blog/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Blog> deleteBlog(@PathVariable String id) {
		
		
		if(blogDAO.get(id)!=null) {
			blogDAO.delete(id);
			blog.setErrorCode("200");
			blog.setErrorMessage("Deleted");
		}
		
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
	}

	
}	

