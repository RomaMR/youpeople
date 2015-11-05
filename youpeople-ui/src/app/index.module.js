(function () {
    'use strict';

    /** @ngInject */
    angular
        .module('youpeople', [
            'youpeople.signIn',
            'youpeople.signUp',
            'youpeople.search',
            'youpeople.person',
            'youpeople.dashboard',
            'youpeople.contactList',
            'youpeople.organizations',
            'ngAnimate',
            'ngCookies',
            'ngSanitize',
            'ngMessages',
            'ngResource',
            'ngMaterial',
            'angularMoment',
            'md.data.table',
            'nvd3',
            'datamaps',
            'ui.router',
            'ui.validate',
            'ngFileUpload',
            'facebook'
        ]);

})();
