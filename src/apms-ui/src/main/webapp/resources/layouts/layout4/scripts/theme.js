/**
Theme script to handle the theme
**/
var Theme = function () {

    // Handle Theme Settings
    var handleTheme = function () {

        var panel = $('.theme-panel');
        var body = $('body'), sidebar_menu = $(".page-sidebar-menu");

        if (body.hasClass('page-boxed') === false) {
            $('.layout-option', panel).val("fluid");
        }

        $('.sidebar-option', panel).val("default");
        $('.page-header-option', panel).val("fixed");
        $('.page-footer-option', panel).val("default");
        if ($('.sidebar-pos-option').attr("disabled") === false) {
            $('.sidebar-pos-option', panel).val(App.isRTL() ? 'right' : 'left');
        }

        //handle theme layout
        var resetLayout = function () {
            $("body").
            removeClass("page-boxed").
            removeClass("page-footer-fixed").
            removeClass("page-sidebar-fixed").
            removeClass("page-header-fixed").
            removeClass("page-sidebar-reversed");

            $('.page-header > .page-header-inner').removeClass("container");

            var container = $('.page-container'), footer = $('.page-footer');
            if (container.parent(".container").size() === 1) {
                container.insertAfter('body > .clearfix');
            }

            var footer_container = $('.page-footer > .container');
            if (footer_container.size() === 1) {
                footer.html(footer_container.html());
            } else if (footer.parent(".container").size() === 1) {
                footer.insertAfter('.page-container');
                $('.scroll-to-top').insertAfter('.page-footer');
            }

            $(".top-menu > .navbar-nav > li.dropdown").removeClass("dropdown-dark");

            $('body > .container').remove();
        };

        var lastSelectedLayout = '';

        var setLayout = function () {

            var container = $('.page-container'), footer = $('.page-footer'), header = $(".page-header")
                , sidebar_menu = $("page-sidebar-menu"), frontend_link = $('#frontend-link');
            var layoutOption = $('.layout-option', panel).val();
            var sidebarOption = $('.sidebar-option', panel).val();
            var headerOption = $('.page-header-option', panel).val();
            var footerOption = $('.page-footer-option', panel).val();
            var sidebarPosOption = $('.sidebar-pos-option', panel).val();
            var sidebarStyleOption = $('.sidebar-style-option', panel).val();
            var sidebarMenuOption = $('.sidebar-menu-option', panel).val();
            var headerTopDropdownStyle = $('.page-header-top-dropdown-style-option', panel).val();


            if (sidebarOption === "fixed" && headerOption === "default") {
                alert('Default Header with Fixed Sidebar option is not supported. Proceed with Fixed Header with Fixed Sidebar.');
                $('.page-header-option', panel).val("fixed");
                $('.sidebar-option', panel).val("fixed");
                sidebarOption = 'fixed';
                headerOption = 'fixed';
            }

            resetLayout(); // reset layout to default state

            if (layoutOption === "boxed") {
                $("body").addClass("page-boxed");

                // set header
                $('.page-header > .page-header-inner').addClass("container");
                // noinspection JSUnusedLocalSymbols
                var cont = $('body > .clearfix').after('<div class="container"></div>');

                // set content
                container.appendTo('body > .container');

                // set footer
                if (footerOption === 'fixed') {
                    footer.html('<div class="container">' + footer.html() + '</div>');
                } else {
                    footer.appendTo('body > .container');
                }
            }

            if (lastSelectedLayout !== layoutOption) {
                //layout changed, run responsive handler:
                App.runResizeHandlers();
            }
            lastSelectedLayout = layoutOption;

            //header
            if (headerOption === 'fixed') {
                $("body").addClass("page-header-fixed");
                header.removeClass("navbar-static-top").addClass("navbar-fixed-top");
            } else {
                $("body").removeClass("page-header-fixed");
                header.removeClass("navbar-fixed-top").addClass("navbar-static-top");
            }

            //sidebar
            if (body.hasClass('page-full-width') === false) {
                if (sidebarOption === 'fixed') {
                    $("body").addClass("page-sidebar-fixed");
                    sidebar_menu.addClass("page-sidebar-menu-fixed");
                    sidebar_menu.removeClass("page-sidebar-menu-default");
                    Layout.initFixedSidebarHoverEffect();
                } else {
                    $("body").removeClass("page-sidebar-fixed");
                    sidebar_menu.addClass("page-sidebar-menu-default");
                    sidebar_menu.removeClass("page-sidebar-menu-fixed");
                    sidebar_menu.unbind('mouseenter').unbind('mouseleave');
                }
            }

            // top dropdown style
            if (headerTopDropdownStyle === 'dark') {
                $(".top-menu > .navbar-nav > li.dropdown").addClass("dropdown-dark");
            } else {
                $(".top-menu > .navbar-nav > li.dropdown").removeClass("dropdown-dark");
            }

            //footer
            if (footerOption === 'fixed') {
                $("body").addClass("page-footer-fixed");
            } else {
                $("body").removeClass("page-footer-fixed");
            }

            //sidebar style
            if (sidebarStyleOption === 'compact') {
                sidebar_menu.addClass("page-sidebar-menu-compact");
            } else {
                sidebar_menu.removeClass("page-sidebar-menu-compact");
            }

            //sidebar menu
            if (sidebarMenuOption === 'hover') {
                if (sidebarOption === 'fixed') {
                    $('.sidebar-menu-option', panel).val("accordion");
                    alert("Hover Sidebar Menu is not compatible with Fixed Sidebar Mode. Select Default Sidebar Mode Instead.");
                } else {
                    sidebar_menu.addClass("page-sidebar-menu-hover-submenu");
                }
            } else {
                sidebar_menu.removeClass("page-sidebar-menu-hover-submenu");
            }

            //sidebar position
            if (App.isRTL()) {
                if (sidebarPosOption === 'left') {
                    $("body").addClass("page-sidebar-reversed");
                    frontend_link.tooltip('destroy').tooltip({
                        placement: 'right'
                    });
                } else {
                    $("body").removeClass("page-sidebar-reversed");
                    frontend_link.tooltip('destroy').tooltip({
                        placement: 'left'
                    });
                }
            } else {
                if (sidebarPosOption === 'right') {
                    $("body").addClass("page-sidebar-reversed");
                    frontend_link.tooltip('destroy').tooltip({
                        placement: 'left'
                    });
                } else {
                    $("body").removeClass("page-sidebar-reversed");
                    frontend_link.tooltip('destroy').tooltip({
                        placement: 'right'
                    });
                }
            }

            Layout.fixContentHeight(); // fix content height
            Layout.initFixedSidebar(); // reinitialize fixed sidebar
        };

        // handle theme colors
        var setColor = function (color) {
            var color_ = (App.isRTL() ? color + '-rtl' : color);
            $('#style_color').attr("href", Layout.getLayoutCssPath() + 'themes/' + color_ + ".css");
        };


        $('.theme-colors > li', panel).click(function () {
            var color = $(this).attr("data-theme");
            setColor(color);
            $('ul > li', panel).removeClass("active");
            $(this).addClass("active");

            if (color === 'dark') {
                $('.page-actions .btn').removeClass('red-haze').addClass('btn-default btn-transparent');
            } else {
                $('.page-actions .btn').removeClass('btn-default btn-transparent').addClass('red-haze');
            }
        });

        // set default theme options:

        if (body.hasClass("page-boxed")) {
            $('.layout-option', panel).val("boxed");
        }

        if (body.hasClass("page-sidebar-fixed")) {
            $('.sidebar-option', panel).val("fixed");
        }

        if (body.hasClass("page-header-fixed")) {
            $('.page-header-option', panel).val("fixed");
        }

        if (body.hasClass("page-footer-fixed")) {
            $('.page-footer-option', panel).val("fixed");
        }

        if (body.hasClass("page-sidebar-reversed")) {
            $('.sidebar-pos-option', panel).val("right");
        }

        if (sidebar_menu.hasClass("page-sidebar-menu-light")) {
            $('.sidebar-style-option', panel).val("light");
        }

        if (sidebar_menu.hasClass("page-sidebar-menu-hover-submenu")) {
            $('.sidebar-menu-option', panel).val("hover");
        }

        // noinspection JSUnusedLocalSymbols
        var sidebarOption = $('.sidebar-option', panel).val();
        // noinspection JSUnusedLocalSymbols
        var headerOption = $('.page-header-option', panel).val();
        // noinspection JSUnusedLocalSymbols
        var footerOption = $('.page-footer-option', panel).val();
        // noinspection JSUnusedLocalSymbols
        var sidebarPosOption = $('.sidebar-pos-option', panel).val();
        // noinspection JSUnusedLocalSymbols
        var sidebarStyleOption = $('.sidebar-style-option', panel).val();
        // noinspection JSUnusedLocalSymbols
        var sidebarMenuOption = $('.sidebar-menu-option', panel).val();

        $('.layout-option, .page-header-top-dropdown-style-option, .page-header-option, .sidebar-option, .page-footer-option, .sidebar-pos-option, .sidebar-style-option, .sidebar-menu-option', panel).change(setLayout);
    };

    // handle theme style
    var setThemeStyle = function(style) {
        var file = (style === 'rounded' ? 'components-rounded' : style === 'material' ? "components-md" : 'components');
        file = (App.isRTL() ? file + '-rtl' : file);
        $('#style_components').attr("href", App.getGlobalCssPath() + file + ".min.css");

        var plugin_file = (style === "material" ? "plugins-md" : "plugins");
        $('#style_plugins').attr("href", App.getGlobalCssPath() + plugin_file + ".min.css");

        if (style === "material") {
            $('body').addClass("page-md");
        } else {
            $('body').removeClass("page-md");
        }

        if (typeof Cookies !== "undefined") {
            Cookies.set('layout-style-option', style);
        }
    };

    return {

        //main function to initiate the theme
        init: function() {
            var layout_style_option = $('.theme-panel .layout-style-option');
            // handles style customer tool
            handleTheme();

            // handle layout style change
            layout_style_option.change(function() {
                 setThemeStyle($(this).val());
            });

            // set layout style from cookie
            if (typeof Cookies !== "undefined" && Cookies.get('layout-style-option') === 'rounded') {
                setThemeStyle(Cookies.get('layout-style-option'));
                layout_style_option.val(Cookies.get('layout-style-option'));
            }
        }
    };

}();

if (App.isAngularJsApp() === false) {
    jQuery(document).ready(function() {
        Theme.init(); // init metronic core componets
    });
}
