define(['./module'],function(services){


	services.factory('taskListService',['$http','$q', function($http,$q){

		

		function getConfigs(){
			var deferred = $q.defer();
			$http.get('/assets/javascripts/backendConfigs.json').then(function(response){
				if(response.status=='200'){
					deferred.resolve('Ok');
					deferred.promise.configs = response.data;
				}else{
					deferred.reject('Error');
				}
			});
			return deferred.promise;
		}

		var configPromise = getConfigs();

		function save(userLogin, list){
			var data ={
				login:userLogin, 
				tasks:list
			};
			return configPromise.then(
				function(resolve){
					var host = configPromise.configs.host;
					var saveUrl = configPromise.configs.saveUrl;
					var url = host+saveUrl;
					return $http({
						url:url,
						method:'POST',
						data: data}).then(function(response){
					
						if(response.status=='200'){
							return response.data;
						}else{
							return 'Error';
						}
					});
				},

				function(reject){
					console.log(reject);
				}
			);

		}

		return {
			save: save
		};

	}]);
});