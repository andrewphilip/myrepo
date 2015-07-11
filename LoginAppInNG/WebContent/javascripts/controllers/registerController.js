(function () {
    'use strict';

    angular
        .module('app')
        .controller('RegisterController', RegisterController);

    RegisterController.$inject = ['UserService', '$location', '$rootScope', 'FlashService'];
    function RegisterController(UserService, $location, $rootScope, FlashService) {
        var lc = this;

        lc.register = register;

        function register() {
            lc.dataLoading = true;
            console.log('inside register()');
            UserService.Create(lc.user)
                .then(function (response) {
                    if (response.success) {
                        FlashService.Success('Registration successful', true);
                        $location.path('/login');
                    } else {
                        FlashService.Error(response.message);
                        lc.dataLoading = false;
                    }
                });
        }
    }

})();

