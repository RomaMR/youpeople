(function () {

    'use strict';

    angular
        .module('youpeople.search', [])
        .controller('SearchController', SearchController);

    /** @ngInject */
    function SearchController($mdToast, PersonsSearchFactory, OrganizationPersonsPropertiesFactory) {
        var vm = this;
        var previousQuery = '';

        vm.searchLog = {};
        vm.organizationId = {};
        vm.filters = {};
        vm.persons = [];
        vm.selected = [];
        vm.loading = false;
        vm.loaded = false;
        vm.count = 0;
        vm.query = {
            organizationName: '',
            order: 'lastName',
            workStatus: 'All',
            limit: 10,
            page: 1
        };

        vm.industriesData = [];
        vm.industriesAggregationData = [];
        vm.yearsAfterData = [];
        vm.employeesNumberData = [];
        vm.employeesTurnoverData = [];
        vm.selectedIndustry = 'Selected industry';

        vm.search = search;
        vm.newSearch = newSearch;
        vm.querySearch = querySearch;
        vm.filter = filter;

        activate();

        function activate() {

        }

        function search() {
            if (vm.query.organizationName !== '') {
                vm.loading = true;
                vm.loaded = false;
                vm.persons = [];

                PersonsSearchFactory.query(vm.query,
                    function (data, headers) {
                        processPersons(data, headers, true);
                    }, function (e) {
                        console.error(e);
                        vm.loading = false;
                        if (e.status == 404) {
                            showToast(e.data.message, 'Fetch from internet');
                        }
                    });
            }
        }

        function newSearch() {
            if (vm.query.organizationName !== '') {
                vm.loading = true;
                vm.loaded = false;
                vm.persons = [];

                PersonsSearchFactory.query(vm.query,
                    function (data, headers) {
                        processPersons(data, headers);
                    }, function (e) {
                        console.error(e);
                        vm.loading = false;
                    });
            }
        }

        function querySearch(query) {
            return query ? vm.organizationNames.filter(function (el) {
                return angular.lowercase(el).indexOf(angular.lowercase(query)) === 0;
            }) : [];
        }

        function filter() {
            vm.query.page = 1;
            search();
        }

        function processPersons(persons, headers, showToast) {
            vm.persons = persons;
            for (var i = 0; i < vm.persons.length; i++) {
                var job = findJobByOrganization(vm.persons[i].jobs, vm.query.organizationName);
                if (job) {
                    vm.persons[i].leftCompanyDate = job.endDate;
                }
            }
            vm.count = headers()['x-total-count'];
            vm.loading = false;
            vm.loaded = true;
            getFiltersData(vm.query.organizationName);
            if (vm.query.page == 1 && showToast && previousQuery != vm.query.organizationName) {
                previousQuery = vm.query.organizationName;
                showUpdateToast();
            }
        }

        function showUpdateToast() {
            var content = 'Already up to date!';
            showToast(content, 'update');
        }

        function showToast(content, buttonName) {
            var toast = $mdToast.simple()
                .content(content)
                .action(buttonName)
                .highlightAction(false)
                .position('bottom left');
            $mdToast.show(toast).then(function (response) {
                if (response == 'ok') {
                    newSearch();
                }
            });
        }

        function getFiltersData(organizationName) {
            OrganizationPersonsPropertiesFactory.get({
                    organizationName: organizationName
                },
                function (data) {
                    vm.filters = data;
                    vm.organizationId = data.organizationId;
                }, function (e) {
                    console.error(e);
                });
        }

        function daysBetween(date1, date2) {
            var ONE_DAY = 1000 * 60 * 60 * 24;
            var date1_ms = date1.getTime();
            var date2_ms = date2.getTime();
            var difference_ms = Math.abs(date1_ms - date2_ms);
            return Math.round(difference_ms / ONE_DAY);
        }

        function findJobByOrganization(array, organizationName) {
            for (var i = 0; i < array.length; i++) {
                if (array[i].organization.name == organizationName) {
                    return array[i];
                }
            }
        }

    }
}());