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

/**
 * Description: download images from http://flat-icon-design.com.<br>
 * Created by Jimmybly Lee on 2017/10/9.
 *
 * @author Jimmybly Lee
 */
public class ImgResourceDownload {

    public static void main(String[] args) {
        String[] types = {"business", "event", "health", "object", "traffic"};
        Integer[] size = {94, 98, 59, 174, 45};
        for (int i = 0; i < types.length; i++) {
            for (int j = 1; j <= size[i]; j++) {
                String url = "http://flat-icon-design.com/f/";
                url += "f_" + types[i] + "_" + j + "/";
                url += "s256_f_" + types[i] + "_" + j + "_0bg.png";
                final DownLoadManager manager = new DownLoadManager(url);
                manager.download();
            }
        }
    }
}
