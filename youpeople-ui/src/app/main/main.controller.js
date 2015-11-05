(function () {
    'use strict';
    /*global d3:false */

    angular
        .module('youpeople')
        .controller('MainController', MainController);

    /** @ngInject */
    function MainController($cookies, $location, AuthService) {
        var vm = this;

        vm.signOut = signOut;

        if ($cookies.getObject('globals') === undefined || !$cookies.getObject('globals').currentUser) {
            $location.path("/sign-in");
        }

        function signOut() {
            AuthService.signOut();
            $location.path( "/sign-in" );
        }
    }
})();
