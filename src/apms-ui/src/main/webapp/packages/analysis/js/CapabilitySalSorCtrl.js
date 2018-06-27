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

angular.module('WebApp').controller('CapabilitySalSorCtrl', ['$rootScope', '$scope', "$listService", "$ajaxCall", function ($rootScope, $scope, $listService, $ajaxCall) {
    $ajaxCall.post({
        data : {
            controller: "AnalysisController",
            method: $rootScope["token"]['user']['org']['bureau']['id'] === '-100' ? 'getBureauSalSorStatistics' : 'getStationSalSorStatistics',
            bureauId: $rootScope["token"]['user']['org']['bureau']['id']
        },
        success: function(res) {
            var makeData = function(result) {
                var data = [];
                $.each(result, function (foo, bureau) {
                    var value = {"name": Object.keys(bureau)[0].split("_")[0], "id": Object.keys(bureau)[0].split("_")[1], "type": Object.keys(bureau)[0].split("_")[2]};
                    $.each(bureau[Object.keys(bureau)[0]], function (k, o) {
                        value[k] = o;
                    });
                    data.push(value);
                });
                return data;
            };


            var fields = [];
            $.each(['政府财政', '公安经费自支', '所队自筹', '其它'], function(foo, field) {
                fields.push({
                    "balloonText": "<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span>",
                    "fillAlphas": 0.8,
                    "labelText": "[[value]]",
                    "lineAlpha": 0.3,
                    "title": field,
                    "type": "column",
                    "color": "#000000",
                    "valueField": field
                });
            });
            var chart = AmCharts.makeChart("chart_sal_source", {
                "type": "serial",
                "theme": "light",
                "legend": {
                    "horizontalGap": 10,
                    "maxColumns": 1,
                    "position": "right",
                    "useGraphSettings": true,
                    "markerSize": 10
                },
                "dataProvider": makeData(res['result']),
                "valueAxes": [{
                    "stackType": "regular",
                    "axisAlpha": 0.3,
                    "gridAlpha": 0
                }],
                "graphs": fields,
                "categoryField": "name",
                "categoryAxis": {
                    "gridPosition": "start",
                    "axisAlpha": 0,
                    "gridAlpha": 0,
                    "position": "left"
                },
                "export": {
                    "enabled": true
                }

            });
            chart.addListener("clickGraphItem", function(event) {
                console.log("id:", event.item.dataContext["id"]);
                console.log("type:", event.item.dataContext['type']);
                console.log("name:", event.item.category);
                if ($rootScope["token"]['user']['org']['bureau']['id'] !== '-100') {
                    return;
                }
                $ajaxCall.post({
                    data: {
                        controller: "AnalysisController",
                        method: event.item.dataContext['type'] === 'BUREAU' ? 'getStationSalSorStatistics' : 'getBureauSalSorStatistics',
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
