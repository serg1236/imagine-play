define(['angular',
        './controllers/index',
        './directives/index',
        './services/index',
        './filters/index'], 
        function(ng){
	
			var app = ng.module('imagine',[
	                        'imagine.services',
	                        'imagine.controllers',
	                        'imagine.filters',
	                        'imagine.directives']);
			
			
			
			return app;
	
		});