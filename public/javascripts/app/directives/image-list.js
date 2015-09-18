define(['./module'],function(directives){
	directives.directive('imageList',['$http', function($http){

		  return{
		    restrict: 'E',
		    templateUrl: '/assets/javascripts/app/directives/image-list.html',
		    scope:{
		    	images: '=images'
		    },
		    link: function(scope, element, attr){
		    	
		    	scope.deleteImage = function(index){
					var image = scope.images[index];
					console.log("here");
					if(image){
						$http({
							url:'/delete-image',
							method:'POST',
							data:image
						}).then(function(response){
							scope.images.splice(index,1);
						})
					}
				}
		    }
		  };
		  
		  
		}]);
})