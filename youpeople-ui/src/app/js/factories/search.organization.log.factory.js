(function () {

    'use strict';

    angular
        .module('youpeople')
        .factory('SearchOrganizationLogFactory', SearchOrganizationLogFactory);

    /** @ngInject */
    function SearchOrganizationLogFactory($resource) {
        return $resource('/api/search/organization/:organizationId/log', {}, {
            'query': {
                method: 'GET',
                isArray: true
            }
        });
    }

}());