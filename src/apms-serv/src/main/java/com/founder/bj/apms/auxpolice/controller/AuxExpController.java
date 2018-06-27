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

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.founder.bj.apms.auxpolice.entity.AuxAppraise;
import com.founder.bj.apms.auxpolice.entity.AuxInfo;
import com.founder.bj.apms.auxpolice.service.AuxInfoService;
import com.founder.bj.apms.dept.entity.DeptBureau;
import com.founder.bj.apms.dept.entity.DeptStation;
import com.founder.bj.apms.sys.dto.StationDTO;
import com.founder.bj.apms.sys.entity.SysDict;
import com.lee.jwaf.action.AbstractControllerSupport;
import com.lee.util.BeanUtils;
import com.lee.util.ReflectionUtils;

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

    private String[] fieldNames = {"工作单位名称", "部门名称", "姓名", "身份证", "性别", "生日", "年龄", "籍贯", "民族", "政治面貌", "健康状况", "电话", "手机", "邮件", "学位", "毕业院校", "专业", "入职前身份", "职务", "职级", "任现职时间", "工龄", "省", "市", "县", "详细地址", "邮编"};
    private String[] fields = {"name", "identityCard", "sex", "birthday", "age", "nativePlace", "nation", "politicalStatus", "health", "tel", "mobile", "mail", "eduDegree", "institutions", "major", "oldIdentity", "job", "jobLevel", "joinDate", "standing", "addProvince", "addCity", "addCountry", "addDetail", "addPostcode"};

    public void exportAllAuxInfo() {
        final AuxInfo condition = new AuxInfo();
        condition.setIsEnabled(workDTO.<String>get("isEnabled"));
        condition.setName(workDTO.<String>get("name"));
        condition.setStation(new DeptStation());
        condition.getStation().setBureau(new DeptBureau());
        String stationId = workDTO.<String>get("stationId");
        String bureauId = workDTO.<String>get("bureauId");
        if(!"".equals(stationId) && stationId != null){
        	condition.getStation().setId(stationId);
        }else if(!"".equals(bureauId) && bureauId != null){
        	condition.getStation().getBureau().setId(bureauId);
        }
        final List<AuxInfo> auxs = infoService.queryAll(condition);
        log.debug("size: {}", auxs.size());
        exportAuxInfo(auxs);
    }

    public void exportBureauAuxInfo() {
    	StationDTO unit = (StationDTO) sessionDTO.currentToken().user().getOrg();
    	final AuxInfo condition = new AuxInfo();
        condition.setIsEnabled(workDTO.<String>get("isEnabled"));
        condition.setName(workDTO.<String>get("name"));
        condition.setStation(new DeptStation());
        condition.getStation().setBureau(new DeptBureau());
        String stationId = workDTO.<String>get("stationId");
        if(!"".equals(stationId) && stationId != null){
        	condition.getStation().setId(stationId);
        }else if(unit.getIsManage()){
        	condition.getStation().getBureau().setId(((StationDTO) sessionDTO.currentToken().user().getOrg()).getBureau().getId());
        }else if(!unit.getId().equals(unit.getBureau().getId())){
        	condition.getStation().setId(((StationDTO) sessionDTO.currentToken().user().getOrg()).getId());
        }
        final List<AuxInfo> auxs = infoService.queryAll(condition);
        exportAuxInfo(auxs);
    }
    
    /**
     * 考核导出
     */
    public void exportAppraise() {
    	StationDTO unit = (StationDTO) sessionDTO.currentToken().user().getOrg();
    	final AuxInfo condition = new AuxInfo();
        condition.setIsEnabled(workDTO.<String>get("isEnabled"));
        condition.setName(workDTO.<String>get("name"));
        condition.setStation(new DeptStation());
        condition.getStation().setBureau(new DeptBureau());
        String stationId = workDTO.<String>get("stationId");
        if(!"".equals(stationId) && stationId != null){
        	condition.getStation().setId(stationId);
        }else if(unit.getIsManage()){
        	condition.getStation().getBureau().setId(((StationDTO) sessionDTO.currentToken().user().getOrg()).getBureau().getId());
        }else if(!unit.getId().equals(unit.getBureau().getId())){
        	condition.getStation().setId(((StationDTO) sessionDTO.currentToken().user().getOrg()).getId());
        }
        final List<AuxInfo> auxs = infoService.queryAll(condition);
        exportAppraiseExl(auxs, workDTO.<String>get("year"));
    }
    
    /**
     * 考核统计导出
     */
    public void exportAppraiseStat() {
        String bool = workDTO.<String>get("bool");
        String bureauId = workDTO.<String>get("bureauId");
        String year = workDTO.<String>get("year");
        final List<String> stat = infoService.getAppraiseStat(bool, bureauId, year);
        exportAppraiseStatExl(stat, year);
    }

    public void exportStationAuxInfo() {
        final AuxInfo condition = new AuxInfo();
        condition.setIsEnabled(workDTO.<String>get("isEnabled"));
        condition.setName(workDTO.<String>get("name"));
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
    
    private void exportAppraiseExl(List<AuxInfo> auxs, String year) {
        HSSFWorkbook workbook = null;
        try {
            workbook = new HSSFWorkbook();
            final HSSFSheet sheet = workbook.createSheet("考核列表");
            sheet.setRowSumsBelow(false);
            sheet.setDefaultColumnWidth(10);
            // 写表头
            String[][] styleDef = {
                {"工作单位", "0", "1"},
                {year+"年度考核", "2", "3"},
                {year+"年季度考核", "4", "7"},
                {year+"年月度考核", "8", "19"}
            };
            String[] fieldList = {"工作单位名称", "部门名称", "姓名", year+"年度", "一季度", "二季度", "三季度", "四季度", 
            		"1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"};

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
            for (int i = 0; i < fieldList.length; i++) {
                cell = row.createCell(i);
                cell.setCellStyle(style);
                cell.setCellValue(fieldList[i]);
            }
            // 写内容
            for (AuxInfo aux : auxs) {
                row = sheet.createRow(count++);
                row.createCell(0).setCellValue(aux.getStation().getBureau().getName());
                row.createCell(1).setCellValue(aux.getStation().getName());
                row.createCell(2).setCellValue(aux.getName());
                Set<AuxAppraise> list = aux.getAppraiseList();
                String years = null, quarter1 = null, quarter2 = null, quarter3 = null, quarter4 = null;
                String mon1 = null, mon2 = null, mon3 = null, mon4 = null, mon5 = null, mon6 = null, mon7 = null,
                		 mon8 = null, mon9 = null, mon10 = null, mon11 = null, mon12 = null;
                for (AuxAppraise auxAppraise : list) {
					if(year.equals(auxAppraise.getYear())){
						if("1".equals(auxAppraise.getType())){
							years = auxAppraise.getLevel().getValue();//年度
						}else if("2".equals(auxAppraise.getType()) && "1".equals(auxAppraise.getNum())){
							quarter1 = auxAppraise.getLevel().getValue();//一季度
						}else if("2".equals(auxAppraise.getType()) && "2".equals(auxAppraise.getNum())){
							quarter2 = auxAppraise.getLevel().getValue();//二季度
						}else if("2".equals(auxAppraise.getType()) && "3".equals(auxAppraise.getNum())){
							quarter3 = auxAppraise.getLevel().getValue();//三季度
						}else if("2".equals(auxAppraise.getType()) && "4".equals(auxAppraise.getNum())){
							quarter4 = auxAppraise.getLevel().getValue();//四季度
						}else if("3".equals(auxAppraise.getType()) && "1".equals(auxAppraise.getNum())){
							mon1 = auxAppraise.getLevel().getValue();//1月份
						}else if("3".equals(auxAppraise.getType()) && "2".equals(auxAppraise.getNum())){
							mon2 = auxAppraise.getLevel().getValue();
						}else if("3".equals(auxAppraise.getType()) && "3".equals(auxAppraise.getNum())){
							mon3 = auxAppraise.getLevel().getValue();
						}else if("3".equals(auxAppraise.getType()) && "4".equals(auxAppraise.getNum())){
							mon4 = auxAppraise.getLevel().getValue();
						}else if("3".equals(auxAppraise.getType()) && "5".equals(auxAppraise.getNum())){
							mon5 = auxAppraise.getLevel().getValue();
						}else if("3".equals(auxAppraise.getType()) && "6".equals(auxAppraise.getNum())){
							mon6 = auxAppraise.getLevel().getValue();
						}else if("3".equals(auxAppraise.getType()) && "7".equals(auxAppraise.getNum())){
							mon7 = auxAppraise.getLevel().getValue();
						}else if("3".equals(auxAppraise.getType()) && "8".equals(auxAppraise.getNum())){
							mon8 = auxAppraise.getLevel().getValue();
						}else if("3".equals(auxAppraise.getType()) && "9".equals(auxAppraise.getNum())){
							mon9 = auxAppraise.getLevel().getValue();
						}else if("3".equals(auxAppraise.getType()) && "10".equals(auxAppraise.getNum())){
							mon10 = auxAppraise.getLevel().getValue();
						}else if("3".equals(auxAppraise.getType()) && "11".equals(auxAppraise.getNum())){
							mon11 = auxAppraise.getLevel().getValue();
						}else if("3".equals(auxAppraise.getType()) && "12".equals(auxAppraise.getNum())){
							mon12 = auxAppraise.getLevel().getValue();
						}
					}
				}
                row.createCell(3).setCellValue(years == null ? "尚未考核" : years);
                row.createCell(4).setCellValue(quarter1 == null ? "尚未考核" : quarter1);
                row.createCell(5).setCellValue(quarter2 == null ? "尚未考核" : quarter2);
                row.createCell(6).setCellValue(quarter3 == null ? "尚未考核" : quarter3);
                row.createCell(7).setCellValue(quarter4 == null ? "尚未考核" : quarter4);
                row.createCell(8).setCellValue(mon1 == null ? "尚未考核" : mon1);
                row.createCell(9).setCellValue(mon2 == null ? "尚未考核" : mon2);
                row.createCell(10).setCellValue(mon3 == null ? "尚未考核" : mon3);
                row.createCell(11).setCellValue(mon4 == null ? "尚未考核" : mon4);
                row.createCell(12).setCellValue(mon5 == null ? "尚未考核" : mon5);
                row.createCell(13).setCellValue(mon6 == null ? "尚未考核" : mon6);
                row.createCell(14).setCellValue(mon7 == null ? "尚未考核" : mon7);
                row.createCell(15).setCellValue(mon8 == null ? "尚未考核" : mon8);
                row.createCell(16).setCellValue(mon9 == null ? "尚未考核" : mon9);
                row.createCell(17).setCellValue(mon10 == null ? "尚未考核" : mon10);
                row.createCell(18).setCellValue(mon11 == null ? "尚未考核" : mon11);
                row.createCell(19).setCellValue(mon12 == null ? "尚未考核" : mon12);
            }
            super.servletResponse.setContentType("application/x-excel");
            super.servletResponse.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("辅警考核列表.xls", "UTF-8"));
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
    
    private void exportAppraiseStatExl(List<String> stat, String year) {
        HSSFWorkbook workbook = null;
        try {
            workbook = new HSSFWorkbook();
            
            final HSSFSheet sheet = workbook.createSheet(year + "年考核统计");
            sheet.setRowSumsBelow(false);
            sheet.setDefaultColumnWidth(10);
            // 写表头
            String[][] styleDef = {
                {year+"年度考核统计", "0", "3"},
                {year+"年一季度", "4", "6"},
                {year+"年二季度", "7", "9"},
                {year+"年三季度", "10", "12"},
                {year+"年四季度", "13", "15"},
                {year+"年1月", "16", "18"},
                {year+"年2月", "19", "21"},
                {year+"年3月", "22", "24"},
                {year+"年4月", "25", "27"},
                {year+"年5月", "28", "30"},
                {year+"年6月", "31", "33"},
                {year+"年7月", "34", "36"},
                {year+"年8月", "37", "39"},
                {year+"年9月", "40", "42"},
                {year+"年10月", "43", "45"},
                {year+"年11月", "46", "48"},
                {year+"年12月", "49", "51"},
            };
            String[] fieldList = {"单位", "优秀人数", "合格人数", "不合格人数", "优秀人数", "合格人数", "不合格人数", "优秀人数", "合格人数", 
            		"不合格人数", "优秀人数", "合格人数", "不合格人数", "优秀人数", "合格人数", "不合格人数", "优秀人数", "合格人数", "不合格人数", 
            		"优秀人数", "合格人数", "不合格人数", "优秀人数", "合格人数", "不合格人数", "优秀人数", "合格人数", "不合格人数", "优秀人数", 
            		"合格人数", "不合格人数", "优秀人数", "合格人数", "不合格人数", "优秀人数", "合格人数", "不合格人数", "优秀人数", "合格人数", 
            		"不合格人数", "优秀人数", "合格人数", "不合格人数", "优秀人数", "合格人数", "不合格人数", "优秀人数", "合格人数", "不合格人数", "优秀人数", "合格人数", "不合格人数"};

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
            for (int i = 0; i < fieldList.length; i++) {
                cell = row.createCell(i);
                cell.setCellStyle(style);
                cell.setCellValue(fieldList[i]);
            }
            // 写内容
            for (int i = 0; i < stat.size(); i++) {
                row = sheet.createRow(count++);
                String[] list = stat.get(i).split(",");
                for (int j = 0; j < list.length; j++) {
                	row.createCell(j).setCellValue(list[j]);
				}
            }
            super.servletResponse.setContentType("application/x-excel");
            super.servletResponse.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("辅警考核统计.xls", "UTF-8"));
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
