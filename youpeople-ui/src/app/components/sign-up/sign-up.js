(function () {

    'use strict';

    angular
        .module('youpeople.signUp', [])
        .controller('SignUpController', SignUpController);

    /** @ngInject */
    function SignUpController($http, $mdToast) {
        var vm = this;

        vm.user = {};

        vm.signUp = signUp;
        vm.uploadFile = uploadFile;

        function signUp() {
            $http.post('/api/users/registration', vm.user, {}).then(registrationSuccessful, registrationFailed);
        }

        function registrationSuccessful(response, AuthService) {
            if(response.code == 201) {
                AuthService.signIn(vm.user.email, vm.user.password);
                $location.path('/organization/select');
            }
        }

        function registrationFailed(error) {
            console.log(error);
            $mdToast.show(
                $mdToast.simple()
                    .content('Something went wrong. Please try again')
                    .position('bottom left')
                    .hideDelay(3000)
            );
        }

        function uploadFile(file) {
            vm.user.file = file;
        }
    }

}());