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

package com.founder.bj.apms.analysis.controller;

import com.founder.bj.apms.analysis.service.AnalysisService;
import com.lee.jwaf.action.AbstractControllerSupport;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Description: TODO.<br>
 * Created by Jimmybly Lee on 2017/10/10.
 *
 * @author Jimmybly Lee
 */
@SuppressWarnings("unused")
@Controller("AnalysisController")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AnalysisController extends AbstractControllerSupport {

    @Resource
    private AnalysisService service;

    public void getHomeBaseInfo() {
        workDTO.put("auxNum", service.getAuxNum());
        workDTO.put("avgSalary", service.getAvgSalary());
        workDTO.put("degreeNum", service.getDegreeNum());
        workDTO.put("goodPern", service.getGoodPern());
    }

    /**
     * 分局级别统计教育.
     */
    public void getBureauEduStatistics() {
        workDTO.setResult(service.getBureauEduStatistics());
    }

    /**
     * 分局级别统计教育.
     */
    public void getStationEduStatistics() {
        workDTO.setResult(service.getStationEduStatistics(workDTO.<String>get("bureauId")));
    }

    /**
     * 分局级别统计年龄段.
     */
    public void getBureauAgeStatistics() {
        workDTO.setResult(service.getBureauAgeStatistics());
    }

    /**
     * 分局级别统计年龄段.
     */
    public void getStationAgeStatistics() {
        workDTO.setResult(service.getStationAgeStatistics(workDTO.<String>get("bureauId")));
    }

    /**
     * 分局级别统计数据完善情况.
     */
    public void getBureauPerStatistics() {
        workDTO.setResult(service.getBureauPerStatistics());
    }

    /**
     * 分局级别统计数据完善情况.
     */
    public void getStationPerStatistics() {
        workDTO.setResult(service.getStationPerStatistics(workDTO.<String>get("bureauId")));
    }
}
