define(['./module'],function(controllers){
	'use strict';
	controllers.controller('MainCtrl', ['$scope','$timeout','$http','fb','imageService', function($scope, $timeout, $http, fb, imageService){
		$scope.loginedToFb = false;
		$scope.currentUser = {};
		
		var statusPromise = fb.getStatus();
		statusPromise.then(function(resolve){
			console.log(statusPromise.status);
			if(statusPromise.status==="connected"){
				initUser();
				$scope.loginedToFb = true;
			}
			//angular loading hack
			$("#page").removeClass("no-display");
			$("#preloader").addClass("no-display");
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
		
		
		
		
		
		
		function initUser(){
			var userPromise = fb.getUserInfo(['id','first_name','last_name']);
			userPromise.then(function(){
					  $scope.currentUser = userPromise.user;
					  $timeout(function(){
						  $scope.$broadcast('UserInit');
					  });
					  
				  });			  
				
		}
		
		
	}]);
})