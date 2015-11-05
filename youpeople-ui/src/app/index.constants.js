/* global malarkey:false, toastr:false, moment:false */
(function() {
  'use strict';

  angular
    .module('youpeople')
    .constant('malarkey', malarkey)
    .constant('toastr', toastr)
    .constant('moment', moment)
    .constant('canonicalUrl', '@@canonicalUrl');

})();
