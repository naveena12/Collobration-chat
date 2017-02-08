'use strict';
app.controller('JobController',['$http','$scope','JobService','$location','$rootScope',
                                 function($http,$scope,JobService,$location,$rootScope){
                                 console.log("JobController....")
	                             var self=this;
                                 self.job={
                                		 job_id:'',
                                		 job_title:'',
                                         domain:'',
                                         qualification:'',
                                         experience:'',
                                         location:'',
                                         vacancy:'',
                                         interview_date:'',
                                         salary:''
                                 };
                                 self.job_application={
                                		 job_app_id:'',
                                		 user_id:'',
                                		 job_id:'',
                                		 app_date:'',
                                		 status:''
                                 };
                                         
                                		 
                                		 
                                                 
                  
                       self.jobs=[];
                         self.submit=function(){
                        	 self.createjob(self.job);
                         };
                        	 self.createjob=function(job){
                        		 JobService.createJob(job).then( 
                        					 console.log('job is  created'),
                        					 self.fetchAllJobs,
                        					 function(errResponse)
                        					 {
                        						 console.error('job is not created');
                        					 }
                        					 )
                        					 
                        	 }; 
                        	 
                        	self.fetchAllJobs=function(){
                        		JobService.fetchAllJobs().then(function(d){
                        			self.jobs=d;
                        		},
                        		function(errResponse){
                        			console.error('error while fetching Jobs');
                        		}
                        		)
                        	};
                        			
                        	 self.job_applications=[];
                             self.submit1=function(){
                            	 self.createjob_application(self.job_application);
                             };
                            	 self.createjob_application=function(job_application){
                            		 JobService.createJob_application(job_application).then( 
                            					 console.log('job_app is created'),
                            					 self.fetchAllJobs,
                            					 function(errResponse)
                            					 {
                            						 console.error('job_app is not created');
                            					 }
                            					 )
                            					 
                            	 }; 
                            	 
                            	self.fetchAllJobs_app=function(){
                            		JobService.fetchAllJobs_app().then(function(d){
                            			self.job_apps=d;
                            		},
                            		function(errResponse){
                            			console.error('error while fetching Jobs_app');
                            		}
                            		)
                            	};
                            	//self.fetchAllJobs_app();
                            	
                            				 
}]); 
                        				 
                        		 
                        	
                         
                         
                                
                      
                                 
                                 
                                 
                                 
                                 
                                 
                                
                             	