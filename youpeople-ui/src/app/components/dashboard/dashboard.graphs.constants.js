(function () {

    'use strict';

    angular
        .module('youpeople.dashboard')
        .constant('graphOptions', {
            YEARS_AFTER: {
                chart: {
                    type: "discreteBarChart",
                    height: 350,
                    margin: {
                        top: 20,
                        right: 20,
                        bottom: 60,
                        left: 50
                    },
                    x: function (d) {
                        return d.name;
                    },
                    y: function (d) {
                        return d.count;
                    },
                    showValues: true,
                    tooltips: false,
                    transitionDuration: 500,
                    showControls: true,
                    xAxis: {
                        showMaxMin: false
                    },
                    yAxis: {
                        axisLabel: 'People'
                    }
                }
            },

            ALUMNI_MAP: {
                scope: 'world',
                projection: "mercator",
                responsive: true,
                options: {
                    width: null,
                    height: null,
                    staticGeoData: true,
                    legendHeight: 60 // optionally set the padding for the legend
                },
                geographyConfig: {
                    highlighBorderColor: '#EAA9A8',
                    highlighBorderWidth: 2,
                    popupTemplate: function (geography, data) {
                        var result = '<div class="hoverinfo"><strong>' +
                            geography.properties.name +
                            '</strong>';
                        if (data !== null) {
                            result += ': ' +
                                (data.value || '0');
                        }
                        result += '</div>';
                        return result;
                    }
                },
                fills: {
                    'HIGH': '#CC4731',
                    'MEDIUM': '#306596',
                    'LOW': '#667FAF',
                    'defaultFill': '#DDDDDD'
                }
            },

            INDUSTRIES: {
                chart: {
                    type: 'pieChart',
                    height: 350,
                    x: function (d) {
                        return d.name;
                    },
                    y: function (d) {
                        return d.count;
                    },
                    showLabels: true,
                    transitionDuration: 500,
                    //labelsOutside: true,
                    labelThreshold: 0.02
                }
            },

            EMPLOYEES_NUMBER: {
                chart: {
                    type: 'stackedAreaChart',
                    height: 350,
                    x: function (d) {
                        return d.year;
                    },
                    y: function (d) {
                        return d.data;
                    },
                    useVoronoi: false,
                    clipEdge: true,
                    transitionDuration: 500,
                    useInteractiveGuideline: true,
                    showControls: false,
                    xAxis: {
                        showMaxMin: false
                    },
                    yAxis: {
                        tickFormat: function (d) {
                            return d3.format(',.2f')(d);
                        }
                    }
                }
            },

            EMPLOYEES_TURNOVER: {
                chart: {
                    type: 'cumulativeLineChart',
                    height: 350,
                    x: function (d) {
                        return d.year;
                    },
                    y: function (d) {
                        return d.turnover;
                    },

                    color: d3.scale.category10().range(),
                    transitionDuration: 500,
                    useInteractiveGuideline: true,
                    clipVoronoi: false,
                    showControls: false,
                    xAxis: {
                        showMaxMin: false
                    },

                    yAxis: {
                        axisLabel: 'Y Axis',
                        tickFormat: function (d) {
                            return d3.format(',.1%')(d);
                        },
                        axisLabelDistance: 20
                    }
                }
            },

            F_BIG_CHARTS: {
                chart: {
                    type: 'cumulativeLineChart',
                    height: 350,
                    x: function (d) {
                        return d[0];
                    },
                    y: function (d) {
                        return d[1];
                    },

                    color: d3.scale.category10().range(),
                    transitionDuration: 500,
                    useInteractiveGuideline: true,
                    clipVoronoi: false,
                    showControls: false,
                    xAxis: {
                        tickFormat: function (d) {
                            return d3.time.format('%Y')(new Date(d));
                        },
                        showMaxMin: false
                    },

                    yAxis: {
                        axisLabelDistance: 20
                    }
                }
            }
        })
        .constant('mapPlugins', {
            ALUMNI_MAP: {
                customLegend: function (layer, data, options) {
                    var html = ['<ul class="list-inline">'],
                        label = '';
                    for (var fillKey in this.options.fills) {
                        html.push('<li class="key" ',
                            'style="border-top: 10px solid ' + this.options.fills[fillKey] + '">',
                            (fillKey === 'defaultFill' ? 'N/A' : fillKey),
                            '</li>');
                    }
                    html.push('</ul>');
                    d3.select(this.options.element).append('div')
                        .attr('class', 'datamaps-legend')
                        .style('position', 'absolute')
                        .style('bottom', 0)
                        .html(html.join(''));
                }
            }
        });
})();