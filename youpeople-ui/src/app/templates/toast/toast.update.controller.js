(function () {

    'use strict';

    angular
        .module('youpeople')
        .controller('ToastUpdateController', ToastUpdateController);

    /** @ngInject */
    function ToastUpdateController(days, query) {
        var vm = this;

        vm.days = days;
        vm.query = query;

        vm.refreshDataBase = refreshDataBase;

        function refreshDataBase() {

        }
    }
}());