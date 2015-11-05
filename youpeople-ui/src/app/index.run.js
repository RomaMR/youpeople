(function () {
    'use strict';

    angular
        .module('youpeople')
        .run(runBlock);

    /** @ngInject */
    function runBlock($rootScope, $state, $stateParams, $cookieStore, $location) {
        $rootScope.$state = $state;
        $rootScope.$stateParams = $stateParams;
        $rootScope.$on('$stateChangeStart', function(event, next) {
            if (next.data && next.data.authorized && ($cookieStore.get('globals') === undefined || !$cookieStore.get('globals').currentUser)) {
                event.preventDefault();
                $location.path('/sign-in');
            }
        });
    }

})();
