(function () {
    'use strict';

    angular
        .module('app')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$location', 'AuthenticationService', 'FlashService'];
    function LoginController($location, AuthenticationService, FlashService) {
        var lc = this;

        lc.login = login;

        (function initController() {
            // reset login status
            AuthenticationService.ClearCredentials();
        })();

        function login() {
            lc.dataLoading = true;
            AuthenticationService.Login(lc.username, lc.password, function (response) {
                if (response.success) {
                    AuthenticationService.SetCredentials(lc.username, lc.password);
                    $location.path('/');
                } else {
                    FlashService.Error(response.message);
                    lc.dataLoading = false;
                }
            });
        };
    }

})();

