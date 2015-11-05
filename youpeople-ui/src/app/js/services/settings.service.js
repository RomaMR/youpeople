(function () {

    'use strict';

    angular
        .module('youpeople')
        .factory('SettingsService', SettingsService);

    /** @ngInject */
    function SettingsService() {
        var currentOrganization = {};
        var settings = {};

        return {
            getSettings: getSettings,
            setSettings: setSettings,
            getCurrentOrganization: getCurrentOrganization,
            setCurrentOrganization: setCurrentOrganization
        };

        function getSettings() {
            return angular.copy(settings);
        }
        function setSettings(newSettings) {
            settings = angular.copy(newSettings);
        }
        function getCurrentOrganization() {
            return angular.copy(currentOrganization);
        }
        function setCurrentOrganization(newCurrentOrganization) {
            currentOrganization = angular.copy(newCurrentOrganization);
        }
    }

})();
