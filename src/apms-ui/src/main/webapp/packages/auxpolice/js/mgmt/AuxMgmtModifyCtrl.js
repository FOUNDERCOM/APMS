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
 * Description: 辅警管理修改控制器.<br>
 * Created by Jimmybly Lee on 2017/9/17.
 * @author Jimmybly Lee
 */
angular.module('WebApp').controller('AuxMgmtModifyCtrl', ['$rootScope', '$scope', "$listService", "$ajaxCall", function ($rootScope, $scope, $listService, $ajaxCall) {

    /**
     * 提交表单
     */
    $scope.submit = function() {
        if ($scope.validate()) {
            var save = function() {
                $scope.entity.sex = {
                    "code" : IDCardValidator.sex($scope.entity.identityCard) === "male" ? "1" : "2"
                };
                var idCard = $scope.entity.identityCard;
                if (idCard.length === 15) {
                    $scope.entity.birthday = "19" + idCard.substring(6, 8) + "-" + idCard.substring(8, 10) + "-" + idCard.substring(10, 12);
                } else {
                    $scope.entity.birthday =  idCard.substring(6, 10) + "-" + idCard.substring(10, 12) + "-" + idCard.substring(12, 14);
                }
                $ajaxCall.post({
                    data : {
                        controller: "AuxController",
                        method: $scope.method,
                        entity : JSON.stringify($scope.entity)
                    },
                    success: function() {
                        $scope.$emit("submitted");
                    }
                });
                $(".modal").modal('hide');
            };

            var confirm = function() {
                bootbox.dialog({
                    title: "请确认",
                    message: "数据中有重复的身份证信息是否仍然提交？",
                    buttons: {
                        main: {label: " 取 消 ", className: "dark icon-ban btn-outline"},
                        danger: {
                            label: " 提  交 ！ ",
                            className: "fa fa-check red",
                            callback: function () {
                                save();
                            }
                        }
                    }
                });
            };

            $ajaxCall.post({
                data: {
                    controller: "AuxController",
                    method: "checkDuplicatedIdentityCard",
                    id: $scope.entity.id,
                    card: $scope.entity.identityCard
                },
                success: function(res) {
                    if (res.result) {
                        confirm();
                    } else {
                        save();
                    }
                }
            });
        }
    };

    /**
     * 删除辅助信息
     * @param idx 序号
     * @param key entity中的名称
     * @param id 辅助信息的id
     * @param type 辅助信息的java类
     */
    $scope.remove = function(idx, key, id, type) {
        $scope.entity[key].splice(idx,1);
        if (id) {
            $ajaxCall.post({
                data : {
                    controller: "AuxController",
                    method: "removeStuff",
                    id : id,
                    type: type
                }
            })
        }
    };

    /**
     * 校验当前实体，一些必须为非空的东西需要有所提示
     */
    $scope.validate = function() {
        var message = "";
        var nullCheck = function(key, msg, parent, isWithId) {
            var dom = $("*[ng-model='entity." + key + (isWithId ? ".id'" : "'")).parents(parent ? "." + parent : ".form-group");
            dom.removeClass("has-error");
            if ($scope.entity[key] === undefined
                || (!isWithId && $scope.entity[key].length === 0)
                || (isWithId && ($scope.entity[key]["id"] === undefined || $scope.entity[key]["id"].length === 0))) {
                message += msg + "不能为空;";
                dom.addClass("has-error");
            }
        };

        // base
        nullCheck("mobile", "手机", "input-icon");
        nullCheck("station", "科所队", "input-icon", true);
        nullCheck("job", "职务", "input-icon");
        nullCheck("addProvince", "住址", "input-icon");
        nullCheck("addCity", "住址", "input-icon");
        nullCheck("addCountry", "住址", "input-icon");
        nullCheck("addDetail", "住址", "input-icon");
        nullCheck("addPostcode", "邮编", "input-icon");

        // detail
        nullCheck("name", "姓名");
        nullCheck("identityCard", "身份证号");
        nullCheck("joinDate", "入职时间");
        nullCheck("oldIdentity", "入职前身份", undefined, true);
        nullCheck("nation", "民族", undefined, true);
        nullCheck("health", "健康状况", undefined, true);
        nullCheck("politicalStatus", "政治面貌", undefined, true);
        nullCheck("eduDegree", "文化程度", undefined, true);
        nullCheck("institutions", "毕业院校");
        nullCheck("major", "所学专业");

        if ($scope.entity.identityCard && $scope.entity.identityCard.length > 0 && !IDCardValidator.validate($scope.entity.identityCard)) {
            var identityCardInputGroup = $("*[ng-model='entity." + "identityCard" +  "']").parents('.form-group');
            message += "身份证号格式不正确";
            identityCardInputGroup.addClass("has-error");
        }

        if (message.length > 0) {
            App.alert({
                container: $("#modifyErrorMsgDiv"),
                place: 'append', // append or prepent in container
                type: 'warning', // alert's type
                message: message, // alert's message
                close: true, // make alert closable
                icon: 'fa fa-warning' // put icon class before the message
            });
            return false;
        } else {
            $('.custom-alerts').remove();
            return true;
        }
    };

    $scope.prepare2SetPhoto = function() {
        var uploadModalScope = $("#uploadPhoto").scope();
        uploadModalScope.init();
        uploadModalScope.$on("submit", function(event, data) {
            $scope.entity.photo = data;
        });
    };
    $scope.upload = function() {
        var files = $("input[type='file'][name='file']");
        if (files.length !== 1 || files[0].files.length !== 1) {
            alert("无法找到唯一的文件！");
            return;
        }
        if (files[0].files[0].size/(1024*1024) > 20) {
            alert("选择图片大小不能大于20兆(M)。");
            return;
        }
        var fd = new FormData();
        fd.append("file", files[0].files[0]);
        $http({
            method: "POST",
            url: "mvc/dispatch?controller=AttController&method=uploadAndCapture",
            data: fd,
            headers: {
                'Content-Type' : undefined
            },
            transformRequest : angular.identity
        }).success(function(success) {
            $scope.entity["fileList"].push({
                name: "",
                photo: success.data,
                attId: success.result
            });
        });
    }
}]);
