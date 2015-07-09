(function(){
	'use strict';
	
	angular.module('personApp')
	   .controller('ListController', ListController);

	ListController.$inject=['$rootScope', 'PersonService', '$location'];
	
	function ListController($rootScope, PersonService, $location){
		var pc = this;
		
		pc.persons =[];
		init();
		pc.deletePerson = delPerson;
		pc.editPerson =editPerson;
		$rootScope.showLoad=true;
		$rootScope.currentPage = 1;
		$rootScope.pageSize = 10;
		
		pc.pageChangeHandler = function(num) {
		      console.log('persons page changed to ' + num);
		};
		
		function init(){
			pc.persons = PersonService.getPersons();
			$rootScope.showLoad=false;
			$location.path('/list');
			//console.log($location.path() == '/list' ? 'Y' : 'N');
		}
		
		
		function delPerson(name){
			$rootScope.showLoad=true;
			PersonService.deletePerson(name).then(function(){
				pc.persons = PersonService.getPersons();
				console.log('delete success');
				$rootScope.showLoad=false;
				$location.path('/list');
			});
		}
		
		function editPerson(name){
			console.log('ListCtrl::editPerson'+name);
			//$location.search('firstname',name).path('/editPerson');
			$location.path('/editPerson');
		}
		
		
	}
	
})()