(function(){
	'use strict';
	
	angular.module('app', ['ngRoute','ngCookies'])
			.config(config)
			.run(run);
	
	
	config.$inject=['$routeProvider','$locationProvider'];
	
	function config($routeProvider, $locationProvider){
		$routeProvider
			.when('/',{
				controller:'HomeController',
				templateUrl: 'views/home.html',
				controllerAs: 'lc'
			})
			.when('/login',{
				controller:'LoginController',
				templateUrl: 'views/login.html',
				controllerAs: 'lc'
			})
			.when('/register',{
				controller:'RegisterController',
				templateUrl: 'views/register.html',
				controllerAs: 'lc'
			})
			.otherwise({redirectTo:'/login'});
	}
	
	
	run.$inject=['$rootScope','$location','$cookieStore','$http'];
	
	function run($rootScope,$location,$cookieStore,$http){
		
		$rootScope.globals= $cookieStore.get('globals') || {};
		
		if($rootScope.globals.currentUser){
			$http.defaults.headers.common['Authorization'] = 'Basic '+$rootScope.globals.currentUser.authdata;
		}
		
		$rootScope.$on('$locationChangeStart', function(event, next,current){
			var restrictedPage=$.inArray($location.path(), ['/login','/register']) === -1;
			
			var loggedIn=$rootScope.globals.currentUser;
			
			if(restrictedPage && !loggedIn){
				$location.path('/login');
			}
		})
	}
	
})();