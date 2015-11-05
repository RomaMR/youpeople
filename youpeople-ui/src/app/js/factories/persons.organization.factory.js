(function () {

    'use strict';

    angular
        .module('youpeople')
        .factory('PersonsOrganizationFactory', PersonsOrganizationFactory);

    /** @ngInject */
    function PersonsOrganizationFactory($resource) {
        return $resource('/api/persons/organization/:organizationId', {}, {
            'query': {
                method: 'GET',
                isArray: true
            }
        });
    }

}());