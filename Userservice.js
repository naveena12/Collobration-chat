'use strict';
app.service('UserService',['$http','$q','$rootScope', function($http,$q,$rootScope){
	console.log("UserService...")
	var Backendurl="http://localhost:8080/collobrationchatt";
	return{
		fetchAllUsers:function(){
			return $http.get(Backendurl+'/users').then(
					function(response){
						return response.data;
					},
					function(errResponse){
						console.error('error while getting  userdetails');
						return $q.reject(errResponse);
					}
					);
		},
		createUser:function(user){
			return $http.post(Backendurl+'/user/',user).then(
					function(response){
						return response.data;
					},
					function(errResponse){
						console.error('error while creating userdetails');
						return $q.reject(errResponse);
					}
					);
		},
		
		authenticateUser:function(user){
			return $http.post(Backendurl+'/user/authenticate/',user).then(
					function(response){
					if(response.data.errormessage==""){
						$rootScope.currentUser={
								user_id:response.data.user_id,
                       		 name:response.data.name,
                       		 email:response.data.email,
                       		 address:response.data.address,
                       		 mobile_no:response.data.mobile_no,
                       		 password:response.data.password,
                       		 role:response.data.role,
                       		 isOnline:response.data.isOnline,
                       		 gender:response.data.gender,
                       		 status:response.data.status
                       		 
						};
					}
						return response.data;
					},
					function(errResponse){
						$rootScope.userLoggedin=false;
						console.error('error while creating userdetails');
						return $q.reject(errResponse);
					}
					);
		}
	}
	
}]);