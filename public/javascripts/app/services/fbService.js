define(['./module'],function(services){
	services.factory('fb',[function(){
		return {
			login: login
		};
		
		function login(){
			return 1;
		}
	}]);
});