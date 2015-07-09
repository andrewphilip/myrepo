(function(){
	'use strict';
	
	angular.module('personApp')
	   .controller('EditController', EditController);

	EditController.$inject=['$rootScope', 'PersonService', '$location', '$routeParams'];
	
	function EditController($rootScope, PersonService, $location,$routeParams){
		
		var pc = this;
		pc.person ={};
		pc.updatePerson = updatePerson;
		init();
		
		function init(){
			var name=$routeParams.firstName;
			$rootScope.showLoad=true;
			PersonService.editPerson(name).then(function(data){
				pc.person=data;
				$rootScope.showLoad=false;
				console.log(data.firstName);
			});
		}
		
		function updatePerson(){
			$rootScope.showLoad=true;
			PersonService.updatePerson(pc.person).then(function(){
				console.log('modified successfully.');
				$rootScope.message='Updated successfully.';
				$rootScope.showLoad=false;
				$location.path('/list');
			});
		}
	}
	
})()