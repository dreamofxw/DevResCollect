package com.xwtiger.devrescollect.study.javaapi.cipher;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtils {


    public static void main(String[] args) {
        byte[] bytes = decryptData("abc".getBytes());
        System.out.println(bytes);
    }

    public static byte[] decryptData(byte[] decryptdata){
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(decryptdata,"AES");
            Cipher aes = Cipher.getInstance("AES");
            aes.init(Cipher.DECRYPT_MODE,secretKeySpec,new IvParameterSpec(new byte[aes.getBlockSize()]));
            byte[] bytes = aes.doFinal();
            return bytes;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

}
