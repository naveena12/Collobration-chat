'use strict'
app.factory('FriendService',['$http','$q','$rootScope', function($http,$q,$rootScope){
	console.log("FriendService...")
	var Backendurl="http://localhost:8080/collobrationchatt";
	return{
		fetchAllFriend:function(){
			return $http.get(Backendurl+'/myFriend').then(
					function(response){
						return response.data;
					},
					function(errResponse){
						console.error('error while getting  frienddetails');
						return $q.reject(errResponse);
					}
					);
		},
		createFriend:function(friend){
			return $http.post(Backendurl+'/addFriend/'+friend.user_id,friend).then(
					function(response){
						return response.data;
					},
					function(errResponse){
						console.error('error while creating frienddetails');
						return $q.reject(errResponse);
					}
					);
		},
		updateFriendRequest:function(friend,id)
		{
			return $http.put(Backendurl+'/friend/'+id,friend)
			.then(
					function(response){
						return response.data;
					},
					function(errResponse){
						console.error('Error while updating friend');
						return $q.reject(errResponse);
					});
					
		},
		deleteFriend:function(id){
			return $http.delete(Backendurl+'/friend/'+id)
			.then(
					function(response){
						return response.data;
					},
					function(errResponse){
						console.error('Error while deleting friend');
						return $q.reject(errResponse);
					});
					
		}
	}
	
}]);