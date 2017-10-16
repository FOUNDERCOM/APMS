/* ***************************************************************************
 * EZ.JWAF/EZ.JCWAP: Easy series Production.
 * Including JWAF(Java-based Web Application Framework)
 * and JCWAP(Java-based Customized Web Application Platform).
 * Copyright (C) 2016-2017 the original author or authors.
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
 * Description: HomeController.<br>
 * Created by Jimmybly Lee on 2017/9/10.
 * @author Jimmybly Lee
 */
angular.module("WebApp").controller('HomeCtrl', ['$rootScope', '$scope', '$ajaxCall', function($rootScope, $scope, $ajaxCall) {

    $ajaxCall.post({
        data : {
            controller: "AnalysisController",
            method: 'getHomeBaseInfo'
        },
        success: function(res) {
            $scope.auxNum = res.auxNum;
            $scope.avgSalary = res.avgSalary;
            $scope.degreeNum = res.degreeNum;
            $scope.goodPern = res.goodPern;
        }
    });

    var initAmChart1 = function() {
        if (typeof(AmCharts) === 'undefined' || $('#chart_5').size() === 0) {
            return;
        }

        var chartData = [{
            "date": "七星关",
            "distance": 1236,
            "townName": "七星关",
            "townName2": "最高",
            "townSize": 25,
            "latitude": 40.71,
            "salary": 2300,
            "duration": 408
        }, {
            "date": "黔西",
            "distance": 847,
            "townName": "黔西",
            "townSize": 14,
            "latitude": 38.89,
            "salary": 2100,
            "duration": 482
        }, {
            "date": "纳雍",
            "distance": 923,
            "townName": "纳雍",
            "townSize": 6,
            "latitude": 34.22,
            "salary": 2400,
            "duration": 562
        }, {
            "date": "大方",
            "distance": 698,
            "townName": "大方",
            "townSize": 7,
            "latitude": 30.35,
            "salary": 2000,
            "duration": 379
        }, {
            "date": "黔西",
            "distance": 480,
            "townName": "黔西",
            "townName2": "最低",
            "townSize": 10,
            "latitude": 25.83,
            "salary": 1800,
            "duration": 501
        }, {
            "date": "金沙",
            "distance": 386,
            "townName": "金沙",
            "townSize": 7,
            "latitude": 30.46,
            "salary": 1800,
            "duration": 443
        }, {
            "date": "织金",
            "distance": 837,
            "townName": "织金",
            "townSize": 10,
            "latitude": 29.94,
            "salary": 2000,
            "duration": 405
        }, {
            "date": "赫章",
            "distance": 238,
            "townName": "赫章",
            "townSize": 16,
            "latitude": 29.76,
            "salary": 2500,
            "duration": 309
        }];
        var chart = AmCharts.makeChart("chart_5", {
            type: "serial",
            fontSize: 12,
            fontFamily: "Open Sans",
            dataDateFormat: "YYYY-MM-DD",
            dataProvider: chartData,

            addClassNames: true,
            startDuration: 1,
            color: "#6c7b88",
            marginLeft: 10,
            marginRight: 10,

            categoryField: "date",
            categoryAxis: {
                parseDates: false,
                minPeriod: "DD",
                autoGridCount: false,
                gridCount: 50,
                gridAlpha: 0.1,
                gridColor: "#FFFFFF",
                axisColor: "#555555",
                dateFormats: [{
                    period: 'DD',
                    format: 'DD'
                }, {
                    period: 'WW',
                    format: 'MMM DD'
                }, {
                    period: 'MM',
                    format: 'MMM'
                }, {
                    period: 'YYYY',
                    format: 'YYYY'
                }]
            },

            valueAxes: [{
                id: "a1",
                title: "辅警人数",
                gridAlpha: 0,
                axisAlpha: 0
            }, {
                id: "a2",
                position: "right",
                gridAlpha: 0,
                axisAlpha: 0,
                labelsEnabled: false
            }],
            graphs: [{
                id: "g1",
                valueField: "distance",
                title: "人数",
                type: "column",
                fillAlphas: 0.7,
                valueAxis: "a1",
                balloonText: "[[value]] 人",
                legendValueText: "[[value]] 人",
                legendPeriodValueText: "总共: [[value.sum]] 人",
                lineColor: "#1BA39C",
                alphaField: "alpha"
            }, {
                id: "g2",
                balloonText: "高学历率:[[value]]%",
                title: "高学历率",
                valueField: "latitude",
                classNameField: "bulletClass",
                type: "line",
                valueAxis: "a2",
                lineColor: "#E43A45",
                lineThickness: 1,
                legendValueText: "[[description]]/[[value]]",
                descriptionField: "townName",
                bullet: "round",
                bulletSizeField: "townSize",
                bulletBorderColor: "#525E64",
                bulletBorderAlpha: 1,
                bulletBorderThickness: 2,
                bulletColor: "#E43A45",
                labelText: "[[townName2]]",
                labelPosition: "right",
                showBalloon: true,
                animationPlayed: true,
            }],

            chartCursor: {
                zoomable: false,
                categoryBalloonDateFormat: "DD",
                cursorAlpha: 0,
                categoryBalloonColor: "#e26a6a",
                categoryBalloonAlpha: 0.8,
                valueBalloonsEnabled: false
            },
            legend: {
                bulletType: "round",
                equalWidths: false,
                valueWidth: 120,
                useGraphSettings: true,
                color: "#6c7b88"
            }
        });
        var chart2 = AmCharts.makeChart("chart_6", {
            type: "serial",
            fontSize: 12,
            fontFamily: "Open Sans",
            dataDateFormat: "YYYY-MM-DD",
            dataProvider: chartData,

            addClassNames: true,
            startDuration: 1,
            color: "#6c7b88",
            marginLeft: 10,
            marginRight: 10,

            categoryField: "date",
            categoryAxis: {
                parseDates: false,
                minPeriod: "DD",
                autoGridCount: false,
                gridCount: 50,
                gridAlpha: 0.1,
                gridColor: "#FFFFFF",
                axisColor: "#555555",
                dateFormats: [{
                    period: 'DD',
                    format: 'DD'
                }, {
                    period: 'WW',
                    format: 'MMM DD'
                }, {
                    period: 'MM',
                    format: 'MMM'
                }, {
                    period: 'YYYY',
                    format: 'YYYY'
                }]
            },

            valueAxes: [{
                id: "a1",
                title: "额定工资",
                gridAlpha: 0,
                axisAlpha: 0
            }, {
                id: "a2",
                position: "right",
                gridAlpha: 0,
                axisAlpha: 0,
                labelsEnabled: false
            }],
            graphs: [{
                id: "g1",
                valueField: "salary",
                title: "额定工资",
                type: "column",
                fillAlphas: 0.7,
                valueAxis: "a1",
                balloonText: "￥ [[value]]",
                legendValueText: "￥ [[value]]",
                legendPeriodValueText: "额定工资: ￥[[value.sum]]",
                lineColor: "#E87E04",
                alphaField: "alpha"
            }],

            chartCursor: {
                zoomable: false,
                categoryBalloonDateFormat: "DD",
                cursorAlpha: 0,
                categoryBalloonColor: "#e26a6a",
                categoryBalloonAlpha: 0.8,
                valueBalloonsEnabled: false
            },
            legend: {
                bulletType: "round",
                equalWidths: false,
                valueWidth: 120,
                useGraphSettings: true,
                color: "#6c7b88"
            }
        });
    };
    initAmChart1();
}]);
