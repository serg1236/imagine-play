define(['./module'],function(controllers){
	'use strict';
	controllers.controller('MainCtrl', ['$scope','$cookies','$http','fb','Upload', function($scope,$cookies,$http,fb,Upload){
		$scope.loginedToFb = false;
		$scope.currentUser = {};
		var statusPromise = fb.getStatus();
		statusPromise.then(function(resolve){
			console.log(statusPromise.status);
			if(statusPromise.status==="connected"){
				initUser();
				$scope.loginedToFb = true;
			}
		});
		
		$scope.loginToFb = function(){
			var loginPromise = fb.login();
			loginPromise.then(function(resolve){
				if(loginPromise.status==="connected"){
					$scope.loginedToFb = true;
					initUser();
					
				}
			});
		};
		
		$scope.logoutFromFb = function(){
			fb.logout().then(function(resolve){
				$scope.loginedToFb=false;
			});
		};
		
		$scope.uploadImage = function(image){
			console.log(image);
		};
		
		function initUser(){
			FB.api('/me', {fields: ['id','first_name','last_name']}, function(response) {
				  $http({
					  url: '/auth',
					  method: 'POST',
					  data: response
				  }).then(function(response){
					  $scope.currentUser = response.data;
				  });			  
				});
		}
		
	}]);
})