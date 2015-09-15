define(['./module'],function(directives){
	directives.directive('photoItem', function($timeout){
		  return{
		    restrict: 'A',
		    templateUrl: '/assets/javascripts/app/directives/photoItem.html',
		    link: function(scope, element, attr){
		    	
		    }
		  }
		});
})