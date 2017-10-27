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

package com.founder.bj.apms.sys.controller;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.*;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.lee.jwaf.action.AbstractControllerSupport;
import com.lee.jwaf.exception.ServiceException;
import com.lee.util.Assert;

/**
 * Description: base 64 图片处理控制器（服务）.<br>
 * Created by Jimmybly Lee on 2017/7/4.
 *
 * @author Jimmybly Lee
 */
// CSOFF: ClassDataAbstractionCoupling
@Controller("Base64Controller")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@SuppressWarnings("unused")
public class Base64Controller extends AbstractControllerSupport {

    /** 标准高度. */
    @SuppressWarnings("FieldCanBeLocal")
    private final Integer CNS_STD_HEIGHT = 300;
    /** 标准宽度. */
    @SuppressWarnings("FieldCanBeLocal")
    private final Integer CNS_STD_WIDTH = 300;

    /**
     * 将上传图片转化为base64编码的String.
     * @throws ServiceException 无法解析图片
     */
    public void convertImg2Base64() throws ServiceException {
        try {
            final MultipartHttpServletRequest fileReq = (MultipartHttpServletRequest) servletRequest;
            final InputStream uploadFileStream = fileReq.getFile("photo").getInputStream();
            final Base64 encoder = new Base64();
            final byte[] data = new byte[uploadFileStream.available()];
            //noinspection ResultOfMethodCallIgnored
            uploadFileStream.read(data);
            uploadFileStream.close();
            workDTO.setResult(encoder.encodeToString(data));
        } catch (IOException ex) {
            // CSOFF: RegexpSinglelineJava
            throw new ServiceException("无法解析图片。", ex);
            // CSON: RegexpSinglelineJava
        }
    }

    /**
     * 将上传图片流转化为base64编码.
     */
    public void formatBase64Img() {
        try {
            final Base64 base64 = new Base64();
            final CutInfo cut = new CutInfo();
            cut.startX = new Double(Double.parseDouble(workDTO.<String>get("x"))).intValue();
            cut.startY = new Double(Double.parseDouble(workDTO.<String>get("y"))).intValue();
            cut.width = new Double(Double.parseDouble(workDTO.<String>get("width"))).intValue();
            cut.height = new Double(Double.parseDouble(workDTO.<String>get("height"))).intValue();

            final byte[] data = formatImage(base64.decode(workDTO.<String>get("data")), cut);
            workDTO.setResult(base64.encodeToString(data));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 处理图片.
     * @param data 图片源
     * @param cut 剪裁信息
     * @throws IOException 无法处理图片
     * @return 图片字节
     */
    private byte[] formatImage(byte[] data, CutInfo cut) throws IOException {
        return formatImage(new ByteArrayInputStream(data), cut);
    }

    /**
     * 处理图片.
     * @param srcInputStream 图片源
     * @param cut 剪裁信息
     * @throws IOException 无法处理图片
     * @return 图片字节
     */
    private byte[] formatImage(InputStream srcInputStream, CutInfo cut) throws IOException {
        // 原图 信息
        final BufferedImage srcBufferedImage = ImageIO.read(srcInputStream);
        final Integer srcWidth = srcBufferedImage.getWidth();
        final Integer srcHeight = srcBufferedImage.getHeight();
        final Image srcImage = srcBufferedImage.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);
        Assert.isTrue((cut.startX + cut.width) <= srcWidth);
        Assert.isTrue((cut.startY + cut.height) <= srcHeight);

        // 剪裁
        final ImageFilter cropFilter = new CropImageFilter(cut.startX, cut.startY, cut.width, cut.height);
        final Image cutImage = Toolkit.getDefaultToolkit()
            .createImage(new FilteredImageSource(srcImage.getSource(), cropFilter));
        final BufferedImage cutBufferedImage = new BufferedImage(cut.width, cut.height, BufferedImage.TYPE_INT_RGB);
        final Graphics cutGraphic = cutBufferedImage.getGraphics();
        cutGraphic.drawImage(cutImage, 0, 0, cut.width, cut.height, null);
        cutGraphic.dispose();

        // 缩放到固定像素
        final BufferedImage scaledBufferedImage = new BufferedImage(CNS_STD_WIDTH, CNS_STD_HEIGHT,
            BufferedImage.TYPE_INT_RGB);
        final double rateX = CNS_STD_WIDTH.doubleValue() / srcWidth;
        final double rateY = CNS_STD_HEIGHT.doubleValue() / srcHeight;
        new AffineTransformOp(AffineTransform.getScaleInstance(rateX, rateY), null)
            .filter(scaledBufferedImage, null);
        final Graphics scaledGraphic = scaledBufferedImage.getGraphics();
        scaledGraphic.drawImage(cutImage, 0, 0, CNS_STD_WIDTH, CNS_STD_HEIGHT, null);
        scaledGraphic.dispose();

        // 返回输出流
        final ByteArrayOutputStream result = new ByteArrayOutputStream();
        ImageIO.write(scaledBufferedImage, "JPEG", result);
        return result.toByteArray();
    }

    /**
     * Description: 图像处理参数.<br>
     * Created by Jimmybly Lee on 2017/7/4.
     *
     * @author Jimmybly Lee
     */
    private final class CutInfo {

        /** 开始坐标x.*/
        private Integer startX;
        /** 开始坐标y.*/
        private Integer startY;
        /** 截图高.*/
        private Integer height;
        /** 截图宽.*/
        private Integer width;
        // CSON: all
    }
}
