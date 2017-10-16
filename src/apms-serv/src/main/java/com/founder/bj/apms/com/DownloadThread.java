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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.net.URLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lee.util.ObjectUtils;

/**
 * Description: download thread.<br>
 * Created by Jimmybly Lee on 2017/10/9.
 *
 * @author Jimmybly Lee
 */
public class DownloadThread extends Thread {

    /** Block size. */
    private static final Integer CNS_BLOCK_SIZE = 256;

    /** Logger. */
    private Logger log = LoggerFactory.getLogger(getClass());

    /** Start position. */
    private long starPos;
    /** End position. */
    private long endPos;
    /** File. */
    private File file;
    /** 下载的URL. */
    private URL url;
    /** Current position. */
    private long curPos;

    /**
     * Constructor.
     * @param starPos start pos
     * @param endPos end pos
     * @param saveFile saved file
     * @param url url
     */
    DownloadThread(long starPos, long endPos, File saveFile, URL url) {
        super();
        this.starPos = starPos;
        this.endPos = endPos;
        this.file = saveFile;
        this.url = url;
        curPos = starPos;
    }

    @Override
    public void run() {
        super.run();

        log.trace("Downloading {} from {} to {}", file.getName(), starPos, endPos);

        final URLConnection conn;
        RandomAccessFile fos = null;
        BufferedInputStream bis = null;

        try {
            // create a new connection, and set the start and end place
            conn = url.openConnection();
            conn.setAllowUserInteraction(true);
            conn.setRequestProperty("Range", "bytes=" + starPos + "-" + endPos);

            // prepare to write file
            fos = new RandomAccessFile(file, "rw");
            fos.seek(starPos);

            // write it.
            bis = new BufferedInputStream(conn.getInputStream());
            final byte[] buf = new byte[CNS_BLOCK_SIZE];
            while (curPos < endPos) {
                final int len = bis.read(buf, 0, CNS_BLOCK_SIZE);
                if (len == -1) {
                    break;
                }
                fos.write(buf, 0, len);
                curPos = curPos + len;
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (!ObjectUtils.isEmpty(bis)) {
                try {
                    bis.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (!ObjectUtils.isEmpty(fos)) {
                try {
                    fos.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

    }
}
