(function () {

    'use strict';

    angular
        .module('youpeople')
        .factory('OrganizationNamesFactory', OrganizationNamesFactory);

    /** @ngInject */
    function OrganizationNamesFactory($resource) {
        return $resource('/api/organizations/names', {}, {
            'query': {
                method: 'GET',
                isArray: true
            }
        });
    }

}());