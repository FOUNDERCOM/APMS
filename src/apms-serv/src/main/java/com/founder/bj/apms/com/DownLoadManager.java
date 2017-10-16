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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: download manager, which can download one file with multi thread.<br>
 * Created by Jimmybly Lee on 2017/10/9.
 *
 * @author Jimmybly Lee
 */
class DownLoadManager {

    /** Static download file path. */
    private static final String CNS_FILE_PATH = "D:/downloadFiles";

    /** Thread num. */
    private static final Integer CNS_THREAD_NUM = 4;

    /** Logger. */
    private Logger log = LoggerFactory.getLogger(getClass());

    /** Url path. */
    private String urlPath;

    /**
     * Constructor.
     *
     * @param urlPath the download url
     */
    DownLoadManager(String urlPath) {
        super();
        this.urlPath = urlPath;
    }

    /**
     * Start download.
     */
    void download() {
        try {
            final String fileName = FilenameUtils.getName(urlPath);

            final URL url = new URL(urlPath);
            final URLConnection connection = url.openConnection();
            final int contentLength = connection.getContentLength();
            final File file = new File(CNS_FILE_PATH + "/" + fileName);
            log.debug("downloading {} with content length {} to path {}", fileName, contentLength, file.getAbsolutePath());

            // download it
            final long sub = contentLength / CNS_THREAD_NUM;
            for (int i = 0; i < CNS_THREAD_NUM; i++) {
                final long starPos = sub * i;
                final long endPos = sub * (i + 1) - 1;
                final DownloadThread thread = new DownloadThread(starPos, endPos, file, url);
                thread.start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
