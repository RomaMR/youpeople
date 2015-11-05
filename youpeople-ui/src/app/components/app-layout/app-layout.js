(function () {
    'use strict';

    angular
        .module('youpeople')
        .controller('AppLayoutController', AppLayoutController);

    /** @ngInject */
    function AppLayoutController(SettingsService, OrganizationNamesFactory, PersonsSearchFactory) {
        var vm = this;

        vm.selectedOrganization = {};
        vm.organizations = [];

        vm.selectOrganization = selectOrganization;

        activate();

        function activate() {
            //var query = {
            //    organizationName: 'GDG Lviv',
            //    order: 'lastName',
            //    workStatus: 'All',
            //    limit: 10,
            //    page: 1
            //};
            //PersonsSearchFactory.query(query,
            //    function (data, headers) {
            //    }, function (e) {
            //        console.error(e);
            //        vm.loading = false;
            //    });

            if (!SettingsService.settings) {
                SettingsService.settings = {
                    defaultOrganization: {}
                };
                OrganizationNamesFactory.query({},
                    function (data) {
                        vm.organizations = data;
                        vm.selectedOrganization = vm.organizations[0];
                        selectOrganization();
                    }, function (e) {
                        console.error(e);
                    });
            }
        }

        function selectOrganization() {
            SettingsService.settings.defaultOrganization = vm.selectedOrganization;
        }
    }

})();
