(function () {
    'use strict';

    angular
        .module('youpeople')
        .controller('OrganizationSelectController', OrganizationSelectController);

    /** @ngInject */
    function OrganizationSelectController($mdToast, $location, OrganizationNamesFactory, AuthService, SettingsService) {
        var vm = this;

        vm.user = {};
        vm.selectedOrganization = {};
        vm.organizations = [];

        vm.openApp = openApp;

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

        function openApp() {
            if(vm.selectedOrganization) {
                SettingsService.setCurrentOrganization(vm.selectedOrganization);
                $location.path('/contacts/internet');
            } else {
                $mdToast.show(
                    $mdToast.simple()
                        .content('Can\'t use this organization')
                        .position('bottom left')
                        .hideDelay(3000)
                );
            }
        }
    }
})();
