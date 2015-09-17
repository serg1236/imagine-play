require.config({
	waitSeconds: 20,
	
	paths:{
		
		angular: '../vendor/angular/angular',
		angularCookies: '../vendor/angular/angular-cookies',
		domReady: '../vendor/requirejs/domReady',
		fileUpload: '../vendor/ng-file-upload/ng-file-upload.min'
	}, 
	shim:{
		
		angular:{
			exports: 'angular'
		},
		
		angularCookies:{
			deps: ['angular']
		},
		fileUpload:{
			deps: ['angular']
		}

		
	},
	
	deps: ['./bootstrap']
});