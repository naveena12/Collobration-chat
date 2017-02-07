'use strict';
app.controller('FriendController',['$scope','UserService','FriendService','$location','$rootScope',
                                 function($scope,UserService,FriendService,$location,$rootScope){
                                 console.log("FriendController....")
	                             var self=this;
                                 self.friend={
                                		 id:'',
                                		 friend_id:'',
                                		 user_id:'',
                                		 status:'',
                                		 isonline:'',
                                		 errorMessage:'',
                                		 errorCode:''
                               
                                		 
                                 };
                                 self.friends=[];
                                 
                                 
                                 
                                 
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
                        	 self.createFriend(self.friend);
                         };
                        	 self.createFriend=function(friend){
                        		 FiendService.createFriend(friend).then( 
                        					 console.log('friend is created'),
                        					 self.fetchAllFriends,
                        					 function(errResponse)
                        					 {
                        						 console.error('friend is not created');
                        					 }
                        					 )
                        					 
                        	 }; 
                        	self.updateFriendRequest = function(friend,id){
                        		FriendService.updateFriendRequest(friend,id)
                        		.then(
                        				self.fetchAllFriends,
                        				function(errResponse){
                        					console.error('Error while updating friend');
                        				}
                        				);
                        	} ;
                        	 
                        	 self.deleteFriend = function(id){
                        		 FriendService.deleteFriend(id)
                        		 .then(
                        				 self.fetchAllFriends,
                        				 function(errResponse){
                        					 console.error('Error while deleting friend');
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
                        	 
                        	self.fetchAllFriends=function(){
                        		FriendService.fetchAllFriend().then(function(d){
                        			self.friends=d;
                        		},
                        		function(errResponse){
                        			console.error('error while fetching Friends');
                        		}
                        		)
                        	};
                        	self.fetchAllFriends();
                        				 
}]); 
                        				 
                        		 
                        	
                         
                         
                                
                      
                                 
                                 
                                 
                                 
                                 
                                 
                                
                             	