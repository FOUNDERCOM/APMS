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

package com.founder.bj.apms.att.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.ResourceBundle;

import javax.activation.MimetypesFileTypeMap;
import javax.annotation.Resource;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.founder.bj.apms.att.entity.Attachment;
import com.founder.bj.apms.att.service.AttService;
import com.founder.bj.apms.com.ClientIPUtils;
import com.founder.bj.apms.sys.entity.SysUser;
import com.lee.jwaf.action.AbstractControllerSupport;
import com.lee.jwaf.exception.ServiceException;
import com.lee.util.DateUtils;

/**
 * Description: 附件控制器.<br>
 * Created by Jimmybly Lee on 2017/10/18.
 *
 * @author Jimmybly Lee
 */
@SuppressWarnings("unused")
@Controller("AttController")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AttController extends AbstractControllerSupport {

    private final static float CNS_MAX_SIZE = 20000;
    /** AttService. */
    @Resource
    private AttService service;

    /**
     * 上传文件，并截图.
     * @throws ServiceException io EXCEPTION
     */
    public void uploadAndCapture() throws ServiceException {
        try {
            final MultipartHttpServletRequest fileReq = (MultipartHttpServletRequest) servletRequest;
            final MultipartFile file = fileReq.getFile("file");
            final Attachment att = createEntity(file);
            workDTO.setResult(service.create(att));

            if (att.getType().startsWith("image/jpeg")) {
                // 待输出结果
                final ByteArrayOutputStream bos = new ByteArrayOutputStream();
                final ImageOutputStream ios = ImageIO.createImageOutputStream(bos);

                // 压缩比
                final ImageWriter writer = ImageIO.getImageWritersByMIMEType(att.getType()).next();
                final ImageWriteParam param = writer.getDefaultWriteParam();
                if (att.getSize() >= CNS_MAX_SIZE) {
                    param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                    param.setCompressionQuality(CNS_MAX_SIZE / file.getSize());
                }

                // 生成压缩结果
                writer.setOutput(ios);
                writer.write(null, new IIOImage(ImageIO.read(file.getInputStream()), null, null), param);

                log.info("{} > {}", file.getSize(), bos.size());
                // 写入结果
                workDTO.put("data", new Base64().encodeToString(bos.toByteArray()));

                bos.close();
                ios.close();
                writer.dispose();
            } else {
                final ResourceBundle rb = ResourceBundle.getBundle("default-img");
                if (att.getType().contains("word")) {
                    workDTO.put("data", rb.getString("word"));
                } else if (att.getType().contains("excel")) {
                    workDTO.put("data", rb.getString("excel"));
                } else if (att.getType().contains("pdf")) {
                    workDTO.put("data", rb.getString("pdf"));
                } else {
                    workDTO.put("data", rb.getString("file"));
                }
            }
        } catch (IOException ex) {
            // CSOFF: RegexpSinglelineJava
            throw new ServiceException("无法解析文件。", ex);
            // CSON: RegexpSinglelineJava
        }
    }

    /**
     * 单纯上传文件.
     * @throws ServiceException io EXCEPTION
     */
    public void upload() throws ServiceException {
        try {
            final MultipartHttpServletRequest fileReq = (MultipartHttpServletRequest) servletRequest;
            final MultipartFile file = fileReq.getFile("file");

            workDTO.setResult(service.create(createEntity(file)));
        } catch (IOException ex) {
            // CSOFF: RegexpSinglelineJava
            throw new ServiceException("无法解析文件。", ex);
            // CSON: RegexpSinglelineJava
        }
    }

    /**
     * Create entity.
     * @param file request file
     * @return entity
     * @throws IOException cannot get byte
     */
    private Attachment createEntity(MultipartFile file) throws IOException {
        final Attachment att = new Attachment();
        final String fileName = file.getOriginalFilename();
        att.setName(FilenameUtils.getBaseName(fileName));
        att.setSuffix(FilenameUtils.getExtension(fileName));
        att.setType(new MimetypesFileTypeMap().getContentType(fileName));
        att.setData(file.getBytes());
        att.setSize(file.getSize());
        att.setLastUpdateUser(new SysUser());
        att.getLastUpdateUser().setId(sessionDTO.currentToken().user().getId());
        att.setLastUpdateDate(DateUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss"));
        att.setLastUpdateIp(ClientIPUtils.getClientIp(servletRequest));
        return att;
    }

    /**
     * 下载文件.
     * @throws ServiceException IO Exception
     * @throws IOException name error
     */
    public void download() throws ServiceException, IOException {
        final Attachment att = service.get(workDTO.<String>get("id"));

        super.servletResponse.setContentType(att.getType());
        super.servletResponse.setHeader("Content-Disposition",
            "attachment;filename=" + URLEncoder.encode(att.getName(), "UTF-8") + "." + att.getSuffix());
        super.servletResponse.setCharacterEncoding("ISO8859-1");
        servletResponse.getOutputStream().write(att.getData());
    }
}
