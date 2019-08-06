package com.xwtiger.devrescollect.statistics;

import android.text.TextUtils;

import com.xwtiger.devrescollect.MyException;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * MD5加密
 *
 * @author lxq
 */
public class MD5Util {

    public static final String encodeBy32BitMD5(String source) {
        return encrypt(source, false);
    }

    private static final String encrypt(String source, boolean is16bit) {
        //if (TextUtils.isEmpty(source)) return "";

        String encryptedStr = null;
        try {
            MessageDigest digester = MessageDigest.getInstance("MD5");
            encryptedStr = convertToHexString(digester.digest(source.getBytes("utf-8")));
            if (is16bit) {
                encryptedStr = encryptedStr.substring(8, 24);
            }
        } catch (NoSuchAlgorithmException e) {
            MyException.printStr(e);
        } catch (UnsupportedEncodingException e) {
            MyException.printStr(e);
        }

        return encryptedStr;
    }

    private static final String convertToHexString(byte data[]) {
        int i;
        StringBuffer buf = new StringBuffer();
        for (int offset = 0; offset < data.length; offset++) {
            i = data[offset];
            if (i < 0) {
                i += 256;
            }
            if (i < 16) {
                buf.append("0");
            }
            buf.append(Integer.toHexString(i));
        }
        return buf.toString();
    }

    public static Map<String, String> getNewMap(String uid, String url, Map<String, String> map) {
        String string = url + stringSort(map) + encodeBy32BitMD5(uid + "_!234567899@dalaba.com");
        map.put("token", encodeBy32BitMD5(string));
        return map;
    }

    /**
     * 排序 对map的key键值按字典升序排序
     *
     * @param map
     * @return
     */
    private static String stringSort(Map<String, String> map) {
        String string = "";
        Collection<String> keyset = map.keySet();
        List<String> list = new ArrayList<>(keyset);
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            string = string + "/" + list.get(i) + "/" + map.get(list.get(i));
        }
        return string;
    }

    //微信支付
    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname))
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
        } catch (Exception exception) {
        }
        return resultString;
    }

    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

}
