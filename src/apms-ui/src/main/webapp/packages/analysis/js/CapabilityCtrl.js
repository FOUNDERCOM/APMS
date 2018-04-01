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
    var chart_0 = AmCharts.makeChart("chart_0", {
        "type": "serial",
        "theme": "light",
        "legend": {
            "horizontalGap": 10,
            "maxColumns": 1,
            "position": "right",
            "useGraphSettings": true,
            "markerSize": 10
        },
        "dataProvider": [{
            "year": 2003,
            "europe": 100,
            "namerica": 2.5,
            "asia": 2.1
        }, {
            "year": 2004,
            "europe": 2.6,
            "namerica": 2.7,
            "asia": 2.2,
            "lamerica": 0.3,
            "meast": 0.3,
            "africa": 0.1
        }, {
            "year": 2005,
            "europe": 2.8,
            "namerica": 2.9,
            "asia": 2.4,
            "lamerica": 0.3,
            "meast": 0.3,
            "africa": 0.1
        }],
        "valueAxes": [{
            "stackType": "regular",
            "axisAlpha": 0.3,
            "gridAlpha": 0
        }],
        "graphs": [{
            "balloonText": "<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span>",
            "fillAlphas": 0.8,
            "labelText": "[[value]]",
            "lineAlpha": 0.3,
            "title": "Europe",
            "type": "column",
            "color": "#000000",
            "valueField": "europe"
        }, {
            "balloonText": "<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span>",
            "fillAlphas": 0.8,
            "labelText": "[[value]]",
            "lineAlpha": 0.3,
            "title": "North America",
            "type": "column",
            "color": "#000000",
            "valueField": "namerica"
        }, {
            "balloonText": "<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span>",
            "fillAlphas": 0.8,
            "labelText": "[[value]]",
            "lineAlpha": 0.3,
            "title": "Asia-Pacific",
            "type": "column",
            "color": "#000000",
            "valueField": "asia"
        }, {
            "balloonText": "<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span>",
            "fillAlphas": 0.8,
            "labelText": "[[value]]",
            "lineAlpha": 0.3,
            "title": "Latin America",
            "type": "column",
            "color": "#000000",
            "valueField": "lamerica"
        }, {
            "balloonText": "<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span>",
            "fillAlphas": 0.8,
            "labelText": "[[value]]",
            "lineAlpha": 0.3,
            "title": "Middle-East",
            "type": "column",
            "color": "#000000",
            "valueField": "meast"
        }, {
            "balloonText": "<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span>",
            "fillAlphas": 0.8,
            "labelText": "[[value]]",
            "lineAlpha": 0.3,
            "title": "Africa",
            "type": "column",
            "color": "#000000",
            "valueField": "africa"
        }],
        "categoryField": "year",
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
