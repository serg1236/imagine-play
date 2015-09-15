define(['angular',
        './controllers/index',
        './directives/index',
        './services/index',
        './filters/index'], 
        function(ng){
	
			return ng.module('todo',[
	                        'todo.services',
	                        'todo.controllers',
	                        'todo.filters',
	                        'todo.directives']);
	
		});