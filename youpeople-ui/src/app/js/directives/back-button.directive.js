(function() {
    'use strict';

    angular
        .module('youpeople')
        .directive('backButton', backButton);

    function backButton() {
        return {
            restrict: 'A',

            link: function(scope, element, attrs) {
                element.bind('click', goBack);

                function goBack() {
                    history.back();
                    scope.$apply();
                }
            }
        };
    }

})();
