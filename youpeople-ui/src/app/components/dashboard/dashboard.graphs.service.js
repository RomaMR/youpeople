(function () {

    'use strict';

    angular
        .module('youpeople.dashboard')
        .factory('DashboardGraphsService', DashboardGraphsService);

    /** @ngInject */
    function DashboardGraphsService($http, $mdToast) {

        return {
            getYearsAfterData: getYearsAfterData,
            getAlumniMapData: getAlumniMapData,
            getEmployeesNumberData: getEmployeesNumberData,
            getEmployeesTurnoverData: getEmployeesTurnoverData,
            getIndustriesData: getIndustriesData,
            getFacebookMetricsData: getFacebookMetricsData
        };

        function getIndustriesData(organizationId, industryGroupId) {
            var id = industryGroupId ? '/' + industryGroupId : '';
            return $http({
                url: '/api/statistic/organization/' + organizationId + '/industry/groups' + id,
                method: 'GET'
            })
                .then(success)
                .catch(failed);

            function success(response) {
                return response.data;
            }

            function failed(e) {
                logError(e, 'Can\'t get data for industries');
            }
        }

        function getYearsAfterData(organizationId) {
            return $http({
                url: '/api/statistic/organization/' + organizationId + '/time/after',
                method: 'GET'
            })
                .then(success)
                .catch(failed);

            function success(response) {
                return response.data;
            }

            function failed(e) {
                logError(e, 'Can\'t get data for years after chart');
            }
        }

        function getAlumniMapData(organizationName) {
            return $http({
                url: '/json-api/dashboard.alumni.map.json',
                method: 'GET',
                params: {organizationName: organizationName}
            })
                .then(success)
                .catch(failed);

            function success(response) {
                return response.data;
            }

            function failed(e) {
                logError(e, 'Can\'t get data for alumni map');
            }
        }

        function getEmployeesNumberData(organizationName) {
            return $http({
                url: '/json-api/dashboard.employees.number.json',
                method: 'GET',
                params: {organizationName: organizationName}
            })
                .then(success)
                .catch(failed);

            function success(response) {
                return response.data;
            }

            function failed(e) {
                logError(e, 'Can\'t get data for employees number chart');
            }
        }

        function getEmployeesTurnoverData(organizationId) {
            return $http({
                url: '/api/statistic/organization/' + organizationId + '/time/turnover',
                method: 'GET'
            })
                .then(success)
                .catch(failed);

            function success(response) {
                return response.data;
            }

            function failed(e) {
                logError(e, 'Can\'t get data for employees turnover chart');
            }
        }

        function getFacebookMetricsData(pageName, metricName, facebookAuthenticationToken, since, until, period) {
            return $http({
                url: '/api/facebook/pagemetrics/' + pageName + '/' + metricName,
                method: 'POST',
                params: {
                    facebookAuthenticationToken: facebookAuthenticationToken,
                    since: since,
                    until: until
                }
            })
                .then(success)
                .catch(failed);

            function success(response) {
                return response.data;
            }

            function failed(e) {
                logError(e, 'Can\'t get data for Facebook metrics data');
            }
        }

        function logError(error, message) {
            console.error(error);
            $mdToast.show(
                $mdToast.simple()
                    .content(message)
                    .position('bottom left')
                    .hideDelay(3000)
            );
        }
    }
})();