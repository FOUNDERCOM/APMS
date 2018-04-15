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

package com.founder.bj.apms.analysis.service;

import java.util.List;
import java.util.Map;

/**
 * Description: TODO.<br>
 * Created by Jimmybly Lee on 2017/10/10.
 *
 * @author Jimmybly Lee
 */
public interface AnalysisService {
    Integer getAuxNum();
    Integer getDegreeNum();
    Integer getAvgSalary();
    Integer getGoodPern();
    List<Map<String, Map<String, Integer>>> getBureauEduStatistics();
    List<Map<String, Map<String, Integer>>> getStationEduStatistics(String bureauId);

    List<Map<String, Map<String, Integer>>> getBureauAgeStatistics();
    List<Map<String, Map<String, Integer>>> getStationAgeStatistics(String bureauId);

    /* 数据完善程度统计。算法：只统计非必选项，然后求差。 */
    List<Map<String, Double>> getBureauPerStatistics();
    List<Map<String, Double>> getStationPerStatistics(String bureauId);
}
