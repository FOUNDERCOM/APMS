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
 * Description: TODO.<br>
 * Created by Jimmybly Lee on 2017/9/29.
 * @author Jimmybly Lee
 */
angular.module('WebApp').controller('CapabilityCtrl', ['$rootScope', '$scope', "$listService", "$ajaxCall", function ($rootScope, $scope, $listService, $ajaxCall) {
    $scope.point = [];
    var title = ["七星关", "黔西", "纳雍", "大方", "金沙", "织金", "赫章"];
    var i = 0, j = 0, sum = 0;
    var data = [
        [{
            "country": "男女比例",
            "litres": 90
        }, {
            "country": "高学历",
            "litres": 60
        }, {
            "country": "年轻",
            "litres": 90
        }, {
            "country": "健康",
            "litres": 100
        }, {
            "country": "经验丰富",
            "litres": 40
        }, {
            "country": "党员",
            "litres": 20
        }],
        [{
            "country": "男女比例",
            "litres": 95
        }, {
            "country": "高学历",
            "litres": 70
        }, {
            "country": "年轻",
            "litres": 90
        }, {
            "country": "健康",
            "litres": 100
        }, {
            "country": "经验丰富",
            "litres": 80
        }, {
            "country": "党员",
            "litres": 30
        }],
        [{
            "country": "男女比例",
            "litres": 80
        }, {
            "country": "高学历",
            "litres": 90
        }, {
            "country": "年轻",
            "litres": 100
        }, {
            "country": "健康",
            "litres": 90
        }, {
            "country": "经验丰富",
            "litres": 90
        }, {
            "country": "党员",
            "litres": 10
        }],
        [{
            "country": "男女比例",
            "litres": 100
        }, {
            "country": "高学历",
            "litres": 30
        }, {
            "country": "年轻",
            "litres": 60
        }, {
            "country": "健康",
            "litres": 95
        }, {
            "country": "经验丰富",
            "litres": 70
        }, {
            "country": "党员",
            "litres": 10
        }],
        [{
            "country": "男女比例",
            "litres": 100
        }, {
            "country": "高学历",
            "litres": 20
        }, {
            "country": "年轻",
            "litres": 50
        }, {
            "country": "健康",
            "litres": 100
        }, {
            "country": "经验丰富",
            "litres": 40
        }, {
            "country": "党员",
            "litres": 20
        }],
        [{
            "country": "男女比例",
            "litres": 80
        }, {
            "country": "高学历",
            "litres": 20
        }, {
            "country": "年轻",
            "litres": 90
        }, {
            "country": "健康",
            "litres": 100
        }, {
            "country": "经验丰富",
            "litres": 10
        }, {
            "country": "党员",
            "litres": 10
        }],
        [{
            "country": "男女比例",
            "litres": 85
        }, {
            "country": "高学历",
            "litres": 30
        }, {
            "country": "年轻",
            "litres": 95
        }, {
            "country": "健康",
            "litres": 95
        }, {
            "country": "经验丰富",
            "litres": 5
        }, {
            "country": "党员",
            "litres": 10
        }]
    ];
    for (i = 0; i < data.length; i++) {
        sum = 0;
        for (j = 0; j < data[i].length; j++) {
            sum += data[i][j]['litres'];
        }
        $scope.point.push({
            "date": title[i],
            "townName": title[i],
            "value": Math.round(sum*100/data[i].length)/100
        });
    }

    var chart2 = AmCharts.makeChart("chart_0", {
        type: "serial",
        fontSize: 12,
        fontFamily: "Open Sans",
        dataDateFormat: "YYYY-MM-DD",
        dataProvider: $scope.point,

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
            valueField: "value",
            title: "平均分",
            type: "column",
            fillAlphas: 0.4,
            valueAxis: "a1",
            balloonText: "[[value]] 分",
            legendValueText: "[[value]] 分",
            legendPeriodValueText: "平均分: [[value.sum]] 分",
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

    for (i = 0; i < data.length; i++) {
        var chart = AmCharts.makeChart("chart_" + (i + 1), {
            "type": "radar",
            "theme": "light",

            "fontFamily": 'Open Sans',

            "color":    '#888',

            "dataProvider": data[i],
            "valueAxes": [{
                "axisTitleOffset": 20,
                "minimum": 0,
                "axisAlpha": 0.15
            }],
            "startDuration": 2,
            "graphs": [{
                "balloonText": "[[value]] %",
                "bullet": "round",
                "valueField": "litres"
            }],
            "categoryField": "country",
            "exportConfig": {
                "menuTop": "10px",
                "menuRight": "10px",
                "menuItems": [{
                    "icon": '/lib/3/images/export.png',
                    "format": 'png'
                }]
            }
        });

        $('#chart_' + (i + 1)).closest('.portlet').find('.fullscreen').click(function() {
            chart.invalidateSize();
        });
    }
}]);
