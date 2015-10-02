define(['./module'],function(controllers){
	'use strict';
	controllers.controller('ImaginationCtrl', ['$scope','userService','$routeParams', function($scope, userService, $routeParams){
		var userId = $routeParams.id;
		userService.getUser(userId).then(function(response){
			$scope.user = response.data;
		});
	}]);
});