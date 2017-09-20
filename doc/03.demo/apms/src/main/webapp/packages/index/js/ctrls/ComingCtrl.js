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
 * Description: Coming Soon Ctrl.<br>
 * Created by Jimmybly Lee on 2017/9/10.
 * @author Jimmybly Lee
 */
angular.module('WebApp').controller('ComingCtrl', ['$rootScope', '$scope', function($rootScope, $scope) {
    $scope.$on('$viewContentLoaded', function() {
        // initialize core components
        App.initAjax();

        // set default layout mode
        $rootScope.settings.layout.pageContentWhite = true;
        $rootScope.settings.layout.pageBodySolid = false;
        $rootScope.settings.layout.pageSidebarClosed = false;

        var div = $(".coming-soon-div");
        var height = App.getViewPort().height -
            $('.page-header').outerHeight(true) -
            $('.page-footer').outerHeight(true) -
            $('.page-title').outerHeight(true) -
            $('.page-bar').outerHeight(true) - 75;// margin-top 75px

        div.css("min-height", height);


        var austDay = new Date();
        austDay = new Date(austDay.getFullYear(), austDay.getMonth() + 1 , 1);
        $('#defaultCountdown').countdown({until: austDay});
        $('#year').text(austDay.getFullYear());

        div.backstretch([
            "packages/index/img/bg/1.jpg",
            "packages/index/img/bg/2.jpg",
            "packages/index/img/bg/3.jpg",
            "packages/index/img/bg/4.jpg"
        ], {
            fade: 1000,
            duration: 10000
        });
    });
}]);
