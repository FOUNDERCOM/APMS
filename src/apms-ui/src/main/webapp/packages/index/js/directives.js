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
 * Description: GLobal Directives.<br>
 * Created by Jimmybly Lee on 2017/9/10.
 * @author Jimmybly Lee
 */

// Route State Load Spinner(used on page or content load)
angular.module("WebApp").directive('ngSpinnerBar', ['$rootScope', '$state',
    function($rootScope, $state) {
        return {
            link: function(scope, element, attrs) {
                // by default hide the spinner bar
                element.addClass('hide'); // hide spinner bar by default

                // display the spinner bar whenever the route changes(the content part started loading)
                $rootScope.$on('$stateChangeStart', function() {
                    element.removeClass('hide'); // show spinner bar
                });

                // hide the spinner bar on route change success(after the content loaded)
                $rootScope.$on('$stateChangeSuccess', function(event) {
                    element.addClass('hide'); // hide spinner bar
                    $('body').removeClass('page-on-load'); // remove page loading indicator
                    Layout.setAngularJsSidebarMenuActiveLink('match', null, event.currentScope.$state); // activate selected link in the sidebar menu

                    // auto scroll to page top
                    setTimeout(function () {
                        App.scrollTop(); // scroll to the top on content load
                    }, $rootScope.settings.layout.pageAutoScrollOnLoad);
                });

                // handle errors
                $rootScope.$on('$stateNotFound', function() {
                    element.addClass('hide'); // hide spinner bar
                });

                // handle errors
                $rootScope.$on('$stateChangeError', function() {
                    element.addClass('hide'); // hide spinner bar
                });
            }
        };
    }
]);

// Handle global LINK click
angular.module("WebApp").directive('a', function() {
    return {
        restrict: 'E',
        link: function(scope, elem, attrs) {
            if (attrs.ngClick || attrs.href === '' || attrs.href === '#') {
                elem.on('click', function(e) {
                    e.preventDefault(); // prevent link click for above criteria
                });
            }
        }
    };
});

// Handle Dropdown Hover Plugin Integration
angular.module("WebApp").directive('dropdownMenuHover', function () {
    return {
        link: function (scope, elem) {
            elem.dropdownHover();
        }
    };
});

angular.module('WebApp').directive("tablePagingFooter", ["$rootScope", function($rootScope) {
    return {
        restrict: "A",
        link: function() {
            return null;
        },
        templateUrl: "packages/index/tpl/table-paging-footer.html" + $rootScope.cacheVersion
    }
}]);

angular.module('WebApp').directive("bsSwitch", [function() {
    return {
        link: function(scope, element) {
            element.bootstrapSwitch();
        }
    }
}]);

angular.module('WebApp').directive("bsPopover", [function() {
    return {
        link: function(scope, element) {
            element.popover({html:true, trigger: 'click'}).on('shown.bs.popover', function() {
                console.log("hello");
                var popover = $(this);
                console.log(popover);
                console.log($(this).parent().find('div.popover .close'));
                $(this).parent().find('div.popover .close').on('click', function() {
                    popover.popover('hide');
                })
            })
        }
    }
}]);
