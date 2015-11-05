(function () {

    'use strict';

    angular
        .module('youpeople')
        .factory('PersonsSearchFactory', PersonsSearchFactory);

    /** @ngInject */
    function PersonsSearchFactory($resource) {
        return $resource('/api/persons/search', {}, {
            'query': {
                method: 'GET',
                isArray: true
            }
        });
    }

}());