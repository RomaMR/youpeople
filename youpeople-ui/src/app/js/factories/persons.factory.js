(function () {

    'use strict';

    angular
        .module('youpeople')
        .factory('PersonsFactory', PersonsFactory);

    /** @ngInject */
    function PersonsFactory($resource) {
        return $resource('/api/persons/:id', {}, {
            'query': {
                method: 'GET',
                isArray: true
            },
            'create': {
                method: 'POST'
            }
        });
    }

}());