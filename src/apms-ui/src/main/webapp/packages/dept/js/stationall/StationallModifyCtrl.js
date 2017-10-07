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
 * Description: 更新科所队控制器.<br>
 * Created by Jimmybly Lee on 2017/7/3.
 * @author Jimmybly Lee
 */
angular.module('WebApp').controller('StationallModifyCtrl', ['$scope', "$ajaxCall", function ($scope, $ajaxCall) {

    var form = $('.modifyStationallForm');
    var error = $('.alert-danger', form);
    var success = $('.alert-success', form);

    form.validate({
        errorElement: 'span', //default input error message container
        errorClass: 'help-block help-block-error', // default input error message class
        focusInvalid: false, // do not focus the last invalid input
        ignore: "",  // validate all fields including form hidden input
        rules: {
            name: {
                minlength: 2,
                maxlength: 32,
                required: true
            },
            strength: {
                min: 0,
                max: 99999,
                required: true,
                number: true
            },
            contactName: {
                minlength: 2,
                maxlength: 8,
                required: true
            },
            contactTel: {
                maxlength: 16,
                required: true
            },
            contactMobile: {
                maxlength: 16,
                required: true
            }
        },
        invalidHandler: function (event, validator) {
            success.hide();
            error.show();
            App.scrollTo(error, -200);
        },
        errorPlacement: function (error, element) {
            var cont = $(element).parent('.input-group');
            if (cont.size() > 0) {
                cont.after(error);
            } else {
                element.after(error);
            }
        },
        highlight: function (element) {
            $(element).closest('.form-group').addClass('has-error').removeClass("has-success");
        },
        unhighlight: function (element) {
            $(element).closest('.form-group').removeClass('has-error').addClass("has-success");
        },
        success: function (label, element) {
            label.closest('.form-group').removeClass('has-error');
        },
        submitHandler: function (form) {
            success.show();
            error.hide();
        }
    });

    $scope.clear = function() {
        $(".form-group").removeClass("has-error").removeClass('has-success');
        $('#name-error').hide();
        $('#strength-error').hide();
        error.hide();
        success.hide();
    };

    /**
     * 提交表单
     */
    $scope.submit = function() {
        if (form.validate().form()) {
            $ajaxCall.post({
                data : {
                    controller: "DeptStationController",
                    method: $scope.method,
                    entity : JSON.stringify($scope.entity)
                },
                success: function() {
                    $scope.$emit("submitted");
                    $("#modifyStationallModalDiv").modal('hide');
                }
            });
        }
    };

}]);
