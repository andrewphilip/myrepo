(function(){
	
	'use strict';
	
	angular.module('personApp')
			.factory('PersonService', PersonService);
	
	PersonService.$inject=['$q', '$filter'];
	
	function PersonService($q, $filter){
		
		var ps = {};
		
		ps.getPersons = getPersons;
		ps.setPerson =  setPersons;
		ps.createPerson = createPerson;
		ps.getPersonByName = getPersonByName;
		ps.deletePerson   = deletePerson;
		ps.editPerson   = editPerson;
		ps.updatePerson = updatePerson;
		
		return ps;
		
		function getPersons(){
			
			if(!localStorage.persons){
				localStorage.persons= JSON.stringify([]);
			}
			//console.log('inside getPersons()');
			return JSON.parse(localStorage.persons);
		}
		
		
		function setPersons(persons){
			localStorage.persons = JSON.stringify(persons);
		}
		
		function getPersonByName(firstname){
            var deferred = $q.defer();
            var filtered = $filter('filter')(getPersons(), { firstName: firstname });
            var person = filtered.length ? filtered[0] : null;
            console.log('inside getPersonByName()'+firstname);
            deferred.resolve(person);
            return deferred.promise;
		}
		
		function createPerson(person){
			var deferred = $q.defer();
			
			getPersonByName(person.firstName).then(function(dupPerson){
				if(dupPerson != null){
					deferred.resolve({ success: false, message: 'Person.firstName "' + person.firstName + '" is already taken' });
				}
				else{
                    var persons = getPersons();
                    // save to local storage
                    console.log('Saving user...');
                    persons.push(person);
                    setPersons(persons);
                    deferred.resolve({ success: true });
				}
			});
			
			  return deferred.promise;
		}
		
		function deletePerson(name){
            var deferred = $q.defer();
            var persons = getPersons();
            for (var i = 0; i < persons.length; i++) {
                var person = persons[i];
                if (person.firstName === name) {
                	persons.splice(i, 1);
                    break;
                }
            }
            setPersons(persons);
            deferred.resolve();
            return deferred.promise;
		}
		
		function editPerson(name){
            var deferred = $q.defer();
            var persons = getPersons();
            for (var i = 0; i < persons.length; i++) {
                var person = persons[i];
                if (person.firstName === name) {
                	deferred.resolve(person);
                	break;
                }
            }
			return deferred.promise;
		}
		
		function updatePerson(person){
            var deferred = $q.defer();
            var persons = getPersons();
            for (var i = 0; i < persons.length; i++) {
                var p = persons[i];
                if (person.firstName === p.firstName) {
                	persons.splice(i, 1);
                	persons.push(person)
                	break;
                }
            }
            setPersons(persons);
            deferred.resolve();
			return deferred.promise;
		}
		
		
	}
})()