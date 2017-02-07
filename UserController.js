'use strict';
app.controller('UserController',['$http','$scope','UserService','$location','$rootScope','$cookieStore',
                                 function($http,$scope,UserService,$location,$rootScope,$cookieStore){
                                 console.log("UserController....")
	                             var self=this;
                                 self.user={
                                		 user_id:'',
                                		 name:'',
                                		 email:'',
                                		 address:'',
                                		 mobile_no:'',
                                		 password:'',
                                		 role:'',
                                		 isOnline:'',
                                		 gender:'',
                                		 status:'',
                                		 errormessage:'',
                                		 errorcode:''
                                 };
                  
                       self.users=[];
                         self.submit=function(){
                        	 self.createUser(self.user);
                         };
                        	 self.createUser=function(user){
                        		 UserService.createUser(user).then( 
                        					 console.log('user is created'),
                        					 self.fetchAllUsers,
                        					 function(errResponse)
                        					 {
                        						 console.error('user is not created');
                        					 }
                        					 )
                        					 
                        	 }; 
                        	 
                        	 self.authenticateUser=function(user){
                        		 UserService.authenticateUser(user).then( 
                        					 console.log('user is created'),
                        					 function(d)
                        					 {
                        						self.user=d;
                        						console.log("user.errorcode:"+ self.user.errorcode)
                        						if(self.user.errorcode=="404")
                        							{
                        							alert("Invalid credentials")
                        							self.user.user_id="";
                        							self.user.password="";
                        							}
                        						else{
                        							console.log("Valid user")
                        							$rootScope.currentUser={user_id:self.user.user_id,
                                               		 name:self.user.name,
                                            		 email:self.user.email,
                                            		 address:self.user.address,
                                            		 mobile_no:self.user.mobile_no,
                                            		 password:self.user.password,
                                            		 role:self.user.role,
                                            		 isOnline:self.user.isOnline,
                                            		 gender:self.user.gender,
                                            		 status:self.user.status
                                            		 
                        							};
                        							//$http.defaults.headers.common['Authorization']='Basic'+$rootScope.currentUser;
                        							$cookieStore.put('currentUser',$rootScope.currentUser);
                        							$location.path("/");
                        						}
                        						
                        					 },
                        					 function(errResponse)
                        					 {
                        						 console.error('user is not created');
                        					 }
                        					 );
                        					 
                        	 }; 
                        	 
                        	self.fetchAllUsers=function(){
                        		UserService.fetchAllUsers().then(function(d){
                        			self.users=d;
                        		},
                        		function(errResponse){
                        			console.error('error while fetching Users');
                        		}
                        		)
                        	};
                        	
                        	self.fetchAllUsers();
                        				 
}]); 
                        				 
                        		 
                        	
                         
                         
                                
                      
                                 
                                 
                                 
                                 
                                 
                                 
                                
                             	