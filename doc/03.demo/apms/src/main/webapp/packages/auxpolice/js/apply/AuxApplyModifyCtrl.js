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
 * Description: 更新辅警控制器.<br>
 * Created by Jimmybly Lee on 2017/7/3.
 * @author Jimmybly Lee
 */
angular.module('WebApp').controller('AuxApplyModifyCtrl', ['$scope', "$ajaxCall", function ($scope, $ajaxCall) {

    /*
     * 开始 与 ZJGLUpdateCtrl 一致.
     */
    // // 政治面貌字典列表
    // $ajaxCall.getDictList($scope, "ZZMM", 'zzmmList');
    // // 省字典列表
    // $ajaxCall.getDictList($scope, "SHENG", 'shengList');
    // // 文化程度典列表
    // $ajaxCall.getDictList($scope, "WHCD", 'whcdList');
    // // 专业类别典列表
    // $ajaxCall.getDictList($scope, "ZYLB", 'zylbList');

    /**
     * 提交表单
     */
    $scope.submit = function () {
        if ($scope.validate()) {
            // $ajaxCall.post({
            //     data : {
            //         controller: "ZJInfoController",
            //         method: $scope.method,
            //         entity : JSON.stringify($scope.entity)
            //     },
            //     success: function() {
            //         $scope.$emit("submitted");
            //     }
            // });
            $(".modal").modal('hide');
        }
    };

    /**
     * 准备设置图片
     */
    $scope.prepare2SetPhoto = function () {
        var uploadModalScope = $("#uploadPhoto").scope();
        uploadModalScope.init();
        uploadModalScope.$on("submit", function (event, data) {
            $scope.entity.jb_zp = data;
        });
    };

    /**
     * 当专业类别被选中时，添加到专业类别列表中
     * @param item
     */
    $scope.onZYLBSelected = function (item) {
        if ($.inArray(item, $scope.entity.zylbList) < 0) {
            $scope.entity.zylbList.push({zylb: item});
        }
    };

    /**
     * 删除辅助信息
     * @param idx 序号
     * @param key entity中的名称
     * @param id 辅助信息的id
     * @param type 辅助信息的java类
     */
    $scope.remove = function (idx, key, id, type) {
        $scope.entity[key].splice(idx, 1);
        if (id) {
            $ajaxCall.post({
                data: {
                    controller: "ZJInfoController",
                    method: "removeFz",
                    id: id,
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
        var nullCheck = function(key, msg, parent) {
            var dom = $("input[ng-model='entity." + key + "'").parents(parent ? "." + parent : ".form-group");
            dom.removeClass("has-error");
            if ($scope.entity[key] === undefined || $scope.entity[key].length === 0) {
                message += msg + "不能为空;";
                dom.addClass("has-error");
            }
        };

        nullCheck("jb_dh", "电话", "input-icon");
        nullCheck("jb_sj", "手机", "input-icon");
        nullCheck("gz_gzdw", "工作单位", "input-icon");
        nullCheck("gz_gzdw_zw", "职务", "input-icon");
        nullCheck("gz_gzdw_dz_sheng", "单位地址", "input-icon");
        nullCheck("gz_gzdw_dz_shi", "单位地址", "input-icon");
        nullCheck("gz_gzdw_dz_jd", "单位地址", "input-icon");

        nullCheck("jb_xm", "姓名");
        nullCheck("jb_jl", "简历");
        nullCheck("jb_sfzh", "身份证号");
        nullCheck("jb_xb", "性别");
        nullCheck("jb_zzmm", "政治面貌");
        nullCheck("jy_whcd", "文化程度");
        nullCheck("jy_whcd_sszy", "所学专业");
        nullCheck("zy_jszc", "技术职称(可以写‘无’)");
        nullCheck("zy_zgrz", "资格认证(可以写‘无’)");
        nullCheck("zy_ywzc", "业务专长(可以写‘无’)");

        if ($scope.entity.gz_gaxt) {
            nullCheck("gz_gaxt_kssj", "参加系统时间");
        }
        if ($scope.entity.zylbList.length === 0) {
            message += "至少选择一个专业类别;";
        }

        if (message.length > 0) {
            App.alert({
                container: $("#updateZJErrorMsgDiv"),
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
    /*
     * 结束 与 ZJGLUpdateCtrl 一致.
     */
}]);
