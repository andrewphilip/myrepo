(function(){
	
	'use strict';
	
	angular.module('app').factory('loginService', LoginService);
	
	LoginService.$inject=['$http','$q','$filter','$cookieStore','$rootScope'];
	
	function LoginService($http,$q, $filter,$cookieStore,$rootScope){
		var loginSrvc={};
		
		loginSrvc.login = login;
		loginSrvc.setUsers = setUsers;
		loginSrvc.getUsers = getUsers;
		loginSrvc.createUser = createUser;
		loginSrvc.getUserByName =getUserByName;
		loginSrvc.setCredentials = setCredentials;
		loginSrvc.clearCredentials = clearCredentials;
		
		//loginSrvc.users = [];
		
		function setUsers(users){
			localStorage.users = JSON.stringify(users);
		}
		
		function getUsers(){
			//console.log('inside getUsers()');
			if(!localStorage.users){
				localStorage.users= JSON.stringify([]);
			}
			return  JSON.parse(localStorage.users);
		}
		
		function getUserByName(uname){
            var deferred = $q.defer();
            var filtered = $filter('filter')(getUsers(), { username: uname });
            var user = filtered.length ? filtered[0] : null;
            
           // console.log('inside getUserByName()');
            deferred.resolve(user);
            return deferred.promise;
		}
		
		function login(username,pwd, callback){
			//console.log("LoginService:: "+username);
			getUsers();
			var res;
			getUserByName(username).then(function(ruser){
					if(ruser != null){
						//console.log('User: '+ruser.username);
						if(username == ruser.username && pwd == ruser.password){
							console.log('success login');
							res = {success:true, message:'Login success...'};
						}
						else{
							console.log('Login Error.');
							res = {success:false, message:'Login failure...'};
						}
						
					}
					else{
						console.log('Login Error.');
						res = {success:false, message:'Login failure...'};
					}
					callback(res);
			});	
			
		}
		
		function createUser(user){
			
			var deferred = $q.defer();
			console.log("username:"+user.username+" pwd:"+user.password);
			getUserByName(user.username).then(function(dupPerson){
				if(dupPerson != null){
					deferred.resolve({ success: false, message: 'Name ' + user.username + ' is already taken' });
				}
				else{
					//console.log('Going to create new user');
					var usersArr= getUsers();
					usersArr.push(user);
					setUsers(usersArr);
                    deferred.resolve({ success: true , message:'Saved.'});
                    console.log('User created..');
				}
			});
			  return deferred.promise;
		}
		
		function setCredentials(username, password){
            var authdata = _base64.encode(username + ':' + password);
            console.log("Enc: "+authdata);
            $rootScope.globals = {
                currentUser: {
                    username: username,
                    authdata: authdata
                }
            };
            $http.defaults.headers.common['Authorization'] = 'Basic ' + authdata; 
            $cookieStore.put('globals', $rootScope.globals);
		}

		function clearCredentials(){
            $rootScope.globals = {};
            $cookieStore.remove('globals');
            $http.defaults.headers.common.Authorization = 'Basic ';
		}
		
		return loginSrvc;
	}
	
	var _base64 = {
			_keyStr:"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",
			encode:function(e){var t="";var n,r,i,s,o,u,a;var f=0;e=_base64._utf8_encode(e);while(f<e.length){n=e.charCodeAt(f++);r=e.charCodeAt(f++);i=e.charCodeAt(f++);s=n>>2;o=(n&3)<<4|r>>4;u=(r&15)<<2|i>>6;a=i&63;if(isNaN(r)){u=a=64}else if(isNaN(i)){a=64}t=t+this._keyStr.charAt(s)+this._keyStr.charAt(o)+this._keyStr.charAt(u)+this._keyStr.charAt(a)}return t},
			decode:function(e){var t="";var n,r,i;var s,o,u,a;var f=0;e=e.replace(/[^A-Za-z0-9+/=]/g,"");while(f<e.length){s=this._keyStr.indexOf(e.charAt(f++));o=this._keyStr.indexOf(e.charAt(f++));u=this._keyStr.indexOf(e.charAt(f++));a=this._keyStr.indexOf(e.charAt(f++));n=s<<2|o>>4;r=(o&15)<<4|u>>2;i=(u&3)<<6|a;t=t+String.fromCharCode(n);if(u!=64){t=t+String.fromCharCode(r)}if(a!=64){t=t+String.fromCharCode(i)}}t=_base64._utf8_decode(t);return t},
			_utf8_encode:function(e){e=e.replace(/rn/g,"n");var t="";for(var n=0;n<e.length;n++){var r=e.charCodeAt(n);if(r<128){t+=String.fromCharCode(r)}else if(r>127&&r<2048){t+=String.fromCharCode(r>>6|192);t+=String.fromCharCode(r&63|128)}else{t+=String.fromCharCode(r>>12|224);t+=String.fromCharCode(r>>6&63|128);t+=String.fromCharCode(r&63|128)}}return t},
			_utf8_decode:function(e){var t="";var n=0;var r=c1=c2=0;while(n<e.length){r=e.charCodeAt(n);if(r<128){t+=String.fromCharCode(r);n++}else if(r>191&&r<224){c2=e.charCodeAt(n+1);t+=String.fromCharCode((r&31)<<6|c2&63);n+=2}else{c2=e.charCodeAt(n+1);c3=e.charCodeAt(n+2);t+=String.fromCharCode((r&15)<<12|(c2&63)<<6|c3&63);n+=3}}return t}
	};
	
})();