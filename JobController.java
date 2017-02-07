package com.niit.colloboration.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.niit.colloboration.model.Job;
import com.niit.colloboration.model.Job_application;
import com.niit.collobration.DAO.JobDAO;
import com.niit.collobration.DAO.Job_applicationDAO;

@RestController
public class JobController {

	private static final Logger logger = LoggerFactory.getLogger(JobController.class);
	
	@Autowired
	JobDAO jobDAO;
	
	@Autowired
	Job_applicationDAO jobappDAO;
	@Autowired
	Job job;
	
	@Autowired
	Job_application job_application;
	
	@RequestMapping(value = "/getAllJobs/" , method = RequestMethod.GET)
	public ResponseEntity<List<Job>> getjobs()
	{
		List<Job> jobs = jobDAO.list();
		if(jobs == null)
		{
			job = new Job();
			 job.setErrorCode("404");
       	  job.setErrorMessage("No blogs are available");
       	  return new ResponseEntity<List<Job>>(HttpStatus.NO_CONTENT);
		}
		 else
         {
       	  return new ResponseEntity<List<Job>>(jobs, HttpStatus.OK);
         }
	}
	
	@RequestMapping(value = "/getAllJobsApplication/" , method = RequestMethod.GET)
	public ResponseEntity<List<Job_application>> getjobsapplied1()
	{
	
		List<Job_application> jobapplied = jobappDAO.list();
		if(jobapplied == null)
		{
			job = new Job();
			 job.setErrorCode("404");
       	  job.setErrorMessage("No jobapplied are available");
       	  return new ResponseEntity<List<Job_application>>(HttpStatus.NO_CONTENT);
		}
		 else
         {
       	  return new ResponseEntity<List<Job_application>>(jobapplied, HttpStatus.OK);
         }
	}
	
	
	@RequestMapping(value = "/getJobsApplication/{jobid}" , method = RequestMethod.GET)
	public ResponseEntity<List<Job_application>> getjobsapplied(@PathVariable("jobid") int jobid)
	{
	 List<Job_application> jobapplied= jobappDAO.applicationsforjob(jobid);
	if(jobapplied== null)
	{
		job.setErrorCode("404");
		job.setErrorMessage("list of jobapplications is empty");
	}
	else{

		job.setErrorCode("200");
		job.setErrorMessage("List of jobapplication");
	}
		
       	  return new ResponseEntity<List<Job_application>>(jobapplied,HttpStatus.OK);
		}
		
         
	/*@RequestMapping(value="/getMyAppliedJobs/" , method = RequestMethod.GET)
	public ResponseEntity<List<Job>> getMyAppliedJobs(HttpSession httpSession) {
		logger.debug("Starting of the method getMyAppliedJobs");
		String loggedInUserID =  (String) httpSession.getAttribute("loggedInUserID");
		
		List<Job> job = jobDAO.getAllJobsApplication();
		List<Job> jobapplication = (List<Job>) jobDAO.getAllJobsApplication();
		return new ResponseEntity<List<Job>>(job , HttpStatus.OK);
	}*/
	
	@RequestMapping(value = "/applyForJob/{jobID}", method = RequestMethod.POST)
	public ResponseEntity<Job_application> applyforJob(@PathVariable("jobID") int jobID, HttpSession httpSession)
	{
		logger.debug("Starting of the method getMyAppliedJobs");
		String loggedInUserID = (String) httpSession.getAttribute("loggedInUserID");
		job=jobDAO.get(jobID);
		//jobApplication.setId();
		job_application.setUser_id(loggedInUserID);
	    job_application.setJob_id(job.getJob_id());
		job_application.setStatus("N");
		job_application.setApp_date(null);
		if (jobappDAO.save(job_application)==false)
		{
			job_application.setErrorCode("404");
			job_application.setErrorMessage("Not able to apply for the job");
			
		}
		else
		{
			job_application.setErrorCode("200");
			job_application.setErrorMessage("You have applied for the job "+job.getJob_id());
		}
		 return new ResponseEntity<Job_application> (job_application , HttpStatus.OK);
	}

	
	/*@RequestMapping(value="/getAllJobDetails/{jobID}", method  = RequestMethod.PUT)
	public ResponseEntity<Job> getAllJobDetails(@RequestParam("jobID")String jobID, HttpSession httpSession){
		logger.debug("Starting of the method getAllJobDetails");
		String loggedInUserID =  (String) httpSession.getAttribute("loggedInUserID");
		
		job_application = jobDAO.getJob_application(jobID);
		job_application.setUser_id(loggedInUserID);
		job_application.setStatus("N");
		if(jobappDAO.save(job_application)) {
			job.setErrorCode("404");
			job.setErrorMessage("Not able to apply for the job");
			logger.debug("Not able to apply for the job");
		}
		return new ResponseEntity<Job>(job , HttpStatus.OK);
	}*/
	
