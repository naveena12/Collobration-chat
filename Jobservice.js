'use strict'
app.factory('JobService',['$http','$q','$rootScope', function($http,$q,$rootScope){
	console.log("JobService...")
	var Backendurl="http://localhost:8080/collobrationchatt";
	return{
		fetchAllJobs:function(){
			return $http.get(Backendurl+'/getAllJobs/').then(
					function(response){
						return response.data;
					},
					function(errResponse){
						console.error('error while getting  jobdetails');
						return $q.reject(errResponse);
					}
					);
		},
		fetchAllJobs_applicaton:function(){
			return $http.get(Backendurl+'/getAllJobsApplication/').then(
					function(response){
						return response.data;
					},
					function(errResponse){
						console.error('error while getting  job_applicationdetails');
						return $q.reject(errResponse);
					}
					);
		},

		createJob_application:function(job){
			return $http.post(Backendurl+'/applyForJob/'+job.job_id,job).then(
					function(response){
						return response.data;
					},
					function(errResponse){
						console.error('error while creating job_applicationdetails');
						return $q.reject(errResponse);
					}
					);
		},
	
		createJob:function(job_application){
			return $http.post(Backendurl+'/postAJob/',job).then(
					function(response){
						return response.data;
					},
					function(errResponse){
						console.error('error while creating jobdetails');
						return $q.reject(errResponse);
					}
					);
		}
	
	}
}]);