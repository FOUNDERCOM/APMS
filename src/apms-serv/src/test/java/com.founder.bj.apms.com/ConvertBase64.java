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

package com.founder.bj.apms.com;

import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Description: 将文件夹下的文件都转换为base64编码字符串.<br>
 * Created by Jimmybly Lee on 2017/10/10.
 *
 * @author Jimmybly Lee
 */
public class ConvertBase64 {

    public static void main(String[] args) {
        String filePath = "D:/123";
        File folder = new File(filePath);
        File[] files = folder.listFiles();
        System.out.println(files.length);
        for (File file : files) {
            try {
                FileInputStream fis = new FileInputStream(file);
                final byte[] data = new byte[fis.available()];
                fis.read(data);
                final Base64 encoder = new Base64();
                System.out.println(file.getName());
                System.out.println(encoder.encodeToString(data));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
