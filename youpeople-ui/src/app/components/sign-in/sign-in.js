(function () {

    'use strict';

    angular
        .module('youpeople.signIn', [])
        .controller('SignInController', SignInController);

    /** @ngInject */
    function SignInController($location, $mdToast, AuthService) {
        var vm = this;

        vm.credentials = {};

        vm.signIn = signIn;

        function signIn() {
            AuthService.signIn(vm.credentials.username, vm.credentials.password)
                .then(function () {
                    $location.path("/");
                }, function (e) {
                    console.error(e);
                    AuthService.signOut();
                    $mdToast.show(
                        $mdToast.simple()
                            .content('Incorrect login name or password')
                            .position('bottom left')
                            .hideDelay(3000)
                    );
                });
        }
    }

}());