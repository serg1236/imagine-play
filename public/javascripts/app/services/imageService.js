define(['./module'],function(services){
	services.factory('imageService',['$q','Upload','$http',function($q, Upload, $http){
		return{
			addImage: addImage,
			deleteImage: deleteImage,
			getImage: getImage
		};
		
		function addImage(image, user){
			
				return Upload.upload({
	                url: '/upload-image',
	                file: image,
	                fields:{
	                	user:user
	                }
	              });

		}
		
		function deleteImage(image){
			return $http({
				url:'/delete-image',
				method:'POST',
				data:image
			});
		}
		
		function getImage(imageId){
			return $http({
				url:'/get-image',
				method:'POST',
				data:{
					id: imageId
				}
			});
		}
		
		function like(imageId, userId){
			return $http({
				url:'/toggle-like',
				method:'POST',
				data:{
					id: imageId,
					user: userId,
					action:'like'
				}
			});
		}
		
		function dislike(imageId, userId){
			return $http({
				url:'/toggle-like',
				method:'POST',
				data:{
					id: imageId,
					user: userId,
					action:'dislike'
				}
			});
		}
		
	}]);
});