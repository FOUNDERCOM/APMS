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
 * Created by Jimmybly Lee on 2017/10/31.
 * @author Jimmybly Lee
 */
angular.module('WebApp').controller('AuxModifyCtrl', ['$state', '$rootScope', '$scope', "$listService", "$ajaxCall", "$http", function ($state, $rootScope, $scope, $listService, $ajaxCall, $http) {

    var handleTitle = function(tab, navigation, index) {
        var total = navigation.find('li').length;
        var current = index + 1;
        // set wizard title
        $('.step-title', $('#form_wizard')).text('步骤 ' + (index + 1) + ' / ' + total);
        // set done steps
        $('li', $('#form_wizard')).removeClass("done");
        var li_list = navigation.find('li');
        for (var i = 0; i < index; i++) {
            $(li_list[i]).addClass("done");
        }

        if (current === 1) {
            $('#form_wizard').find('.button-previous').hide();
            $('#form_wizard').find('.button-first').hide();
        } else {
            $('#form_wizard').find('.button-previous').show();
            $('#form_wizard').find('.button-first').show();
        }

        if (current >= total) {
            $('#form_wizard').find('.button-next').hide();
            $('#form_wizard').find('.button-submit').show();
        } else {
            $('#form_wizard').find('.button-next').show();
            $('#form_wizard').find('.button-submit').hide();
        }
        App.scrollTo($('.page-title'));
    };
    // default form wizard
    $('#form_wizard').bootstrapWizard({
        'nextSelector': '.button-next',
        'previousSelector': '.button-previous',
        'firstSelector': '.button-first',
        onTabClick: function (tab, navigation, index, clickedIndex) {
            return false;
        },
        onNext: function (tab, navigation, index) {
            if ($scope.isFail(index)) {
                return false;
            }
            handleTitle(tab, navigation, index);
        },
        onPrevious: function (tab, navigation, index) {
            handleTitle(tab, navigation, index);
        },
        onFirst: function(tab, navigation, index) {
            handleTitle(tab, navigation, index);
        },
        onTabShow: function (tab, navigation, index) {
            var total = navigation.find('li').length;
            var current = index + 1;
            var $percent = (current / total) * 100;
            $('#form_wizard').find('.progress-bar').css({
                width: $percent + '%'
            });
        }
    });

    $('#form_wizard').find('.button-previous').hide();
    $('#form_wizard').find('.button-first').hide();
    $('#form_wizard .button-submit').click(function () {
        $scope.submit();
    }).hide();

    $scope.isFail = function(idx) {
        var message = "您录入的信息有误：<br>";
        var check = function(key, msg, isWithId) {
            var dom = $("*[ng-model='entity." + key + (isWithId ? ".id']" : "']")).parents(".form-group");
            dom.removeClass("has-error");
            if ($scope.entity[key] === undefined
                || (!isWithId && $scope.entity[key].length === 0)
                || (isWithId && ($scope.entity[key]["id"] === undefined || $scope.entity[key]["id"].length === 0))) {
                message += msg + "不能为空!<br>";
                dom.addClass("has-error");
            }
        };
        if (idx === 1) {
            // base

            // 分局
            // 科所队
            check("station", "科所队", true);
            // 电话
            // 手机
            check("mobile", "手机");
            // 邮箱
            // 职务
            check("job", "手机");
            // 现住址 省
            check("addProvince", "现住址 省");
            // 现住址 市
            check("addCity", "现住址 市");
            // 现住址 区县
            check("addCountry", "现住址 区县");
            // 邮编
            check("addPostcode", "邮编");
            // 现住址 详细地址
            check("addDetail", "现住址 详细地址");
        } else if (idx === 2) {
            // detail

            // 姓名
            check("name", "姓名");
            // 身份证号
            check("identityCard", "身份证号");
            if ($scope.entity.identityCard && $scope.entity.identityCard.length > 0 && !IDCardValidator.validate($scope.entity.identityCard)) {
                var identityCardInputGroup = $("*[ng-model='entity." + "identityCard" +  "']").parents('.form-group');
                message += "身份证号格式不正确";
                identityCardInputGroup.addClass("has-error");
            }
            // 入职时间
            check("joinDate", "入职时间");
            // 入职前身份
            check("oldIdentity", "入职前身份", true);
            // 民族
            check("nation", "民族", true);
            // 健康状况
            check("health", "健康状况", true);
            // 政治面貌
            check("politicalStatus", "政治面貌", true);
            // 文化程度
            check("eduDegree", "文化程度", true);
            // 籍贯
            // 毕业院校
            check("institutions", "毕业院校");
            // 所学专业
        } else if (idx === 3) {
            // file
            if ($scope.entity.fileList.length === 0) {
//                message += "请至少上传一个证明文件。";
            } else {
                $.each($scope.entity.fileList, function(key, data) {
                    if (data.name === undefined || data.name.length === 0) {
                        message += "<br>请为文件命名。";
                    }
                });
            }
        } else if (idx === 4) {
            // work
            $.each($scope.entity.workList, function(key, data) {
                if (data.start === undefined || data.start.length === 0) {
                    message += "开始时间不能为空。<br>";
                }
                if (data.dept === undefined || data.dept.length === 0) {
                    message += "工作单位不能为空。<br>";
                }
            });
        } else if (idx === 5) {
            // 家庭情况
            $.each($scope.entity.familyList, function(key, data) {
                if (data.rel === undefined || data.rel.length === 0) {
                    message += "家庭关系不能为空。<br>";
                }
                if (data.name === undefined || data.name.length === 0) {
                    message += "姓名不能为空。<br>";
                }
                if (data.mobile === undefined || data.mobile.length === 0) {
                    message += "手机号不能为空。<br>";
                }
                if (data.identityCard === undefined || data.identityCard.length === 0) {
                    message += "身份证号不能为空。<br>";
                }
            });
            // 年度考核
            $.each($scope.entity.appraiseList, function(key, data) {
                if (data.year === undefined || data.year.length === 0) {
                    message += "年度不能为空。<br>";
                }
                if (data.level === undefined || data.level.id === undefined) {
                    message += "评级不能为空。<br>";
                }
            });
            // 奖励情况
            $.each($scope.entity.awardList, function(key, data) {
                if (data.title === undefined || data.title.length === 0) {
                    message += "奖励名称不能为空。<br>";
                }
                if (data.dept === undefined || data.dept.length === 0) {
                    message += "奖励单位不能为空。<br>";
                }
                if (data.date === undefined || data.date.length === undefined) {
                    message += "奖励时间不能为空。<br>";
                }
            });
            // 惩罚情况
            $.each($scope.entity.punishList, function(key, data) {
                if (data.title === undefined || data.title.length === 0) {
                    message += "惩罚名称不能为空。<br>";
                }
                if (data.dept === undefined || data.dept.length === 0) {
                    message += "惩罚单位不能为空。<br>";
                }
                if (data.date === undefined || data.date.length === undefined) {
                    message += "惩罚时间不能为空。<br>";
                }
                if (data.desc === undefined || data.desc.length === undefined) {
                    message += "惩罚说明不能为空。<br>";
                }
            });
            // 确认页
            $.each($scope.entity.eduList, function(foo, edu) {
                $.each($scope.eduDegreeList, function(key, dict) {
                    if (edu.degree.id === dict.id) {
                        edu.degree.value = dict.value;
                        edu.degree.nature = dict.nature;
                        edu.degree.code = dict.code;
                    }
                });
            });
        }

        if (message !== "您录入的信息有误：<br>") {
            App.alert({
                container: $("#modifyErrorMsgDiv"),
                place: 'append', // append or prepent in container
                type: 'warning', // alert's type
                message: message, // alert's message
                close: true, // make alert closable
                icon: 'fa fa-warning' // put icon class before the message
            });
            return true;
        } else {
            $('.custom-alerts').remove();
            return false;
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
    };

    /**
     * 提交表单
     */
    $scope.submit = function() {
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
                    method: ($scope.method === "create" && $state.current.name !== 'aux_apply')?"createPass":$scope.method,
                    entity : JSON.stringify($scope.entity)
                },
                success: function() {
                	alert("注册辅警信息成功！");
                    $scope.$emit("submitted");
                    $(".modal").modal('hide');
                }
            });
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
    };
    $scope.foo = function(v) {
        console.log(v);
    }
}]);
