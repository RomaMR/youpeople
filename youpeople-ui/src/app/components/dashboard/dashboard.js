(function () {

    'use strict';

    angular
        .module('youpeople.dashboard', [])
        .controller('DashboardController', DashboardController);

    /** @ngInject */
    function DashboardController($cookies, graphOptions, mapPlugins, ResultsService, DashboardGraphsService, SettingsService) {
        var vm = this;

        vm.totalPageLikes = 580;
        vm.lastWeekChangePageLikes = 0.5;
        vm.newPageLikes = 3;
        vm.lastWeekChangeNewPageLikes = 200;
        vm.totalReach = 1069;
        vm.lastWeekChangeTotalReach = 1711.9;
        vm.postReach = 1051;
        vm.lastWeekChangePostReach = 4279;

        vm.organization = {};
        vm.industriesData = [];
        vm.industriesAggregationData = [];
        vm.yearsAfterData = [];
        vm.employeesNumberData = [];
        vm.employeesTurnoverData = [];
        vm.selectedIndustry = 'Selected industry';

        vm.yearsAfterOptions = graphOptions.YEARS_AFTER;
        vm.alumniMapOptions = graphOptions.ALUMNI_MAP;
        vm.mapPlugins = mapPlugins.ALUMNI_MAP;
        vm.industriesAggregationOptions = angular.copy(graphOptions.INDUSTRIES);
        vm.industriesAggregationOptions.chart.pie = {
            dispatch: {
                elementClick: function (t) {
                    getDashboardDataForIndustry(vm.organization.id, t.data);
                }
            }
        };
        vm.industriesOptions = graphOptions.INDUSTRIES;
        vm.employeesNumberOptions = graphOptions.EMPLOYEES_NUMBER;
        vm.employeesTurnoverOptions = graphOptions.EMPLOYEES_TURNOVER;
        vm.fBigOptions = graphOptions.F_BIG_CHARTS;

        activate();

        function activate() {
            vm.organization = SettingsService.getCurrentOrganization();
            if (ResultsService.results && ResultsService.results.graphs && ResultsService.results.query && ResultsService.results.query.organizationId == SettingsService.settings.defaultOrganization.id) {
                vm.industriesAggregationData = ResultsService.results.graphs.industriesAggregationData;
                vm.yearsAfterData = ResultsService.results.graphs.yearsAfterData;
                vm.alumniMapOptions.data = ResultsService.results.graphs.alumniMapOptionsData;
                vm.employeesTurnoverData = ResultsService.results.graphs.employeesTurnoverData;
                vm.employeesNumberData = ResultsService.results.graphs.employeesNumberData;
                vm.industriesData = ResultsService.results.graphs.industriesData;
                vm.fPageLikesData = ResultsService.results.graphs.fPageLikesData;
                vm.fPostReachData = ResultsService.results.graphs.fPostReachData;
                vm.fPostClicksData = ResultsService.results.graphs.fPostClicksData;
            } else  {
                getData();
            }

        }

        function getData() {
            if(!ResultsService.results.graphs) {
                ResultsService.results.graphs = {};
            }
            DashboardGraphsService.getIndustriesData(vm.organization.id)
                .then(function (data) {
                    vm.industriesAggregationData = data;
                    ResultsService.results.graphs.industriesAggregationData = vm.industriesAggregationData;
                    getDashboardDataForIndustry(vm.organization.id, vm.industriesAggregationData[0]);
                });
            DashboardGraphsService.getYearsAfterData(vm.organization.id)
                .then(function (data) {
                    vm.yearsAfterData = [{
                        key: "S1",
                        color: "#51A351",
                        values: data
                    }];
                    ResultsService.results.graphs.yearsAfterData = vm.yearsAfterData;
                });
            DashboardGraphsService.getAlumniMapData(vm.organization.id)
                .then(function (data) {
                    vm.alumniMapOptions.data = data;
                    ResultsService.results.graphs.alumniMapOptionsData = vm.alumniMapOptions.data;
                });
            DashboardGraphsService.getEmployeesTurnoverData(vm.organization.id)
                .then(function (data) {
                    vm.employeesTurnoverData = [{
                        key: "Employees",
                        values: data
                    }];
                    var numberOfEmployeesData = [];
                    var firedOrLeftData = [];
                    var hiredData = [];
                    for(var i=0; i<data.length; i++) {
                        numberOfEmployeesData.push({
                            year: data[i].year,
                            data: data[i].count
                        });
                        firedOrLeftData.push({
                            year: data[i].year,
                            data: data[i].fired
                        });
                        hiredData.push({
                            year: data[i].year,
                            data: data[i].hired
                        });
                    }
                    vm.employeesNumberData = [
                        {
                            key: "Number of employees",
                            values: numberOfEmployeesData
                        },

                        {
                            key: "Fired or Left",
                            values: firedOrLeftData
                        },

                        {
                            key: "Hired",
                            values: hiredData
                        }
                    ];
                    ResultsService.results.graphs.employeesTurnoverData = vm.employeesTurnoverData;
                    ResultsService.results.graphs.employeesNumberData = vm.employeesNumberData;
                });

            var until = new Date(),
                since = new Date(),
                facebookAuthenticationToken = $cookies.get('facebookAuthenticationToken');
            since.setDate(since.getDate() - 7);

            DashboardGraphsService.getFacebookMetricsData('GDGLviv', 'page_fans', facebookAuthenticationToken, since.getTime(), until.getTime(), 'DAY')
                .then(function (data) {
                    console.log(data);
                    vm.fPageLikesData = [{
                        key: "Page likes",
                        color: "#2A3551",
                        values: data
                    }];
                    ResultsService.results.graphs.fPageLikesData = vm.fPageLikesData;
                });
            //DashboardGraphsService.getFPostReachData(vm.organization.id)
            //    .then(function (data) {
            //        vm.fPostReachData = [{
            //            key: "Post Reach",
            //            color: "#CD9A47",
            //            values: data
            //        }];
            //        ResultsService.results.graphs.fPostReachData = vm.fPostReachData;
            //    });
            //DashboardGraphsService.getFPostClicksData(vm.organization.id)
            //    .then(function (data) {
            //        vm.fPostClicksData = [{
            //            key: "Post clicks",
            //            color: "#A7AEC1",
            //            values: data
            //        }];
            //        ResultsService.results.graphs.fPostClicksData = vm.fPostClicksData;
            //    });
        }

        function getDashboardDataForIndustry(organizationId, industryGroup) {
            vm.selectedIndustry = industryGroup.name;
            DashboardGraphsService.getIndustriesData(organizationId, industryGroup.id)
                .then(function (data) {
                    vm.industriesData = data;
                    ResultsService.results.graphs.industriesData = vm.industriesData;
                });
        }
    }

    /** @ngInject */
    DashboardController.prototype.canActivate = function ($cookies) {
        return $cookies.getObject('globals') !== undefined && $cookies.getObject('globals').currentUser;
    };
}());