(function () {

    'use strict';

    angular
        .module('youpeople')
        .factory('PersonsOrganizationFileFactory', PersonsOrganizationFileFactory);

    /** @ngInject */
    function PersonsOrganizationFileFactory($resource) {
        return $resource('/api/persons/organization/:organizationId/upload', {}, {
            'query': {
                method: 'GET',
                isArray: true
            }
        });
    }

}());