(function () {
    'use strict';

    angular
        .module('app')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$location' , 'loginService'];
    function LoginController($location,loginService) {
        var lc = this;

        lc.login = login;
        lc.register = register;
        

        function login() {
        	loginService.clearCredentials();
        	console.log(lc.user.username);
        	loginService.login(lc.user.username,lc.user.password, function(res){
        		if(res.success){
        			console.log("Login-Ctrl::"+res.message);
        			loginService.setCredentials(lc.user.username,lc.user.password);        			
        		}
        		else{
        			console.log("Login-Ctrl::"+res.message);
        		}
        	});
        	
        };
        
        function register(){
        	console.log('inside register():'+lc.user.username);
        	loginService.createUser(lc.user).then(
        	function(res){
        		console.log('loginCtrl::register()-callback');
        		if(res.success){
        			console.log("Login-Ctrl::"+res.message);
        			$location.path('/login');
        		}
        		else{
        			console.log("Login-Ctrl::"+res.message);
        		}
        		
        	});
        	
        }
    }

})();

