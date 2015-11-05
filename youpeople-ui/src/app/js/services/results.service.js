(function () {

    'use strict';

    angular
        .module('youpeople')
        .factory('ResultsService', ResultsService);

    /** @ngInject */
    function ResultsService() {
        var results = {};

        return results;
    }

})();
