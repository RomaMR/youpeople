(function () {

    'use strict';

    angular
        .module('youpeople')
        .factory('OrganizationPersonsPropertiesFactory', OrganizationPersonsPropertiesFactory);

    /** @ngInject */
    function OrganizationPersonsPropertiesFactory($resource) {
        return $resource('/api/organizations/:organizationId/persons/properties', {}, {
            'query': {
                method: 'GET',
                isArray: true
            }
        });
    }

}());