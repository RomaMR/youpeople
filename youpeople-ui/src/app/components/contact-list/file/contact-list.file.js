(function () {

    'use strict';

    angular
        .module('youpeople.contactList')
        .controller('ContactListFileController', ContactListFileController);

    /** @ngInject */
    function ContactListFileController($mdToast, Upload, PersonsOrganizationFileFactory, PersonsSearchFactory, OrganizationPersonsPropertiesFactory,
                                       SearchOrganizationLogFactory, SettingsService) {
        var vm = this;

        vm.organization = {};
        vm.filters = {};
        vm.contactsFile = {};
        vm.persons = [];
        vm.loading = false;
        vm.loaded = false;
        vm.count = 0;
        vm.query = {
            organizationId: '',
            order: 'lastName',
            workStatus: '',
            locality: '',
            industry: '',
            limit: 10,
            page: 1
        };

        vm.search = search;
        vm.newSearch = newSearch;
        vm.filter = filter;
        vm.selectFile = selectFile;
        vm.uploadFile = uploadFile;

        activate();

        function activate() {
            vm.organization = SettingsService.getCurrentOrganization();
            vm.query.organizationId = vm.organization.id;
            search();
        }

        function search() {
            if (vm.query.organizationId) {
                vm.loading = true;
                vm.loaded = false;
                vm.persons = [];
                vm.query.locality = (!vm.query.locality)?'':vm.query.locality;
                vm.query.industry = (!vm.query.industry)?'':vm.query.industry;
                PersonsOrganizationFileFactory.query(vm.query,
                    function (data, headers) {
                        processPersons(data, headers, true);
                        vm.loading = false;
                        vm.loaded = true;
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
            if (vm.query.organizationId !== '') {
                vm.loading = true;
                vm.loaded = false;
                vm.persons = [];

                var query = angular.copy(vm.query);
                query.organizationName = vm.organization.name;

                PersonsSearchFactory.query(query,
                    function (data, headers) {
                        processPersons(data, headers);
                        vm.loading = false;
                        vm.loaded = true;
                    }, function (e) {
                        console.error(e);
                        vm.loading = false;
                    });
            }
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
            getFiltersData(vm.organization.id);
            //if (firstRequest && showToast) {
            //    firstRequest = false;
            //    showUpdateToast();
            //}
            //if (!ResultsService.results) {
            //    ResultsService.results = {};
            //}
            //ResultsService.results.query = vm.query;
            //ResultsService.results.persons = vm.persons;
            //ResultsService.results.headers = headers;
        }

        function showUpdateToast() {
            var content = 'Already up to date!';
            SearchOrganizationLogFactory.query({
                organizationId: vm.organization.id
            }, function (data) {
                vm.searchLog = data;
                var days = daysBetween(new Date(vm.searchLog[0].searchTimestamp), new Date());
                if (days > 1) {
                    content = 'Database was updated ' + days + ' days ago';
                }
                showToast(content, 'update');
            }, function (e) {
                console.log(e);
                showToast('It\'s first time you are looking for it', 'update');
            });
        }

        function selectFile(file) {
            if (file) {
                vm.contactsFile = file;
            }
        }

        function uploadFile() {
            Upload.upload({
                url: '/api/persons/organization/' + vm.organization.id + '/upload',
                file: vm.contactsFile
            }).progress(function (evt) {
                vm.progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
            }).success(function (data, status, headers, config) {
                $mdToast.show(
                    $mdToast.simple()
                        .content(config.file.name + ' have been uploaded')
                        .position('bottom left')
                        .hideDelay(3000)
                );
            }).error(function (data) {
                console.error(data);
            });
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

        function getFiltersData(organizationId) {
            OrganizationPersonsPropertiesFactory.get({
                organizationId: organizationId
            }, function (data) {
                vm.filters = data;
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