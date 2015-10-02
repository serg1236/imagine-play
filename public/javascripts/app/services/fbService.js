define(['./module'],function(services){
	services.factory('fb',['$q','userService',function($q,userService){
		
		return {
			login: login,
			logout:logout,
			getStatus: init,
			getUserInfo:getUserInfo
		};
		
		function login(){
			var deferred = $q.defer();
			FB.login(function(response){
				deferred.promise.status = response.status;
				deferred.resolve("Ok");
			});
			return deferred.promise;
		}
		
		function logout(){
			var deferred = $q.defer();
			FB.logout(function(response){
				deferred.resolve("Ok");
			});
			return deferred.promise;
		}
		
		function init(){
			var deferred = $q.defer();
			window.fbAsyncInit = function() {
			    FB.init({
			      appId      : '1009612422439015',
			      xfbml      : true,
			      version    : 'v2.4'
			    });
			    var statusPromise = checkStatus();
			    statusPromise.then(function(resolve){
			    	deferred.promise.status = statusPromise.status;
			    	deferred.resolve("Ok");
			    });
			  };

			  (function(d, s, id){
			     var js, fjs = d.getElementsByTagName(s)[0];
			     if (d.getElementById(id)) {return;}
			     js = d.createElement(s); js.id = id;
			     js.src = "//connect.facebook.net/en_US/sdk.js";
			     fjs.parentNode.insertBefore(js, fjs);
			   }(document, 'script', 'facebook-jssdk'));
			  
			  
			  return deferred.promise;
		}
		
		function checkStatus(){
			var deferred = $q.defer();
			FB.getLoginStatus(function(response) {
				deferred.resolve("Ok");
				deferred.promise.status = response.status;
			});
			return deferred.promise;
		}
		//gets info from FB authenticate to the app
		function getUserInfo(fields){
			var deferred = $q.defer();
			FB.api('/me', {fields: fields}, function(response) {
				 userService.authUser(response).then(function(userResp){
					  deferred.promise.user = userResp.data;
					  deferred.resolve("Ok");
				  });			  
			});
			return deferred.promise;
		}
		
	}]);
});