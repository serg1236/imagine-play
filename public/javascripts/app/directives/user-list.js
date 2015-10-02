define(['./module'],function(directives){
	directives.directive('userList',['userService', function(userService){

		  return{
		    restrict: 'E',
		    templateUrl: '/assets/javascripts/app/directives/user-list.html',
		    scope:{
		    	excludedUser: '=exclude'
		    },
		    link: function(scope, element, attr){
		    	
		    	
		    	function initList(){
		    		userService.getUserList(scope.excludedUser).then(function(response){
		    			scope.users = response.data;
		    		});
		    	}
		    	
		    	scope.$on('UserInit', function(){
		    		initList();
		    	});
		    }
		  };
		  
		  
		}]);
})