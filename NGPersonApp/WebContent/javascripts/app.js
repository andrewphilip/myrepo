(function(){
	'use strict';
	
	angular.module('personApp', ['ngRoute','angularSpinner','angularUtils.directives.dirPagination'])
		.config(config);
	
	
	config.$inject=['$routeProvider', '$locationProvider'];
	
	function config($routeProvider, $locationProvider){
		
		$routeProvider
			.when('/list',
					{
						controller:'ListController',
						templateUrl:'views/list.html',
						controllerAs: 'pc'	
					})
			.when('/addPerson',
					{
						controller:'CreateController',
						templateUrl:'views/addperson.html',
						controllerAs: 'pc'	
					})
			.when('/editPerson/:firstName',
					{
						controller:'EditController',
						templateUrl:'views/editperson.html',
						controllerAs: 'pc'	
					})		
			.otherwise({redirectTo:'/list'});
		
	}
})()