(function () {
    'use strict';

    angular
        .module('youpeople')
        .config(config)
        .config(facebookConfig)
        .config(httpHeaderConfig);

    /** @ngInject */
    function config($logProvider, toastr) {
        // Enable log
        $logProvider.debugEnabled(true);

        // Set options third-party lib
        toastr.options.timeOut = 3000;
        toastr.options.positionClass = 'toast-bottom-left';
        toastr.options.preventDuplicates = true;
        toastr.options.progressBar = true;
    }

    /** @ngInject */
    function facebookConfig(FacebookProvider) {
        FacebookProvider.init('933725940014396');
    }

    /** @ngInject */
    function httpHeaderConfig($httpProvider) {
        $httpProvider.defaults.useXDomain = true;
        $httpProvider.defaults.withCredentials = true;
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
        $httpProvider.interceptors.push(['$q', '$location', '$window', 'canonicalUrl', function ($q, $location, $window, canonicalUrl) {
            return {
                'request': function (config) {
                    if (config.url.startsWith('/api')) {
                        config.url = canonicalUrl + config.url;
                    }
                    return config || $q.when(config);
                },
                'responseError': function (response) {
                    if ($window.location.hash != "#/sign-in") {
                        if (response.status === 401) {
                            $location.path("/sign-in");
                        } else if (response.status === 403) {
                            $location.path("/");
                        }
                    }
                    return $q.reject(response);
                }
            };
        }]);
    }

    ///** @ngInject */
    //function componentLoaderConfig($componentLoaderProvider) {
    //    $componentLoaderProvider.setTemplateMapping(function (name) {
    //        var dashName = dashCase(name);
    //        return 'app/components/' + dashName + '/' + dashName + '.html';
    //    });
    //}
    //
    //function dashCase(str) {
    //    return str.replace(/([A-Z])/g, function ($1) {
    //        return '-' + $1.toLowerCase();
    //    });
    //}

})();
