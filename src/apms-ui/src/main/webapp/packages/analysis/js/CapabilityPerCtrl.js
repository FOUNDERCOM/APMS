/* ***************************************************************************
 * Copyright (C) 2018-2019 the original author Jimmybly Lee
 * or authors of NAPTUNE.COM
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of MIT License as published by
 * the Free Software Foundation;
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the MIT License for more details.
 *
 * You should have received a copy of the MIT License along
 * with this library; if not, write to the Free Software Foundation.
 * ***************************************************************************/

/**
 * Description: 学历学位.<br>
 * Created by jimmy on 2018/4/2.
 * @author Jimmybly Lee
 */

angular.module('WebApp').controller('CapabilityPerCtrl', ['$rootScope', '$scope', "$listService", "$ajaxCall", function ($rootScope, $scope, $listService, $ajaxCall) {
    $ajaxCall.post({
        data : {
            controller: "AnalysisController",
            method: $rootScope["token"]['user']['org']['bureau']['id'] === '-100' ? 'getBureauPerStatistics' : 'getStationPerStatistics',
            bureauId: $rootScope["token"]['user']['org']['bureau']['id']
        },
        success: function(res) {
            var makeData = function(result) {
                var data = [];
                $.each(result, function (foo, bureau) {
                    var value = {"name": Object.keys(bureau)[0].split("_")[0], "id": Object.keys(bureau)[0].split("_")[1], "type": Object.keys(bureau)[0].split("_")[2], "value": bureau[Object.keys(bureau)[0]].toFixed(2) };
                    data.push(value);
                });
                return data;
            };
            console.log(makeData(res['result']));
            var chart = AmCharts.makeChart( "chart_per", {
                "type": "serial",
                "theme": "light",
                "dataProvider": makeData(res['result']),
                "valueAxes": [ {
                    "gridColor": "#FFFFFF",
                    "gridAlpha": 0.2,
                    "dashLength": 0
                } ],
                "gridAboveGraphs": true,
                "startDuration": 1,
                "graphs": [ {
                    "balloonText": "[[category]]: <b>[[value]]%</b>",
                    "fillAlphas": 0.8,
                    "lineAlpha": 0.2,
                    "type": "column",
                    "valueField": "value"
                } ],
                "chartCursor": {
                    "categoryBalloonEnabled": false,
                    "cursorAlpha": 0,
                    "zoomable": false
                },
                "categoryField": "name",
                "categoryAxis": {
                    "gridPosition": "start",
                    "gridAlpha": 0,
                    "tickPosition": "start",
                    "tickLength": 20
                },
                "export": {
                    "enabled": true
                }

            } );
            chart.addListener("clickGraphItem", function(event) {
                if ($rootScope["token"]['user']['org']['bureau']['id'] !== '-100') {
                    return;
                }
                $ajaxCall.post({
                    data: {
                        controller: "AnalysisController",
                        method: event.item.dataContext['type'] === 'BUREAU' ? 'getStationPerStatistics' : 'getBureauPerStatistics',
                        bureauId: event.item.dataContext["id"]
                    },
                    success: function (res) {
                        chart.dataProvider = makeData(res['result']);
                        chart.validateData();
                    }
                });
            });
        }
    });
}]);
