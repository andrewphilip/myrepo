(function(){
	
	'use strict';
	
	angular.module('personApp')
		   .controller('CreateController', CreateController);
	
	CreateController.$inject=['$rootScope', 'PersonService', '$location'];
	
	function CreateController($rootScope, PersonService, $location){
		
		var pc = this;
		
		pc.addPerson = addPerson;
		
		function addPerson(){
			console.log('inside addPerson()');
			$rootScope.showLoad=true;
			PersonService.createPerson(pc.newPerson)
				.then(function(res){
                    if (res.success) {
        				$rootScope.message='Created successfully.';
        				$rootScope.showLoad=false;
                        $location.path('/list');
                    } else {
                    	console.log('Error creating a person.'+ res.message);
        				$rootScope.message='Error occurred:'+res.message;
                    	$location.path('/list');
                    }
				});
			
		}
		
	}
})()