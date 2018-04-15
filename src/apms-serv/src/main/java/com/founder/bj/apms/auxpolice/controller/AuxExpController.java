/* ***************************************************************************
 * Copyright (C) 2018-2019 the original author Jimmybly Lee
 * or authors of NAPTUNE.COM
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

package com.founder.bj.apms.auxpolice.controller;

import com.founder.bj.apms.auxpolice.entity.AuxInfo;
import com.founder.bj.apms.auxpolice.service.AuxInfoService;
import com.founder.bj.apms.dept.entity.DeptBureau;
import com.founder.bj.apms.dept.entity.DeptStation;
import com.founder.bj.apms.sys.entity.SysDict;
import com.lee.jwaf.action.AbstractControllerSupport;
import com.lee.util.BeanUtils;
import com.lee.util.ObjectUtils;
import com.lee.util.ReflectionUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Description: TODO.<br>
 * Created by jimmy on 2018/4/2.
 *
 * @author Jimmybly Lee
 */
@SuppressWarnings("unused")
@Controller("AuxExpController")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AuxExpController extends AbstractControllerSupport {
    /**
     * Info Service.
     */
    @Resource
    private AuxInfoService infoService;

    private String[] fieldNames = {"分局", "派出所", "姓名", "身份证", "性别", "生日", "年龄", "祖籍", "民族", "政治面貌", "健康状况", "电话", "手机", "邮件", "学位", "毕业院校", "专业", "入职前身份", "职位", "职级", "入职时间", "工龄", "省", "市", "县", "详址", "邮编"};
    private String[] fields = {"name", "identityCard", "sex", "birthday", "age", "nativePlace", "nation", "politicalStatus", "health", "tel", "mobile", "mail", "eduDegree", "institutions", "major", "oldIdentity", "job", "jobLevel", "joinDate", "standing", "addProvince", "addCity", "addCountry", "addDetail", "addPostcode"};

    public void exportAllAuxInfo() {
        final AuxInfo condition = new AuxInfo();
        condition.setIsEnabled(true);
        final List<AuxInfo> auxs = infoService.queryAll(condition);
        log.debug("size: {}", auxs.size());
        exportAuxInfo(auxs);
    }

    public void exportBureauAuxInfo() {
        final AuxInfo condition = new AuxInfo();
        condition.setIsEnabled(true);
        condition.setStation(new DeptStation());
        condition.getStation().setBureau(new DeptBureau());
        condition.getStation().getBureau().setId(workDTO.<String>get("bureauId"));
        final List<AuxInfo> auxs = infoService.queryAll(condition);
        exportAuxInfo(auxs);
    }

    public void exportStationAuxInfo() {
        final AuxInfo condition = new AuxInfo();
        condition.setIsEnabled(true);
        condition.setStation(new DeptStation());
        condition.getStation().setId(workDTO.<String>get("stationId"));
        final List<AuxInfo> auxs = infoService.queryAll(condition);
        exportAuxInfo(auxs);
    }

    private void exportAuxInfo(List<AuxInfo> auxs) {
        HSSFWorkbook workbook = null;
        try {
            workbook = new HSSFWorkbook();
            final HSSFSheet sheet = workbook.createSheet("花名册");
            sheet.setRowSumsBelow(false);

            // 写表头
            // title, start, end
            String[][] styleDef = {
                {"工作单位", "0", "1"},
                {"基本信息", "2", "10"},
                {"联系方式", "11", "13"},
                {"教育信息", "14", "16"},
                {"工作信息", "17", "21"},
                {"居住信息", "22", "26"}
            };
            int count = 0;
            HSSFRow row = sheet.createRow(count++);
            HSSFCell cell;
            HSSFCellStyle style = workbook.createCellStyle();
            HSSFFont font = workbook.createFont();
            font.setFontHeightInPoints((short) 12);
            font.setBold(true);
            style.setFont(font);
            style.setAlignment(HorizontalAlignment.CENTER);
            for (String[] def : styleDef) {
                cell = row.createCell(Integer.parseInt(def[1]));
                cell.setCellStyle(style);
                cell.setCellValue(def[0]);

                sheet.addMergedRegion(new CellRangeAddress(0, 0, Integer.parseInt(def[1]), Integer.parseInt(def[2])));
            }

            row = sheet.createRow(count++);
            for (int i = 0; i < fieldNames.length; i++) {
                cell = row.createCell(i);
                cell.setCellStyle(style);
                cell.setCellValue(fieldNames[i]);
            }
            // 写内容
            for (AuxInfo aux : auxs) {
                row = sheet.createRow(count++);
                row.createCell(0).setCellValue(aux.getStation().getBureau().getName());
                row.createCell(1).setCellValue(aux.getStation().getName());
                for (int i = 0; i < fields.length; i++) {
                    String value = "";
                    if (SysDict.class.equals(BeanUtils.getPropertyDescriptor(aux.getClass(), fields[i]).getPropertyType())) {
                        ReflectionUtils.makeAccessible(ReflectionUtils.findField(aux.getClass(), fields[i]));
                        value = ((SysDict) ReflectionUtils.getField(ReflectionUtils.findField(aux.getClass(), fields[i]), aux)).getValue();
                    } else {
                        ReflectionUtils.makeAccessible(ReflectionUtils.findField(aux.getClass(), fields[i]));
                        value = ReflectionUtils.getField(ReflectionUtils.findField(aux.getClass(), fields[i]), aux) + "";
                    }
                    row.createCell(i + 2).setCellValue(value);
                }
            }
            super.servletResponse.setContentType("application/x-excel");
            super.servletResponse.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("花名册.xls", "UTF-8"));
            super.servletResponse.setCharacterEncoding("ISO8859-1");
            workbook.write(servletResponse.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                servletResponse.getOutputStream().flush();
                servletResponse.getOutputStream().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
