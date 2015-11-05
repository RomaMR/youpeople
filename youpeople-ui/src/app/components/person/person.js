(function () {

    'use strict';

    angular
        .module('youpeople.person', [])
        .controller('PersonController', PersonController);

    /** @ngInject */
    function PersonController($stateParams, $mdToast, PersonsFactory) {
        var vm = this;

        vm.person = {};

        activate();

        function activate() {
            PersonsFactory.get({
                id:  $stateParams.id
            }, function(data) {
                vm.person = data;
            }, function(e) {
                console.error(e);
                $mdToast.show(
                    $mdToast.simple()
                        .content('Can\'t get person')
                        .position('bottom left')
                        .hideDelay(3000)
                );
            });
        }

    }

}());