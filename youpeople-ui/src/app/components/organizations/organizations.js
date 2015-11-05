(function () {
    'use strict';

    angular
        .module('youpeople.organizations', [])
        .controller('OrganizationsController', OrganizationsController);

    /** @ngInject */
    function OrganizationsController($mdToast, $cookies, OrganizationNamesFactory, AuthService, Facebook) {
        var vm = this;

        vm.user = {};
        vm.selectedOrganization = {};
        vm.progressPercentage = 0;
        vm.organizations = [];

        vm.facebookLogin = facebookLogin;

        activate();

        function activate() {
            vm.user = AuthService.getUser();
            OrganizationNamesFactory.query({},
                function (data) {
                    vm.organizations = data;
                }, function (e) {
                    console.error(e);
                });
        }

        function facebookLogin() {
            Facebook.login(function (response) {
                if (response.status === 'connected') {
                    console.log(response);
                    $cookies.put('facebookAuthenticationToken', response.authResponse.accessToken);
                    $mdToast.show(
                        $mdToast.simple()
                            .content('Logged in Facebook')
                            .position('bottom left')
                            .hideDelay(3000)
                    );
                }
            }, {scope: 'email, public_profile, user_friends', return_scopes: true});
        }
    }

    /** @ngInject */
    OrganizationsController.prototype.canActivate = function ($cookies) {
        return $cookies.getObject('globals') !== undefined && $cookies.getObject('globals').currentUser;
    };
})();
