define(['./module'],function(directives){
	directives.directive('startScreen', function($timeout){
		  return{
		    restrict: 'E',
		    templateUrl: '/assets/javascripts/app/directives/start-screen.html',
		    link: function(scope, element, attr){
		    	
		    }
		  }
		});
})