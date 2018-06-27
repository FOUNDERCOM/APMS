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
 * Description: 系统文件上传下载控制器.<br>
 * Created by hekun on 2018/6/17.
 * @author hekun 
 */
angular.module('WebApp').controller('SysFileHelpCtrl', ['$scope', "$http", "$ajaxCall", function ($scope, $http, $ajaxCall) {
	$scope.fileList = [];
	$scope.load = function() {
		$ajaxCall.post({
	        data : {
	            controller: "AttController",
	            method: 'getFileList'
	        },
	        success: function(res) {
	        	if(res.result){
	        		$scope.fileList = res.result;
	        	}
	        }
	    });
	};
	$scope.load();
	
	$scope.deleteFile = function(item) {
		$ajaxCall.post({
	        data : {
	            controller: "AttController",
	            method: 'deleteFile',
	            path: item.fpath
	        },
	        success: function(res) {
        		alert("系统帮助文件删除成功。");
        		$scope.load();
	        }
	    });
	};
	
	$scope.upload = function() {
        var files = $("input[type='file'][name='file']");
        if (files.length !== 1 || files[0].files.length !== 1) {
            return;
        }
        if (files[0].files[0].size/(1024*1024) > 20) {
            alert("选择文件大小不能大于20兆(M)。");
            return;
        }
        var fd = new FormData();
        fd.append("file", files[0].files[0]);
        $http({
            method: "POST",
            url: "mvc/dispatch?controller=AttController&method=uploadFile",
            data: fd,
            headers: {
                'Content-Type' : undefined
            },
            transformRequest : angular.identity
        }).success(function(success) {
        	alert("系统帮助文件上传成功。");
        	$scope.load();
        });
    };
}]);