	@RequestMapping(value="/selectUser/{user_id}/{jobID}", method = RequestMethod.PUT)
	public ResponseEntity<Job_application> selectUser(@PathVariable("user_id")String user_id,@PathVariable("jobID")int jobID){
		logger.debug("Starting of the method selectUser");
		job_application=jobappDAO.get(user_id, jobID);
		if(job_application.getStatus()=="N")
		job_application.setStatus("A");
		if(jobappDAO.update(job_application)==false) {
			job_application.setErrorCode("404");
			job_application.setErrorMessage("Not able to change the application status as 'selected'");
			logger.debug("Not able to change the application status as 'selected'");
		}
		else
		{
			job_application.setErrorCode("200");
			job_application.setErrorMessage("able to change the application status as 'selected'");
		}
		return new ResponseEntity<Job_application>(job_application, HttpStatus.OK);
	}
	
	@RequestMapping(value="/canCallForInterview/{userID}/{jobID}",method = RequestMethod.PUT)
	public ResponseEntity<Job_application> callForInterview(@PathVariable("userID")String userID, @PathVariable("jobID")int jobID){
		logger.debug("Starting of the method canCallForInterview");
		job_application=jobappDAO.get(userID, jobID);
		job_application.setStatus("C");
		if(jobappDAO.update(job_application)==false){
			job_application.setErrorCode("404");
			job_application.setErrorMessage("Not able to change the job application status 'Call for Interview'");
			logger.debug("Not able to change the job application status 'Call for Interview'");
		}
		else
		{
			job_application.setErrorCode("200");
			job_application.setErrorMessage("able to change the application status as 'call for interview'");
		}
		return new ResponseEntity<Job_application>(job_application, HttpStatus.OK);
	}
	
	@RequestMapping(value="/rejectJobApplcation/{userID}/{jobID}",method= RequestMethod.PUT)
	public ResponseEntity<Job> rejectJobApplication(@PathVariable("userID")int userID , @PathVariable("jobID")int jobID){
		logger.debug("Starting of the method rejectJobApplication");
		//jobApplication = jobDAO.getJobApplication(userID, jobID);
		
		job_application.setStatus("R");
		if(jobappDAO.update(job_application) ==false) {
			job_application.setErrorCode("404");
			job_application.setErrorMessage("Not able to reject the application");
			logger.debug("Not able to reject the application");
		}
		else
		{
			job_application.setErrorCode("200");
			job_application.setErrorMessage("Successfully updated the status as Rejected");
		}
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}
	
	@RequestMapping(value="/postAJob/", method = RequestMethod.POST)
	public ResponseEntity<Job> postAJob(@RequestBody Job job) {
		logger.debug("Starting of the method postAJob");
		
		//String loggedInuserID =  (String) httpsession.getAttribute("loggedInUserID");
		
		
		
		
		if(jobDAO.save(job)) {
			job.setErrorCode("200");
			job.setErrorMessage("Job is uploaded");
			
		}
		else
		{
			job.setErrorCode("404");
			job.setErrorMessage("Not able to post a job");
			logger.debug("Not able to post a job");
		}
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}	
	
	
	 /*@RequestMapping(value="/jobaccept/{id}" , method = RequestMethod.PUT)
	   public  ResponseEntity<Job_application> jobaccept(@PathVariable("id") int id, @RequestBody Job_application jobApplication ) 
	   {
		 jobApplication = jobDAO.getJobApplication(jobApplication.getId());  
		 
		  if(jobApplication==null)
		  {
			 
			  jobApplication = new Job_application();
			  jobApplication.setErrorMessage("User does not exist with id "+ job_application.getId());
			   return new ResponseEntity<Job_application>(job_application, HttpStatus.NOT_FOUND);
		  }
		  
		  jobApplication.setStatus("A");
		  jobApplication.setRemarks("C");
		  jobDAO.update Job_application(job_application); 
		  
		   
		   return new ResponseEntity<Job_application>(job_application, HttpStatus.OK);
	   }*/
}
