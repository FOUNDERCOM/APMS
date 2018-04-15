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

package com.founder.bj.apms.auxpolice.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.founder.bj.apms.auxpolice.entity.AuxInfo;
import com.founder.bj.apms.auxpolice.entity.AuxStuff;
import com.founder.bj.apms.auxpolice.service.AuxFlowService;
import com.founder.bj.apms.auxpolice.service.AuxInfoService;
import com.founder.bj.apms.com.CRUDController;
import com.founder.bj.apms.com.ClientIPUtils;
import com.founder.bj.apms.dept.entity.DeptBureau;
import com.founder.bj.apms.dept.entity.DeptStation;
import com.founder.bj.apms.sys.dto.StationDTO;
import com.founder.bj.apms.sys.entity.SysUser;
import com.lee.jwaf.action.AbstractControllerSupport;
import com.lee.jwaf.exception.ServiceException;
import com.lee.util.DateUtils;
import com.lee.util.ObjectUtils;

// CSOFF: RegexpSinglelineJava
/**
 * Description: Aux Controller.<br>
 * Created by Jimmybly Lee on 2017/10/9.
 *
 * @author Jimmybly Lee
 */
@SuppressWarnings("unused")
@Controller("AuxController")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AuxController extends AbstractControllerSupport implements CRUDController {

    /**
     * Flow Service.
     */
    @Resource
    private AuxFlowService flowService;
    /**
     * Info Service.
     */
    @Resource
    private AuxInfoService infoService;

    @Override
    public void query() {
        final AuxInfo condition = workDTO.convertJsonToBeanByKey("condition", AuxInfo.class);
        final List<AuxInfo> result = infoService.query(condition, workDTO.getStart(), workDTO.getLimit());
        clearForQuery(result);
        workDTO.setResult(result);
        workDTO.setTotle(infoService.count(condition));
    }

    /**
     * Clear the reverse relation of {@link AuxInfo} for serialization.
     *
     * @param list {@link AuxInfo} list
     */
    private void clearForQuery(List<AuxInfo> list) {
        for (AuxInfo item : list) {
            item.getLastUpdateUser().setStation(null);
            if (!ObjectUtils.isEmpty(item.getLastApproveUser())) {
                item.getLastApproveUser().setStation(null);
            }
            item.getStation().setLastUpdateUser(null);
            item.getStation().getBureau().setLastUpdateUser(null);

            clearStuffForQuery(item.getAwardList());
            clearStuffForQuery(item.getEduList());
            clearStuffForQuery(item.getFamilyList());
            clearStuffForQuery(item.getPunishList());
            clearStuffForQuery(item.getWorkList());
            clearStuffForQuery(item.getFileList());
            clearStuffForQuery(item.getAppraiseList());
        }
    }

    /**
     * Clear the reverse relation of {@link AuxStuff} for serialization.
     *
     * @param list {@link AuxStuff} list
     */
    private void clearStuffForQuery(Set<? extends AuxStuff> list) {
        for (AuxStuff item : list) {
            item.setAux(null);
        }
    }

    @Override
    public void create() throws ServiceException {
        final AuxInfo entity = workDTO.convertJsonToBeanByKey("entity", AuxInfo.class);

        entity.setIsEnabled(true);

        entity.setLastUpdateUser(new SysUser());
        entity.getLastUpdateUser().setId(sessionDTO.currentToken().user().getId());
        entity.setLastUpdateDate(DateUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss"));
        entity.setLastUpdateIp(ClientIPUtils.getClientIp(servletRequest));
        infoService.create(entity);
    }

    public void createPass() throws ServiceException {
        final AuxInfo entity = workDTO.convertJsonToBeanByKey("entity", AuxInfo.class);

        entity.setIsEnabled(true);

        entity.setLastUpdateUser(new SysUser());
        entity.getLastUpdateUser().setId(sessionDTO.currentToken().user().getId());
        entity.setLastUpdateDate(DateUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss"));
        entity.setLastUpdateIp(ClientIPUtils.getClientIp(servletRequest));
        String id = infoService.create(entity);

        flowService.pass(sessionDTO.currentToken(), ClientIPUtils.getClientIp(servletRequest),
        		id);
    }

    @Override
    public void update() throws ServiceException {
        final AuxInfo entity = workDTO.convertJsonToBeanByKey("entity", AuxInfo.class);

        entity.setLastUpdateUser(new SysUser());
        entity.getLastUpdateUser().setId(sessionDTO.currentToken().user().getId());
        entity.setLastUpdateDate(DateUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss"));
        entity.setLastUpdateIp(ClientIPUtils.getClientIp(servletRequest));
        infoService.update(entity);
    }

    @Override
    public void remove() {
        infoService.setStatus(sessionDTO.currentToken(), ClientIPUtils.getClientIp(servletRequest),
            workDTO.<String>get("id"), false);
    }

    @Override
    public void resume() {
        infoService.setStatus(sessionDTO.currentToken(), ClientIPUtils.getClientIp(servletRequest),
            workDTO.<String>get("id"), true);
    }

    /**
     * 删除辅助信息.
     *
     * @throws ServiceException cannot cast type
     */
    public void removeStuff() throws ServiceException {
        try {
            //noinspection unchecked
            infoService.removeStuff(workDTO.<String>get("id"),
                (Class<? extends AuxStuff>) Class.forName(workDTO.<String>get("type")));
        } catch (ClassNotFoundException ex) {
            throw new ServiceException("无法找到匹配的类型");
        }
    }

    /**
     * Apply.
     */
    public void apply() {
        flowService.apply(sessionDTO.currentToken(), ClientIPUtils.getClientIp(servletRequest),
            workDTO.<String>get("id"));
    }

    /**
     * Accept.
     */
    public void accept() {
        flowService.accept(sessionDTO.currentToken(), ClientIPUtils.getClientIp(servletRequest),
            workDTO.<String>get("id"));
    }

    /**
     * Pass.
     */
    public void pass() {
        flowService.pass(sessionDTO.currentToken(), ClientIPUtils.getClientIp(servletRequest),
            workDTO.<String>get("id"));
    }

    /**
     * Reject.
     */
    public void reject() {
        flowService.reject(sessionDTO.currentToken(), ClientIPUtils.getClientIp(servletRequest),
            workDTO.<String>get("id"));
    }

    /**
     * Change Salary.
     */
    public void changeSalary() {
        infoService.changeSalary(sessionDTO.currentToken(), ClientIPUtils.getClientIp(servletRequest),
            workDTO.<String>get("id"), workDTO.getInteger("salaryBase"), workDTO.getInteger("salaryBonus"),
            workDTO.getInteger("salaryTax"), workDTO.getInteger("salarySSS"), workDTO.getInteger("salarySFund"),
            workDTO.getInteger("salaryCSS"), workDTO.getInteger("salaryCFund"));
    }

    /**
     * Export Salary.
     */
    @SuppressWarnings("CheckStyle")
    public void exportSalary() {
        final AuxInfo condition = new AuxInfo();
        condition.setIsEnabled(true);
        condition.setStation(new DeptStation());
        condition.getStation().setBureau(new DeptBureau());
        condition.getStation().getBureau().setId(((StationDTO) sessionDTO.currentToken().user().getOrg()).getBureau().getId());
        final List<AuxInfo> result = infoService.query(condition, 0, -1);

        //noinspection CheckStyle
        HSSFWorkbook wb = null;
        try {
            wb = new HSSFWorkbook();
            final HSSFSheet sheet;

            sheet = wb.createSheet("工资信息");
            sheet.setRowSumsBelow(false);

            int count = 0;
            HSSFRow row = sheet.createRow(count++);
            row.createCell(0).setCellValue("分局");
            row.createCell(1).setCellValue("科所队");
            row.createCell(2).setCellValue("名称");
            row.createCell(3).setCellValue("性别");
            row.createCell(4).setCellValue("身份证号");
            row.createCell(5).setCellValue("基本工资");
            row.createCell(6).setCellValue("绩效奖金");
            row.createCell(7).setCellValue("所得税");
            row.createCell(8).setCellValue("个付社保");
            row.createCell(9).setCellValue("个付公积金");
            row.createCell(10).setCellValue("司付社保");
            row.createCell(11).setCellValue("司付公积金");
            row.createCell(12).setCellValue("实发工资");
            row.createCell(13).setCellValue("司付工资");

            for (AuxInfo item : result) {
                row = sheet.createRow(count++);
                row.createCell(0).setCellValue(item.getStation().getBureau().getName());
                row.createCell(1).setCellValue(item.getStation().getName());
                row.createCell(2).setCellValue(item.getName());
                row.createCell(3).setCellValue(item.getSex().getValue());
                row.createCell(4).setCellValue(item.getIdentityCard());
                row.createCell(5).setCellValue(ObjectUtils.isEmpty(item.getSalaryBase()) ? 0 : item.getSalaryBase());
                row.createCell(6).setCellValue(ObjectUtils.isEmpty(item.getSalaryBonus()) ? 0 : item.getSalaryBonus());
                row.createCell(7).setCellValue(ObjectUtils.isEmpty(item.getSalaryTax()) ? 0 : item.getSalaryTax());
                row.createCell(8).setCellValue(ObjectUtils.isEmpty(item.getSalarySSS()) ? 0 : item.getSalarySSS());
                row.createCell(9).setCellValue(ObjectUtils.isEmpty(item.getSalarySFund()) ? 0 : item.getSalarySFund());
                row.createCell(10).setCellValue(ObjectUtils.isEmpty(item.getSalaryCSS()) ? 0 : item.getSalaryCSS());
                row.createCell(11).setCellValue(ObjectUtils.isEmpty(item.getSalaryCFund()) ? 0 : item.getSalaryCFund());
                row.createCell(12).setCellValue(ObjectUtils.isEmpty(item.getSalarySGet()) ? 0 : item.getSalarySGet());
                row.createCell(13).setCellValue(ObjectUtils.isEmpty(item.getSalaryCPay()) ? 0 : item.getSalaryCPay());
            }

            super.servletResponse.setContentType("application/x-excel");
            super.servletResponse.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode("工资信息.xls", "UTF-8"));
            super.servletResponse.setCharacterEncoding("ISO8859-1");
            wb.write(servletResponse.getOutputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (wb != null) {
                try {
                    wb.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            try {
                servletResponse.getOutputStream().flush();
                servletResponse.getOutputStream().close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Check duplicated Identity card.
     */
    public void checkDuplicatedIdentityCard() {
        workDTO.setResult(infoService.checkDuplicatedIdentityCare(workDTO.<String>get("id"), workDTO.<String>get("card")));
    }

}
