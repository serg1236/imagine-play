define(['./module'],function(services){
	services.factory('userService',['$http',function($http){
		return{
			authUser: authUser,
			getUserList: getUserList,
			getUser: getUser
		};
		
		function authUser(user){
			return $http({
				  url: '/auth',
				  method: 'POST',
				  data: user
			  });
		}
		
		function getUser(userId){
			return $http({
				  url: '/get-user',
				  method: 'POST',
				  data: {
					  id: userId
				  }
			  });
		}
		
		function getUserList(user){
			return $http({
				  url: '/user-list',
				  method: 'POST',
				  data: user
			  });
		}
	}]);
});