define(['./module'],function(controllers){
	'use strict';
	controllers.controller('MainCtrl', ['$scope','$cookies','$http','fb','Upload', function($scope,$cookies,$http,fb,Upload){
		$scope.loginedToFb = false;
		$scope.currentUser = {};
		$scope.uploadProgress = false;
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
		
		
		//TODO: move to service
		$scope.uploadImage = function(image){
			if (image && !image.$error){
				image.upload = Upload.upload({
	                url: '/upload-image',
	                file: image,
	                fields:{
	                	user:$scope.currentUser
	                }
	              })
	              .progress(function(){
	                	$scope.uploadProgress = true;
	                })
	              .success(function(data){
	            	  console.log(data)
	            	  $scope.currentUser = data;
	            	  $scope.uploadProgress = false;
	              })
	              .error(function(){
	            	  $scope.uploadProgress = false;
	            	  Materialize.toast("Sorry, something wrong:(", 2000);
	              });
			}
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