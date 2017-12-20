package com.xwtiger.devrescollect.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by Busap-112 on 2017/12/18.
 */

public class StreamUtils {


    private static byte[] streamToBytes(InputStream is) {
        ByteArrayOutputStream os = new ByteArrayOutputStream(1024);
        byte[] buffer = new byte[1024];
        int len;
        try {
            while ((len = is.read(buffer)) >= 0) {
                os.write(buffer, 0, len);
            }
        } catch (java.io.IOException e) {
        }
        return os.toByteArray();
    }

}
