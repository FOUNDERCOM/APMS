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
 * Description: 辅警管理列表控制器.<br>
 * Created by Jimmybly Lee on 2017/9/17.
 * @author Jimmybly Lee
 */
angular.module('WebApp').controller('AuxMgmtListCtrl', ['$rootScope', '$scope', "$listService", "$ajaxCall", function ($rootScope, $scope, $listService, $ajaxCall) {

    // 流程相关颜色和消息配置
    $.getJSON("packages/auxpolice/js/com/config.json", function(data) {
        $scope.cfg = data;
    });
    // 入职前身份字典列表
    $ajaxCall.getDictList($scope, "OLD_IDENTITY", 'oldIdentityList');
    // 性别字典列表
    $ajaxCall.getDictList($scope, "SEX", 'sexList');
    // 民族字典列表
    $ajaxCall.getDictList($scope, "NATION", 'nationList');
    // 健康状况字典列表
    $ajaxCall.getDictList($scope, "HEALTH", 'healthList');
    // 健康状况字典列表
    $ajaxCall.getDictList($scope, "POLITICAL_STATUS", 'politicalStatusList');
    // 学位字典列表
    $ajaxCall.getDictList($scope, "EDUCATION_DEGREE", 'eduDegreeList');
    // 评价等级字典列表
    $ajaxCall.getDictList($scope, "APPRAISE_LEVEL", 'appraiseLevelList');

    // 分局列表
    $ajaxCall.post({
        data : {
            controller: "DeptBureauController",
            method: 'query',
            condition : JSON.stringify({isEnabled: true}),
            start: 0,
            limit: -1
        },
        success: function(res) {
            $scope.bureauList = res['result'];
        }
    });

    // 科所队列表
    $ajaxCall.post({
        data : {
            controller: "DeptStationController",
            method: 'query',
            condition : JSON.stringify({isEnabled: true}),
            start: 0,
            limit: -1
        },
        success: function(res) {
            $scope.stationList = res['result'];
        }
    });

    if($rootScope.token['user']['org']['bureau']['id'] == $rootScope.token['user']['org']['id']){
		$scope.condition = {isEnabled: "1"};
		$scope.fjdw = true;
		$scope.pcsdw = true;
	}else if($rootScope.token['user']['org']['isManage']){
		$scope.condition = {isEnabled: "1", station: {bureau: {id: $rootScope.token['user']['org']['bureau']['id']}}};
		$scope.fjdw = false;
		$scope.pcsdw = true;
	}else{
		$scope.condition = {isEnabled: "1", station: {id: $rootScope.token['user']['org']['id']}};
		$scope.fjdw = false;
		$scope.pcsdw = false;
	}
    $listService.init($scope, {
        "controller": "AuxController",
        "method": "query",
        callback: function (success) {
            $scope.list = success.data.result;
        },
        pageSizeList: [12, 18, 24, 48],
        pageSize: 12
    });

    /**
     * 刷新数据
     */
    $scope.load = function () {
    	var station = $scope.condition.station;
    	if(station == null){
    		if($rootScope.token['user']['org']['bureau']['id'] == $rootScope.token['user']['org']['id']){
    		}else if($rootScope.token['user']['org']['isManage']){
    			$scope.condition.station = {bureau: {id: $rootScope.token['user']['org']['bureau']['id']}};
    		}else{
    			$scope.condition.station = {id: $rootScope.token['user']['org']['id']};
    		}
    	}
        $scope.pageRequest.getResponse();
    };
    $scope.load();
    
    /**
     * 导出花名册
     */
    $scope.downloadExl = function () {
    	var station = $scope.condition.station;
    	var href = "mvc/dispatch?controller=AuxExpController&method=exportBureauAuxInfo&isEnabled="+$scope.condition.isEnabled;
    	if($scope.condition.name != null){
    		href += "&name="+$scope.condition.name;
    	}
    	if(station != null && station.id != null){
    		href += "&stationId="+station.id;
    	}
    	document.location.href = href;
    };
    
    /**
     * 导出考核模板
     */
    $scope.downAppraise = function () {
    	var station = $scope.condition.station;
    	var href = "mvc/dispatch?controller=AuxExpController&method=exportAppraise&isEnabled="+$scope.condition.isEnabled;
    	if($scope.condition.name != null){
    		href += "&name="+$scope.condition.name;
    	}
    	if(station != null && station.id != null){
    		href += "&stationId="+station.id;
    	}
    	if($scope.condition.year != null && $scope.condition.year != null){
    		href += "&year="+$scope.condition.year;
    	}else{
    		href += "&year="+new Date().getFullYear();
    	}
    	document.location.href = href;
    };
    
    /**
     * 导出考核统计
     */
    $scope.downAppraiseStat = function () {
    	var href = "mvc/dispatch?controller=AuxExpController&method=exportAppraiseStat";
    	var bool = $rootScope["token"]['user']['org']['bureau']['id'] === '-100' ? 1 : 0;
		href += "&bool="+bool+"&bureauId="+$rootScope["token"]['user']['org']['bureau']['id'];
    	if($scope.condition.year != null && $scope.condition.year != null){
    		href += "&year="+$scope.condition.year;
    	}else{
    		href += "&year="+new Date().getFullYear();
    	}
    	document.location.href = href;
    };
    
    /**
     * 导出工资报表
     */
    $scope.downloadGzExl = function () {
    	var station = $scope.condition.station, href = "mvc/dispatch?controller=AuxController&method=exportSalary";
    	if($scope.condition.salarySGet !== undefined && $scope.condition.salarySGet !== null && $scope.condition.salarySGet !== ""){
    		href += "&salarySGet="+$scope.condition.salarySGet;
    	}
    	if(station == null || station.id == null){
    		try{
    			var bureau = $scope.condition.station.bureau;
    			href += "&bureauId="+bureau.id;
    		}catch(e){
    		}
    	}else{
    		href += "&stationId="+station.id;
    	}
    	document.location.href = href;
    };

    /**
     * 修改给定实体的状态
     * @param item 给定实体
     * @param isEnabled 新状态
     */
    $scope.changeStatus = function (item, isEnabled) {
    	if(isEnabled){
	        bootbox.dialog({
	            title: "请确认",
	            message: isEnabled ? "是否确认恢复该辅警？" : "是否确认注销该辅警？",
	            buttons: {
	                main: {label: " 取 消 ", className: "dark icon-ban btn-outline"},
	                danger: {
	                    label: isEnabled ? " 确  定 ！ " : " 注 销 ！",
	                    className: isEnabled ? "fa fa-recycle green" : "fa fa-ban red",
	                    callback: function () {
	                        $ajaxCall.post({
	                            data: {
	                                controller: "AuxController",
	                                method: isEnabled ? "resume" : "remove",
	                                id: item.id
	                            },
	                            success: function () {
	                            	alert(isEnabled ? "恢复辅警数据成功！" : "注销辅警数据成功！");
	                                $scope.load();
	                            }
	                        });
	                    }
	                }
	            }
	        });
	    }else{
			var scope = $("#removeAuxDiv").scope();
	        scope.title = "注销辅警信息";
	        scope.entity = item;
	
	        scope.$on("submitted", function () {
	            $scope.load();
	        });
		}
    };

    /**
     * 准备添加实体
     */
    $scope.prepareToAdd = function () {
        var divId = "modifyAuxploiceMgmtModalDiv";
        var scope = $("#" + divId).scope();
        scope.title = "注册辅警信息";
        scope.method = "create";
        scope.entity = {
            "isEnabled": true,
            "name": "",
            "tel": "",
            "mobile": "",
            "job": "",
            "identityCard": "",
            "joinDate": "",
            "workDate": "",
            "organizeDate": "",
            "employUnit": "",
            "standing": 0,
            "oldIdentity": {},
            "sex": {},
            "nation": {},
            "health": {},
            "politicalStatus": {},
            "educationDegree": {},
            "institutions": "",
            "major": "",
            "birthday": "",
            "nativePlace": "",
            "addProvince": "贵州省",
            "addCity": "毕节市",
            "addRyzt": "在职",
            "addCountry": "",
            "addDetail": "",
            "postCode": "",
            "station": {
                "id": $rootScope.token['user']['org']["id"],
                "name": $rootScope.token['user']['org']["name"],
                "bureau": {
                    "id": $rootScope.token['user']['org']['bureau']['id'],
                    "name": $rootScope.token['user']['org']['bureau']['name']
                }
            },
            "photo": $rootScope.cfg['defaultPhoto'],
            "awardList": [],
            "eduList": [],
            "familyList": [],
            "punishList": [],
            "workList": [],
            "fileList": [],
            "casList": [],
            "appraiseList": []
        };

        scope.$on("submitted", function () {
            $scope.load();
        });
        $('#form_wizard').find('.button-first').click();
    };

    /**
     * 准备修改实体
     */
    $scope.prepareToModify = function (item) {
    	item.appraiseList.sort(function(a, b){
            return parseInt(a.num)-parseInt(b.num);  
        });
        var divId = "modifyAuxploiceMgmtModalDiv";
        var scope = $("#" + divId).scope();
        scope.title = "修改辅警信息";
        scope.method = "update";
        scope.entity = item;

        scope.$on("submitted", function () {
            $scope.load();
        });
        $('#form_wizard').find('.button-first').click();
    };

    /**
     * 准备查看实体
     */
    $scope.prepareToView = function (item) {
    	item.appraiseList.sort(function(a, b){
            return parseInt(a.num)-parseInt(b.num);  
        });
        var divId = "viewAuxploiceMgmtModalDiv";
        var scope = $("#" + divId).scope();
        scope.title = "查看辅警信息";
        scope.method = "update";
        scope.entity = item;

        scope.$on("submitted", function () {
            $scope.load();
        });
    };
}]);
