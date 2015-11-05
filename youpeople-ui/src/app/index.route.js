(function() {
    'use strict';

    angular
        .module('youpeople')
        .config(routerConfig);

    /** @ngInject */
    function routerConfig($stateProvider, $urlRouterProvider) {
        $stateProvider
            .state('signIn', {
                url: '/sign-in',
                templateUrl: 'app/components/sign-in/sign-in.html',
                controller: 'SignInController',
                controllerAs: 'signIn'
            })
            .state('signUp', {
                url: '/sign-up',
                templateUrl: 'app/components/sign-up/sign-up.html',
                controller: 'SignUpController',
                controllerAs: 'signUp'
            })
            .state('organizationSelect', {
                url: '/',
                templateUrl: 'app/components/organization-select/organization-select.html',
                controller: 'OrganizationSelectController',
                controllerAs: 'organizationSelect',
                data: {
                    authorized: true
                }
            })
            .state('app', {
                abstract: true,
                templateUrl: 'app/components/app-layout/app-layout.html',
                controller: 'AppLayoutController',
                controllerAs: 'appLayout'   ,
                data: {
                    authorized: true,
                    selectedOrganization: true
                }
            })
            .state('app.organizations', {
                url: '/organizations',
                templateUrl: 'app/components/organizations/organizations.html',
                controller: 'OrganizationsController',
                controllerAs: 'organizations'
            })
            .state('app.contactList', {
                abstract: true,
                url: '/contacts',
                templateUrl: 'app/components/contact-list/contact-list.html',
                controller: 'ContactListController'
            })
            .state('app.contactList.internet', {
                url: '/internet',
                templateUrl: 'app/components/contact-list/internet/contact-list.internet.html',
                controller: 'ContactListInternetController',
                controllerAs: 'contactListInternet'
            })
            .state('app.contactList.file', {
                url: '/file',
                templateUrl: 'app/components/contact-list/file/contact-list.file.html',
                controller: 'ContactListFileController',
                controllerAs: 'contactListFile'
            })
            .state('app.dashboard', {
                url: '/dashboard',
                templateUrl: 'app/components/dashboard/dashboard.html',
                controller: 'DashboardController',
                controllerAs: 'dashboard'
            })
            .state('app.emailing', {
                url: '/emailing',
                templateUrl: 'app/components/emailing/emailing.html',
                controller: 'EmailingController',
                controllerAs: 'emailing'
            })
            .state('app.events', {
                url: '/events',
                templateUrl: 'app/components/events/events.html',
                controller: 'EventsController',
                controllerAs: 'events'
            })
            .state('app.posts', {
                url: '/posts',
                templateUrl: 'app/components/posts/posts.html',
                controller: 'PostsController',
                controllerAs: 'posts'
            })
            .state('app.person', {
                url: '/person/:id',
                templateUrl: 'app/components/person/person.html',
                controller: 'PersonController',
                controllerAs: 'person'
            })
            .state('app.personNew', {
                templateUrl: 'app/components/person-add-edit/person-add-edit.html',
                url: '/new/person',
                controller: 'PersonAddEditController',
                controllerAs: 'personAddEdit'
            })
            .state('app.person.edit', {
                templateUrl: 'app/components/person-add-edit/person-add-edit.html',
                url: '/person/:id/edit',
                controller: 'PersonAddEditController',
                controllerAs: 'personAddEdit'
            })
            .state('search', {
                url: '/search',
                templateUrl: 'app/components/search/search.html',
                controller: 'SearchController',
                controllerAs: 'search'
            })
        ;

        $urlRouterProvider.otherwise('/');
    }

})();
