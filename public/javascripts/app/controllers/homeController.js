define(['./module'],function(controllers){
	'use strict';
	controllers.controller('HomeCtrl', ['$scope','$timeout','$http','fb','imageService', function($scope, $timeout, $http, fb, imageService){
		
		$scope.user = $scope.$parent.currentUser;
		$scope.$on('UserInit', function(){
			$scope.user = $scope.$parent.currentUser;
			console.log($scope.user);
    	});
		
		$scope.uploadProgress = false;
		
		$scope.uploadImage = function(image){
			if (image && !image.$error){
				imageService.addImage(image,$scope.user)
	              .progress(function(){
	                	$scope.uploadProgress = true;
	                })
	              .success(function(data){
	            	  console.log(data);
	            	  $scope.user = data;
	            	  $scope.uploadProgress = false;
	              })
	              .error(function(){
	            	  $scope.uploadProgress = false;
	            	  Materialize.toast("Sorry, something wrong:(", 2000);
	              });
			}
		};
	}]);
});