define(['./module'],function(directives){
	directives.directive('imageList',['imageService', function(imageService){

		  return{
		    restrict: 'E',
		    templateUrl: '/assets/javascripts/app/directives/image-list.html',
		    scope:{
		    	images: '=images',
		    	editable: '='
		    },
		    link: function(scope, element, attr){
		    	
		    	scope.deleteImage = function(index){
					var image = scope.images[index];
					console.log("here");
					if(image){
						imageService.deleteImage(image).then(function(response){
							scope.images.splice(index,1);
						})
					}
				}
		    }
		  };
		  
		  
		}]);
})