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

package com.founder.bj.apms.analysis.service.impl;

import com.founder.bj.apms.analysis.service.AnalysisService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Description: .<br>
 * Created by Jimmybly Lee on 2017/10/10.
 *
 * @author Jimmybly Lee
 */
@SuppressWarnings("unchecked")
@Transactional(readOnly = true)
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AnalysisServiceImpl implements AnalysisService {

    // CSOFF: MemberName
    /**
     * Hibernate 数据库操作管理器.
     **/
    @PersistenceContext(unitName = "apms_mgmt")
    private EntityManager em;
    // CSON: MemberName

    @Override
    public Integer getAuxNum() {
        final String sql = "SELECT count(1) FROM APMS_AUX_INFO WHERE IS_ENABLED = '1'";
        return ((Number) em.createNativeQuery(sql).getSingleResult()).intValue();
    }

    @Override
    public Integer getDegreeNum() {
        final String sql = "SELECT count(1) FROM APMS_AUX_INFO WHERE IS_ENABLED = '1' AND AUX_EDUCATION_DEGREE > '4'";
        return ((Number) em.createNativeQuery(sql).getSingleResult()).intValue();
    }

    @Override
    public Integer getAvgSalary() {
        final String sql = "SELECT AVG(CASE WHEN AUX_SALARY_C_PAY IS NULL THEN 0 ELSE AUX_SALARY_C_PAY END) FROM APMS_AUX_INFO WHERE IS_ENABLED = '1'";
        return ((Number) em.createNativeQuery(sql).getSingleResult()).intValue();
    }

    @Override
    public Integer getGoodPern() {
        final String sql = "SELECT COUNT(1) FROM APMS_AUX_INFO WHERE IS_ENABLED = '1' AND AUX_POLITICAL_STATUS = '2'";
        return ((Number) em.createNativeQuery(sql).getSingleResult()).intValue();
    }

    @Override
    public List<Map<String, Map<String, Integer>>> getBureauEduStatistics() {
        String sql = "";
        sql += "SELECT                                               ";
        sql += "	(                                                 ";
        sql += "		SELECT                                        ";
        sql += "			BU.BUREAU_NAME                            ";
        sql += "		FROM                                          ";
        sql += "			APMS_DEPT_BUREAU BU                       ";
        sql += "		WHERE                                         ";
        sql += "			BU.BUREAU_ID = ST.BUREAU_ID               ";
        sql += "	) || '_' || ST.BUREAU_ID || '_BUREAU',           ";
        sql += "	(                                                 ";
        sql += "		SELECT                                        ";
        sql += "			DICT.DICT_VALUE                           ";
        sql += "		FROM                                          ";
        sql += "			APMS_SYS_DICT DICT                        ";
        sql += "		WHERE                                         ";
        sql += "			DICT.DICT_CODE = INF.AUX_EDUCATION_DEGREE ";
        sql += "		AND DICT.DICT_NATURE = 'EDUCATION_DEGREE'     ";
        sql += "	),                                                ";
        sql += "	COUNT (INF.AUX_ID)                                ";
        sql += "FROM                                                 ";
        sql += "	APMS_AUX_INFO INF,                                ";
        sql += "	APMS_DEPT_STATION ST                              ";
        sql += "WHERE                                                ";
        sql += "	ST.STATION_ID = INF.STATION_ID                    ";
        sql += "GROUP BY                                             ";
        sql += "	INF.AUX_EDUCATION_DEGREE,                         ";
        sql += "	ST.BUREAU_ID                                      ";
        sql += "ORDER BY                                             ";
        sql += "	ST.BUREAU_ID,                                      ";
        sql += "	INF.AUX_EDUCATION_DEGREE                           ";

        return getEduResult(em.createNativeQuery(sql).getResultList());
    }

    @Override
    public List<Map<String, Map<String, Integer>>> getStationEduStatistics(String bureauId) {
        String sql = "";
        sql += "SELECT                                             ";
        sql += "	(                                                ";
        sql += "		SELECT                                       ";
        sql += "			STATION.STATION_NAME                     ";
        sql += "		FROM                                         ";
        sql += "			APMS_DEPT_STATION STATION                ";
        sql += "		WHERE                                        ";
        sql += "			STATION.STATION_ID = ST.STATION_ID       ";
        sql += "	) || '_' || ST.STATION_ID || '_STATION',          ";
        sql += "	(                                                ";
        sql += "		SELECT                                       ";
        sql += "			DICT.DICT_VALUE                          ";
        sql += "		FROM                                         ";
        sql += "			APMS_SYS_DICT DICT                       ";
        sql += "		WHERE                                        ";
        sql += "			DICT.DICT_CODE = INF.AUX_EDUCATION_DEGREE";
        sql += "		AND DICT.DICT_NATURE = 'EDUCATION_DEGREE'    ";
        sql += "	),                                               ";
        sql += "	COUNT (INF.AUX_ID)                               ";
        sql += "FROM                                               ";
        sql += "	APMS_AUX_INFO INF,                               ";
        sql += "	APMS_DEPT_STATION ST                             ";
        sql += "WHERE                                              ";
        sql += "	ST.STATION_ID = INF.STATION_ID                   ";
        sql += "  AND ST.BUREAU_ID = :bureauId                       ";
        sql += "GROUP BY                                           ";
        sql += "	INF.AUX_EDUCATION_DEGREE,                        ";
        sql += "	ST.STATION_ID                                    ";
        sql += "ORDER BY                                           ";
        sql += "	ST.STATION_ID,                                   ";
        sql += "	INF.AUX_EDUCATION_DEGREE                         ";

        return getEduResult(em.createNativeQuery(sql).setParameter("bureauId", bureauId).getResultList());
    }

    /**
     * 转化 key value 组合结果集. 学位
     *
     * @param resultSet 目标结果集
     * @return 结果集
     */
    private List<Map<String, Map<String, Integer>>> getEduResult(final List<Object[]> resultSet) {
        final List<Map<String, Map<String, Integer>>> result = new LinkedList<>();
        for (Object[] data : resultSet) {
            boolean isFound = false;
            for (Map<String, Map<String, Integer>> dept : result) {
                if (dept.containsKey(data[0].toString())) {
                    isFound = true;
                    dept.get(data[0].toString()).put(data[1].toString(), Integer.parseInt(data[2].toString()));
                }
            }
            if (!isFound) {
                final Map<String, Map<String, Integer>> dept = new HashMap<>();
                dept.put(data[0].toString(), new HashMap<String, Integer>());
                dept.get(data[0].toString()).put(data[1].toString(), Integer.parseInt(data[2].toString()));
                result.add(dept);
            }
        }
        return result;
    }

    @Override
    public List<Map<String, Map<String, Integer>>> getBureauAgeStatistics() {
        String sql = "";
        sql += "SELECT                                                                                       ";
        sql += "	(                                                                                        ";
        sql += "		SELECT                                                                               ";
        sql += "			BU.BUREAU_NAME                                                                   ";
        sql += "		FROM                                                                                 ";
        sql += "			APMS_DEPT_BUREAU BU                                                              ";
        sql += "		WHERE                                                                                ";
        sql += "			BU.BUREAU_ID = ST.BUREAU_ID                                                      ";
        sql += "	) || '_' || ST.BUREAU_ID || '_BUREAU',                                                  ";
        sql += "SUM(CASE WHEN AGE BETWEEN 18 AND 25 THEN 1 ELSE 0 END),                                      ";
        sql += "SUM(CASE WHEN AGE BETWEEN 26 AND 30 THEN 1 ELSE 0 END),                                      ";
        sql += "SUM(CASE WHEN AGE BETWEEN 31 AND 35 THEN 1 ELSE 0 END),                                      ";
        sql += "SUM(CASE WHEN AGE BETWEEN 36 AND 40 THEN 1 ELSE 0 END),                                      ";
        sql += "SUM(CASE WHEN AGE BETWEEN 41 AND 45 THEN 1 ELSE 0 END),                                      ";
        sql += "SUM(CASE WHEN AGE BETWEEN 46 AND 100 THEN 1 ELSE 0 END)                                      ";
        sql += "FROM                                                                                         ";
        sql += "(SELECT I.*, (TO_CHAR(SYSDATE, 'YYYY') - SUBSTR(I.AUX_BIRTHDAY, 0, 4))  AGE FROM APMS_AUX_INFO I) INF,";
        sql += "	APMS_DEPT_STATION ST                                                                      ";
        sql += "WHERE                                                                                         ";
        sql += "	ST.STATION_ID = INF.STATION_ID                                                            ";
        sql += "GROUP BY                                                                                      ";
        sql += "	ST.BUREAU_ID                                                                              ";
        sql += "ORDER BY                                                                                      ";
        sql += "	ST.BUREAU_ID                                                                              ";
        return getAgeResult(em.createNativeQuery(sql).getResultList());
    }

    @Override
    public List<Map<String, Map<String, Integer>>> getStationAgeStatistics(String bureauId) {
        String sql = "";
        sql += "SELECT                                                                                       ";
        sql += "	(                                                                                          ";
        sql += "		SELECT                                                                                 ";
        sql += "			STATION.STATION_NAME                                                               ";
        sql += "		FROM                                                                                   ";
        sql += "			APMS_DEPT_STATION STATION                                                          ";
        sql += "		WHERE                                                                                  ";
        sql += "			STATION.STATION_ID = ST.STATION_ID                                                 ";
        sql += "	) || '_' || ST.STATION_ID || '_STATION',                                                    ";
        sql += "SUM(CASE WHEN AGE BETWEEN 18 AND 25 THEN 1 ELSE 0 END),                                      ";
        sql += "SUM(CASE WHEN AGE BETWEEN 26 AND 30 THEN 1 ELSE 0 END),                                      ";
        sql += "SUM(CASE WHEN AGE BETWEEN 31 AND 35 THEN 1 ELSE 0 END),                                      ";
        sql += "SUM(CASE WHEN AGE BETWEEN 36 AND 40 THEN 1 ELSE 0 END),                                      ";
        sql += "SUM(CASE WHEN AGE BETWEEN 41 AND 45 THEN 1 ELSE 0 END),                                      ";
        sql += "SUM(CASE WHEN AGE BETWEEN 46 AND 100 THEN 1 ELSE 0 END)                                      ";
        sql += "FROM                                                                                         ";
        sql += "(SELECT I.*, (TO_CHAR(SYSDATE, 'YYYY') - SUBSTR(I.AUX_BIRTHDAY, 0, 4))  AGE FROM APMS_AUX_INFO I) INF,";
        sql += "	APMS_DEPT_STATION ST                                                                      ";
        sql += "WHERE                                                                                         ";
        sql += "	ST.STATION_ID = INF.STATION_ID                                                            ";
        sql += "	AND ST.BUREAU_ID = :bureauId                                                            ";
        sql += "GROUP BY                                                                                      ";
        sql += "	ST.STATION_ID                                                                              ";
        sql += "ORDER BY                                                                                      ";
        sql += "	ST.STATION_ID                                                                              ";
        return getAgeResult(em.createNativeQuery(sql).setParameter("bureauId", bureauId).getResultList());
    }

    /**
     * 转化 key value 组合结果集. 生日
     *
     * @param resultSet 目标结果集
     * @return 结果集
     */
    private List<Map<String, Map<String, Integer>>> getAgeResult(final List<Object[]> resultSet) {
        final List<Map<String, Map<String, Integer>>> result = new LinkedList<>();
        final String[] fiels = {"18~25", "26~30", "31~35", "36~40", "41~45", "45+"};
        for (Object[] data : resultSet) {
            boolean isFound = false;
            for (Map<String, Map<String, Integer>> dept : result) {
                if (dept.containsKey(data[0].toString())) {
                    isFound = true;
                    for (int i = 0; i < fiels.length; i++) {
                        dept.get(data[0].toString()).put(fiels[i], Integer.parseInt(data[i + 1].toString()));
                    }
                }
            }
            if (!isFound) {
                final Map<String, Map<String, Integer>> dept = new HashMap<>();
                dept.put(data[0].toString(), new HashMap<String, Integer>());
                for (int i = 0; i < fiels.length; i++) {
                    dept.get(data[0].toString()).put(fiels[i], Integer.parseInt(data[i + 1].toString()));
                }
                result.add(dept);
            }
        }
        return result;

    }

    /* 数据完善程度统计。算法：只统计非必选项，然后求差。 */
    public List<Map<String, Double>> getBureauPerStatistics() {

        // 共22个录入项
        String sql = "";
        sql += "SELECT                                               ";
        sql += "	(                                                 ";
        sql += "		SELECT                                        ";
        sql += "			BU.BUREAU_NAME                            ";
        sql += "		FROM                                          ";
        sql += "			APMS_DEPT_BUREAU BU                       ";
        sql += "		WHERE                                         ";
        sql += "			BU.BUREAU_ID = ST.BUREAU_ID               ";
        sql += "	) || '_' || ST.BUREAU_ID || '_BUREAU',           ";
        sql += "	1 - ((SUM(CASE WHEN INF.AUX_TEL IS NULL THEN 1 ELSE 0 END) + SUM(CASE WHEN INF.AUX_MAIL IS NULL THEN 1 ELSE 0 END) + SUM(CASE WHEN INF.AUX_NATIVE_PLACE IS NULL THEN 1 ELSE 0 END)) / (SUM(1) * 22)) ";
        sql += "FROM                                                 ";
        sql += "	APMS_AUX_INFO INF,                                ";
        sql += "	APMS_DEPT_STATION ST                              ";
        sql += "WHERE                                                ";
        sql += "	ST.STATION_ID = INF.STATION_ID                    ";
        sql += "GROUP BY                                             ";
        sql += "	ST.BUREAU_ID                                      ";
        sql += "ORDER BY                                             ";
        sql += "	ST.BUREAU_ID                                      ";

        return getPerResult(em.createNativeQuery(sql).getResultList());
    }
    public List<Map<String, Double>> getStationPerStatistics(String bureauId) {
        String sql = "";
        sql += "SELECT                                             ";
        sql += "	(                                                ";
        sql += "		SELECT                                       ";
        sql += "			STATION.STATION_NAME                     ";
        sql += "		FROM                                         ";
        sql += "			APMS_DEPT_STATION STATION                ";
        sql += "		WHERE                                        ";
        sql += "			STATION.STATION_ID = ST.STATION_ID       ";
        sql += "	) || '_' || ST.STATION_ID || '_STATION',          ";
        sql += "	1 - ((SUM(CASE WHEN INF.AUX_TEL IS NULL THEN 1 ELSE 0 END) + SUM(CASE WHEN INF.AUX_MAIL IS NULL THEN 1 ELSE 0 END) + SUM(CASE WHEN INF.AUX_NATIVE_PLACE IS NULL THEN 1 ELSE 0 END)) / (SUM(1) * 22)) ";
        sql += "FROM                                               ";
        sql += "	APMS_AUX_INFO INF,                               ";
        sql += "	APMS_DEPT_STATION ST                             ";
        sql += "WHERE                                              ";
        sql += "	ST.STATION_ID = INF.STATION_ID                   ";
        sql += "  AND ST.BUREAU_ID = :bureauId                       ";
        sql += "GROUP BY                                           ";
        sql += "	ST.STATION_ID                                    ";
        sql += "ORDER BY                                           ";
        sql += "	ST.STATION_ID                                   ";

        return getPerResult(em.createNativeQuery(sql).setParameter("bureauId", bureauId).getResultList());
    }
    /**
     * 转化 key value 组合结果集. 学位
     *
     * @param resultSet 目标结果集
     * @return 结果集
     */
    private List<Map<String, Double>> getPerResult(final List<Object[]> resultSet) {
        final List<Map<String, Double>> result = new LinkedList<>();
        for (Object[] data : resultSet) {
            final Map<String, Double> dept = new HashMap<>();

            dept.put(data[0].toString(), ((Number) data[1]).doubleValue() * 100);
            result.add(dept);
        }
        return result;
    }
}
