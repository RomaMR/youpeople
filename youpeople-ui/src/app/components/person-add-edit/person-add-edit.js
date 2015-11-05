(function () {

    'use strict';

    angular
        .module('youpeople')
        .controller('PersonAddEditController', PersonAddEditController);

    /** @ngInject */
    function PersonAddEditController($stateParams, $mdToast, PersonsFactory, SettingsService) {
        var vm = this;
        //var organizationId = $stateParams.organizationId;
        var organization = SettingsService.getCurrentOrganization();
        vm.person = {};

        activate();

        vm.addJob = addJob;
        vm.addVolunteering = addVolunteering;
        vm.addEducation = addEducation;
        vm.addContact = addContact;

        function activate() {
            vm.person.jobs = [];
            vm.person.volunteerings = [];
            vm.person.educations = [];
            if ($stateParams.id) {
                PersonsFactory.get({
                    id: $stateParams.id
                }, function (data) {
                    vm.person = data;
                }, function (e) {
                    console.error(e);
                    $mdToast.show(
                        $mdToast.simple()
                            .content('Can\'t get person')
                            .position('bottom left')
                            .hideDelay(3000)
                    );
                });
            } else {
                addJob();
                addVolunteering();
                addEducation();
            }
        }

        function addContact() {
            console.log(vm.person);
            vm.person.organizationId = organization.id;
            PersonsFactory.create(vm.person,
                function () {
                    delete vm.person.organizationId;
                    $mdToast.show(
                        $mdToast.simple()
                            .content('Person has been saved')
                            .position('bottom left')
                            .hideDelay(3000)
                    );
                }, function (e) {
                    console.error(e);
                    $mdToast.show(
                        $mdToast.simple()
                            .content('Can\'t save person')
                            .position('bottom left')
                            .hideDelay(3000)
                    );
                });
        }

        function addJob() {
            vm.person.jobs.push({});
        }

        function addVolunteering() {
            vm.person.volunteerings.push({});
        }

        function addEducation() {
            vm.person.educations.push({});
        }

    }

}());