define(['./module'],function(controllers){
	'use strict';
	controllers.controller('ImageCtrl', ['$scope','imageService','$routeParams', function($scope, imageService, $routeParams){
		var imageId = $routeParams.id;
		$scope.user = $scope.$parent.currentUser;
		$scope.$on('UserInit', function(){
			$scope.user = $scope.$parent.currentUser;
    	});
		
		imageService.getImage(imageId).then(function(response){
			$scope.image = response.data;
			$('.materialboxed').materialbox();
			console.log($scope.image);
		});
		
		$scope.isLiked = isLiked();
		
		function isLiked(){
			for(int i=0; i<$scope.image.liked.length; i++){
				if($scope.image.liked.id==$scope.user.id){
					return true;
				}
			}
			return false;
		}
		
		$scope.toggleLike = function(){
			if($scope.isLiked){
				dislike();
			}else{
				like();
			}
		}
		
		function like(){
			imageService.like($scope.image.public_id, $scope.user).then(function(response){
				$scope.image.liked.push($scope.user);
				$scope.isLiked = true;
			});
		}
		
		function like(){
			imageService.dislike($scope.image.public_id, $scope.user).then(function(response){
				$scope.isLiked = false;
				for(int i=0; i<$scope.image.liked.length; i++){
					if($scope.image.liked.id==$scope.user.id){
						$scope.image.liked.splice(i,1);
						break;
					}
				}
			});
		}
		
	}]);
});